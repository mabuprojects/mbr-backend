package es.mabu.mr.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.exception.exception.NotFoundException;
import es.mabu.mr.order.dto.request.OrderRequestDto;
import es.mabu.mr.order.dto.request.PaymentRequest;
import es.mabu.mr.order.dto.response.OrderResponseDto;
import es.mabu.mr.order.exception.CashOnDeliveryNotAvaliable;
import es.mabu.mr.order.exception.OrderCanNotBePaidException;
import es.mabu.mr.order.exception.OrderHasNoProductsException;
import es.mabu.mr.order.exception.OutsideAddressRestaurantException;
import es.mabu.mr.order.exception.ProductNotAvaliable;
import es.mabu.mr.order.exception.RestaurantHasNotThisService;
import es.mabu.mr.order.exception.RestaurantNotAvaliableException;
import es.mabu.mr.order.exception.StripeCardException;
import es.mabu.mr.order.exception.WrongStatusException;
import es.mabu.mr.security.jwt.JwtTokenUtil;
import es.mabu.mr.security.jwt.JwtUser;
import es.mabu.mr.utils.OrderConversor;

@RestController
@RequestMapping("/order")
public class OrderController {

	/** The token header. */
	@Value("${jwt.header}")
	private String tokenHeader;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderConversor orderConversor;

	@RequestMapping(method = RequestMethod.POST, value = "/pay")
	OrderResponseDto payOrder(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request)
			throws StripeCardException, OrderCanNotBePaidException {

		return orderConversor.orderToOrderResponseDto(this.orderService.payOrder(paymentRequest, getUserId(request)));
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request)
			throws OrderHasNoProductsException, RestaurantHasNotThisService, RestaurantNotAvaliableException,
			CashOnDeliveryNotAvaliable, ProductNotAvaliable, OutsideAddressRestaurantException {

		/* Seteo el cliente en la order */
		orderRequestDto.setClientId(getUserId(request));

		return orderConversor.orderToOrderResponseDto(orderService.createOrder(orderRequestDto));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/client")
	List<OrderResponseDto> getOrdersByClient(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return orderConversor.orderToOrderResponseDto(orderService.findOrderByClient(user.getId()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{restaurantId}")
	List<OrderResponseDto> getRestaurantOrders(@PathVariable Long restaurantId) {
		return orderConversor.orderToOrderResponseDto(orderService.findOpenOrdersByRestaurant(restaurantId));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{orderId}/cook")
	OrderResponseDto startCooking(@PathVariable Long orderId) throws WrongStatusException, NotFoundException {
		return orderConversor.orderToOrderResponseDto(orderService.cookOrder(orderId));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{orderId}/send")
	OrderResponseDto sendToClient(@PathVariable Long orderId) throws WrongStatusException, NotFoundException {
		return orderConversor.orderToOrderResponseDto(orderService.sendOrder(orderId));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{orderId}/close")
	OrderResponseDto close(@PathVariable Long orderId) throws WrongStatusException, NotFoundException {
		return orderConversor.orderToOrderResponseDto(orderService.finishOrder(orderId));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{orderId}/cancel")
	OrderResponseDto cancel(@PathVariable Long orderId) throws WrongStatusException, NotFoundException {
		return orderConversor.orderToOrderResponseDto(orderService.cancelOrder(orderId));
	}

	/*
	 * Función auxiliar para obterner el id del cliente
	 */
	public Long getUserId(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user.getId();
	}
}
