package es.mabu.mr.order;

import java.util.List;

import es.mabu.mr.exception.exception.NotFoundException;
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

public interface OrderService {

	Order createOrder(OrderRequestDto order)
			throws OrderHasNoProductsException, RestaurantHasNotThisService, RestaurantNotAvaliableException,
			CashOnDeliveryNotAvaliable, ProductNotAvaliable, OutsideAddressRestaurantException;

	Order findOrderById(Long orderId);

	List<Order> findOrderByClient(Long clientId);

	List<Order> findOpenOrdersByRestaurant(Long restaurantId);

	Order payOrder(PaymentRequest paymentRequest, Long clientId) throws StripeCardException, OrderCanNotBePaidException;

	Order cookOrder(Long orderId) throws WrongStatusException, NotFoundException;

	Order sendOrder(Long orderId) throws WrongStatusException, NotFoundException;

	Order finishOrder(Long orderId) throws WrongStatusException, NotFoundException;

	Order cancelOrder(Long orderId) throws WrongStatusException, NotFoundException;

}
