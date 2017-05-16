package es.mabu.mr.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mabu.mr.order.Order;
import es.mabu.mr.order.dto.base.PaymentDetailsDto;
import es.mabu.mr.order.dto.response.OptionLinePriceResponseDto;
import es.mabu.mr.order.dto.response.OrderLineResponseDto;
import es.mabu.mr.order.dto.response.OrderResponseDto;
import es.mabu.mr.order.orderline.OrderLine;
import es.mabu.mr.order.paymentDetails.PaymentDetails;
import es.mabu.mr.product.ProductService;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.optionlineprice.OptionLinePriceRepository;
import es.mabu.mr.restaurant.RestaurantService;
import es.mabu.mr.user.client.ClientService;

@Service
public class OrderConversor {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	ClientService clientService;

	@Autowired
	ProductService productService;

	@Autowired
	OptionLinePriceRepository optionLinePriceRepository;

	public List<OrderResponseDto> orderToOrderResponseDto(List<Order> orders) {
		List<OrderResponseDto> os = new ArrayList<>();

		for (Order o : orders) {
			os.add(orderToOrderResponseDto(o));
		}
		return os;
	}

	public OrderResponseDto orderToOrderResponseDto(Order order) {
		// ORDER LINES
		List<OrderLineResponseDto> orderlinesDtos = new ArrayList<>(order.getOrderLines().size());

		for (OrderLine ol : order.getOrderLines()) {
			List<OptionLinePriceResponseDto> olpdtos = new ArrayList<>();
			for (OptionLinePrice olp : ol.getOptionLinePrices()) {
				olpdtos.add(new OptionLinePriceResponseDto(olp.getId(), olp.getPriceAdded(),
						olp.getRestaurant().getId(), olp.getOptionLine().getOption().getName(),
						olp.getOptionLine().getName(), olp.getOptionLine().getOption().isMain()));
			}
			orderlinesDtos.add(new OrderLineResponseDto(ol.getId(), ol.getProduct().getId(), ol.getTaxe(),
					ol.getQuantity(), ol.getPrice(), ol.getTotalPrice(), olpdtos, ol.getProduct().getName(),
					ol.getProduct().getImageName()));
		}

		return new OrderResponseDto(order.getId(), order.getClient().getId(), order.getAddress(),
				order.getRestaurant().getId(), order.getServiceType(), order.getEstimatedPickupOrDeliveryTime(),
				order.getTotalPrice(), order.getDeliveryCharge(), order.getCashOnDelivery(), order.getClientNote(),
				order.getCreated(), order.getSent(), order.getStatus(), orderlinesDtos,
				paymentDetailsToPaymentDetailsDto(order.getPaymentDetails()));
	}

	public PaymentDetailsDto paymentDetailsToPaymentDetailsDto(PaymentDetails paymentDetails) {
		if (paymentDetails != null) {
			return new PaymentDetailsDto(paymentDetails);
		} else {
			return null;
		}
	}
}
