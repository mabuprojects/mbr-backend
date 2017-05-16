package es.mabu.mr.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import es.mabu.mr.address.Address;
import es.mabu.mr.address.AddressRepository;
import es.mabu.mr.exception.exception.NotFoundException;
import es.mabu.mr.order.dto.request.OrderLinePriceRequestDto;
import es.mabu.mr.order.dto.request.OrderLineRequestDto;
import es.mabu.mr.order.dto.request.OrderRequestDto;
import es.mabu.mr.order.dto.request.PaymentRequest;
import es.mabu.mr.order.exception.CashOnDeliveryNotAvaliable;
import es.mabu.mr.order.exception.OrderCanNotBePaidException;
import es.mabu.mr.order.exception.OrderHasNoProductsException;
import es.mabu.mr.order.exception.OutsideAddressRestaurantException;
import es.mabu.mr.order.exception.ProductNotAvaliable;
import es.mabu.mr.order.exception.RestaurantHasNotThisService;
import es.mabu.mr.order.exception.RestaurantNotAvaliableException;
import es.mabu.mr.order.exception.StripeCardException;
import es.mabu.mr.order.exception.WrongStatusException;
import es.mabu.mr.order.orderline.OrderLine;
import es.mabu.mr.order.orderline.OrderLineRepository;
import es.mabu.mr.order.paymentDetails.PaymentDetails;
import es.mabu.mr.order.paymentDetails.PaymentDetailsRepository;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductService;
import es.mabu.mr.product.ProductState;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.optionlineprice.OptionLinePriceRepository;
import es.mabu.mr.product.productDetails.ProductDetails;
import es.mabu.mr.product.productDetails.ProductDetailsRepository;
import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.restaurant.RestaurantService;
import es.mabu.mr.restaurant.RestaurantServiceType;
import es.mabu.mr.user.client.ClientService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	ClientService clientService;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductDetailsRepository productDetailsRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;

	@Autowired
	OptionLinePriceRepository optionLinePriceRepository;

	@Value("${stripe.apiKey}")
	private String stripeApiKey;

	@Value("${stripe.currency}")
	private String currency;

	@Value("${stripe.description}")
	private String description;

	@Value("${company.cashOnDeliveryAvaliable}")
	private boolean cashOnDeliveryAvaliable;

	@Override
	public Order payOrder(PaymentRequest paymentRequest, Long clientId)
			throws StripeCardException, OrderCanNotBePaidException {

		Order order = orderRepository.findOne(paymentRequest.getOrderId());

		/* El estado debe ser siempre pendiente de pago */
		if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
			throw new OrderCanNotBePaidException("Status should be PENDING_PAYMENT");
		}
		/* Sólo los dueños de las orders pueden pagarlas */
		if (order.getClient().getId() != clientId) {
			throw new OrderCanNotBePaidException("Invalid client");
		}
		PaymentDetails paymentDetailsSaved = createCharge(order, paymentRequest);
		order.setPaymentDetails(paymentDetailsSaved);
		order.setStatus(OrderStatus.PAID);
		return orderRepository.save(order);

	}

	public PaymentDetails createCharge(Order order, PaymentRequest paymentRequest) throws StripeCardException {

		Stripe.apiKey = stripeApiKey;

		// Token is created using Stripe.js or Checkout!
		// Get the payment token submitted by the form:

		// Charge the user's card:
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", getStripeAmount(order.getTotalPrice()));
		params.put("currency", currency);
		params.put("description", description);
		params.put("source", paymentRequest.getTokenStripe());

		Charge charge;

		try {
			charge = Charge.create(params);

			PaymentDetails paymentDetails = new PaymentDetails(order.getId(), order, "stripe", charge.getId(),
					new Timestamp(new Date().getTime()), null, new BigDecimal(charge.getAmount()));
			PaymentDetails paymentDetailsSaved = paymentDetailsRepository.save(paymentDetails);

			return paymentDetailsSaved;

		} catch (CardException e) {
			// Since it's a decline, CardException will be caught
			throw new StripeCardException(e.getCode());

		} catch (RateLimitException e) {
			// Too many requests made to the API too quickly
			throw new StripeCardException(e.getMessage());
		} catch (InvalidRequestException e) {
			// Invalid parameters were supplied to Stripe's API
			throw new StripeCardException(e.getMessage());
		} catch (AuthenticationException e) {
			// Authentication with Stripe's API failed
			// (maybe you changed API keys recently)
			throw new StripeCardException(e.getMessage());
		} catch (APIConnectionException e) {
			// Network communication with Stripe failed
			throw new StripeCardException(e.getMessage());
		} catch (StripeException e) {
			// Display a very generic error to the user, and maybe send
			// yourself an email
			throw new StripeCardException(e.getMessage());
		}
	}

	// TODO Probar exhaustivamente esta función auxiliar. Realizar la función
	// inversa para payment details
	public String getStripeAmount(BigDecimal totalPrice) {
		BigDecimal amount = totalPrice.setScale(2, RoundingMode.CEILING);
		amount = amount.multiply(new BigDecimal("100"));
		String a = amount.toPlainString();
		int radixLoc = a.indexOf('.');
		return a.substring(0, radixLoc);
	}

	/**
	 * Create order
	 * 
	 * @throws OrderHasNoProductsException
	 * @throws RestaurantHasNotThisService
	 * @throws RestaurantNotAvaliableException
	 * @throws CashOnDeliveryNotAvaliable
	 * @throws ProductNotAvaliable
	 * @throws OutsideAddressRestaurantException
	 */
	@Override
	public Order createOrder(OrderRequestDto order)
			throws OrderHasNoProductsException, RestaurantHasNotThisService, RestaurantNotAvaliableException,
			CashOnDeliveryNotAvaliable, ProductNotAvaliable, OutsideAddressRestaurantException {

		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

		/* Para poder comprar en un restaurante este debe estar visible */
		if (!restaurant.getVisible()) {
			throw new RestaurantNotAvaliableException("");
		}

		/* Compruebo si ese restaurante tiene ese servicio activo */
		if (!restaurant.getServices().contains(order.getServiceType())) {
			throw new RestaurantHasNotThisService(
					restaurant.getName() + " has not" + order.getServiceType().toString() + "service.");
		}

		/*
		 * Compruebo que si es de tipo DELIVERY, que le código postal sea válido
		 */
		if (order.getServiceType() == RestaurantServiceType.DELIVERY) {
			boolean validZip = false;

			for (Integer zip : restaurant.getZipCodes()) {
				if (zip.toString().equals(order.getAddress().getPostalCode())) {
					validZip = true;
				}
			}
			if (!validZip) {
				throw new OutsideAddressRestaurantException("Invalid Address");
			}

		}

		/* Comprobar si existe al menos un producto en el pedido */
		if (order.getOrderLines().isEmpty()) {
			throw new OrderHasNoProductsException("order must have at least one product");
		}

		Timestamp currentTime = new Timestamp(new Date().getTime());

		/* Los descuentos están desactivados y los gastos de envio son 0 */
		Order orderSaved = new Order(clientService.findClientById(order.getClientId()), null, restaurant,
				order.getServiceType(), null, BigDecimal.ZERO, BigDecimal.ZERO, order.isCashOnDelivery(),
				order.getClientNote(), null, null, currentTime, null, null, new ArrayList<>());

		/* El cliente quiere pagar en el envio */
		if (order.isCashOnDelivery()) {
			if (cashOnDeliveryAvaliable) {
				/* Ese servicio esta disponible */
				orderSaved.setStatus(OrderStatus.NOT_PAID);
			} else {
				/* No se puede pagar en el envio, obligatorio pagar */
				throw new CashOnDeliveryNotAvaliable("");
			}

		} else {
			orderSaved.setStatus(OrderStatus.PENDING_PAYMENT);
		}

		/* Si la dirección no esta persistida la persisto */

		if (order.getAddress().getId() == null) {
			orderSaved.setAddress(addressRepository.save(order.getAddress()));
		} else {
			/*
			 * Si la direccion ya existe (se corresponde a la de un cliente, la
			 * duplico
			 */

			Address address = addressRepository.findOne(order.getAddress().getId());
			orderSaved.setAddress(addressRepository.save(duplicateAddress(address)));
		}

		orderSaved = orderRepository.save(orderSaved);

		/* Create optionLines */
		orderSaved.setOrderLines(createOrderLines(order.getOrderLines(), orderSaved));
		/* Set Total Price */
		orderSaved.setTotalPrice(geTotalPrice(orderSaved.getOrderLines(), orderSaved.getDeliveryCharge()));

		return orderRepository.save(orderSaved);
	}

	/**
	 * Aux function
	 * 
	 * @param address
	 * @return
	 */
	private Address duplicateAddress(Address address) {
		return new Address(address.getCountry(), address.getState(), address.getCity(), address.getStreet(),
				address.getNumber(), address.getUnity(), address.getPostalCode(), address.getObservations(),
				address.getLatitude(), address.getLongitude());
	}

	/**
	 * Persiste las orderlines
	 * 
	 * @param orderLines
	 * @param orderSaved
	 * @return
	 * @throws ProductNotAvaliable
	 */
	private List<OrderLine> createOrderLines(List<OrderLineRequestDto> orderLines, Order orderSaved)
			throws ProductNotAvaliable {

		List<OrderLine> neworderLines = new ArrayList<>();

		for (OrderLineRequestDto ol : orderLines) {

			ProductDetails pds = productDetailsRepository.findByProductIdAndRestaurantId(ol.getProductId(),
					orderSaved.getRestaurant().getId());

			/* En caso de que el estado del producto no sea valido */
			if (pds.getState() == ProductState.HIDDEN || pds.getState() == ProductState.HISTORICAL) {
				throw new ProductNotAvaliable("State =" + pds.getState());
			}

			BigDecimal price = getOrderLinePrice(ol.getOrderLinePrices(), pds.getPrice());

			BigDecimal priceTotalOrderLine = price.multiply(new BigDecimal(ol.getQuantity()));

			Product product = productService.findProductById(ol.getProductId());

			List<OptionLinePrice> olps = ol.getOrderLinePrices().stream()
					.map(olp -> optionLinePriceRepository.findOne(olp.getId())).collect(Collectors.toList());

			neworderLines.add(orderLineRepository.save(new OrderLine(orderSaved, product, product.getTaxe(),
					ol.getQuantity(), pds.getPrice(), priceTotalOrderLine, olps)));

		}
		return neworderLines;
	}

	// TODO COMPROBAR QUE SI EL PRODUCTO TIENE UNA OPCION CON MAIN ESTA ES
	// OBLIGATORIA, SOLTAR UNA EXCEPCIÓN EN CASO DE QUE FALLE

	private BigDecimal getOrderLinePrice(List<OrderLinePriceRequestDto> optionLinePrices, BigDecimal basePrice) {
		BigDecimal price = basePrice;
		BigDecimal priceAded = BigDecimal.ZERO;

		for (OrderLinePriceRequestDto optionLinePrice : optionLinePrices) {

			OptionLinePrice olp = optionLinePriceRepository.findOne(optionLinePrice.getId());

			if (olp.getOptionLine().getOption().isMain()) {
				price = olp.getPriceAdded();

			} else {
				priceAded = priceAded.add(olp.getPriceAdded());
			}
		}

		return price.add(priceAded);

	}

	/**
	 * Return el precio total de la lista de opciones mas los gastos de envio
	 * 
	 * @param orderLines
	 * @return
	 */
	private BigDecimal geTotalPrice(List<OrderLine> orderLines, BigDecimal deliveryCharge) {

		BigDecimal priceTotal = BigDecimal.ZERO;

		for (OrderLine ol : orderLines) {
			priceTotal = priceTotal.add(ol.getTotalPrice());
		}
		return priceTotal.add(deliveryCharge);
	}

	@Override
	public Order findOrderById(Long orderId) {
		return orderRepository.findOne(orderId);
	}

	@Override
	public List<Order> findOrderByClient(Long clientId) {
		return orderRepository.findByClientIdOrderByCreatedDesc(clientId);
	}

	@Override
	public List<Order> findOpenOrdersByRestaurant(Long restaurantId) {
		return orderRepository.findOpenOrdersByRestaurant(restaurantId, OrderStatus.CLOSED, OrderStatus.CANCELED);
	}

	@Override
	public Order cookOrder(Long orderId) throws WrongStatusException, NotFoundException {
		Order order = orderRepository.findOne(orderId);

		if (order.getStatus() == OrderStatus.PAID) {
			return changeStatus(orderId, OrderStatus.PAID, OrderStatus.COOKING);
		} else if (order.getStatus() == OrderStatus.NOT_PAID) {
			return changeStatus(orderId, OrderStatus.NOT_PAID, OrderStatus.COOKING);
		}

		throw new WrongStatusException(OrderStatus.PAID, order.getStatus());
	}

	@Override
	public Order sendOrder(Long orderId) throws WrongStatusException, NotFoundException {
		return changeStatus(orderId, OrderStatus.COOKING, OrderStatus.SENDED);
	}

	@Override
	public Order finishOrder(Long orderId) throws WrongStatusException, NotFoundException {
		return changeStatus(orderId, OrderStatus.SENDED, OrderStatus.CLOSED);
	}

	private Order changeStatus(Long orderId, OrderStatus previous, OrderStatus newStatus)
			throws WrongStatusException, NotFoundException {
		Order order = orderRepository.findOne(orderId);
		validateStatus(order, previous);
		order.setStatus(newStatus);
		return orderRepository.save(order);
	}

	private void validateStatus(Order order, OrderStatus previous) throws WrongStatusException {
		if (previous == null) {
			validateStatus(order, OrderStatus.CANCELED);
			validateStatus(order, OrderStatus.CLOSED);
		} else {
			if (order.getStatus() != previous) {
				throw new WrongStatusException(previous, order.getStatus());
			}
		}
	}

	@Override
	public Order cancelOrder(Long orderId) throws WrongStatusException, NotFoundException {
		return changeStatus(orderId, null, OrderStatus.CANCELED);
	}

}
