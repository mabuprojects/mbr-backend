package es.mabu.mr.order.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import es.mabu.mr.address.Address;
import es.mabu.mr.order.OrderStatus;
import es.mabu.mr.order.dto.base.OrderBaseDto;
import es.mabu.mr.order.dto.base.PaymentDetailsDto;
import es.mabu.mr.restaurant.RestaurantServiceType;

public class OrderResponseDto extends OrderBaseDto {
	private List<OrderLineResponseDto> orderLines;

	public OrderResponseDto() {
		super();
	}

	public OrderResponseDto(Long id, Long clientId, Address address, Long restaurantId,
			RestaurantServiceType serviceType, Timestamp estimatedPickupOrDeliveryTime, BigDecimal totalPrice,
			BigDecimal deliveryCharge, boolean cashOnDelivery, String clientNote, Timestamp created, Timestamp sent,
			OrderStatus status, List<OrderLineResponseDto> orderLines, PaymentDetailsDto paymentDetails) {
		super(id, clientId, address, restaurantId, serviceType, estimatedPickupOrDeliveryTime, totalPrice,
				deliveryCharge, cashOnDelivery, clientNote, created, sent, status, paymentDetails);
		this.orderLines = orderLines;
	}

	public List<OrderLineResponseDto> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLineResponseDto> orderLines) {
		this.orderLines = orderLines;
	}

}
