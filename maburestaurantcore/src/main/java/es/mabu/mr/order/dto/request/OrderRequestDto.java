package es.mabu.mr.order.dto.request;

import java.util.List;

import es.mabu.mr.address.Address;
import es.mabu.mr.restaurant.RestaurantServiceType;

public class OrderRequestDto {

	private Long clientId;

	private Address address;

	private Long restaurantId;

	private RestaurantServiceType serviceType;

	private boolean cashOnDelivery;

	private String clientNote;

	private List<OrderLineRequestDto> orderLines;

	public OrderRequestDto() {
		super();
	}

	public OrderRequestDto(Long clientId, Address address, Long restaurantId, RestaurantServiceType serviceType,
			boolean cashOnDelivery, String clientNote, List<OrderLineRequestDto> orderLines) {
		super();
		this.clientId = clientId;
		this.address = address;
		this.restaurantId = restaurantId;
		this.serviceType = serviceType;
		this.cashOnDelivery = cashOnDelivery;
		this.clientNote = clientNote;
		this.orderLines = orderLines;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public RestaurantServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(RestaurantServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isCashOnDelivery() {
		return cashOnDelivery;
	}

	public void setCashOnDelivery(boolean cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}

	public String getClientNote() {
		return clientNote;
	}

	public void setClientNote(String clientNote) {
		this.clientNote = clientNote;
	}

	public List<OrderLineRequestDto> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLineRequestDto> orderLines) {
		this.orderLines = orderLines;
	}

}
