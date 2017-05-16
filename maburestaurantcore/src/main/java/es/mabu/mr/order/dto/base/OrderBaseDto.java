package es.mabu.mr.order.dto.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import es.mabu.mr.address.Address;
import es.mabu.mr.order.OrderStatus;
import es.mabu.mr.restaurant.RestaurantServiceType;

public abstract class OrderBaseDto {

	private Long id;

	private Long clientId;

	private Address address;

	private Long restaurantId;

	private RestaurantServiceType serviceType;

	private Timestamp estimatedPickupOrDeliveryTime;

	private BigDecimal totalPrice;

	private BigDecimal deliveryCharge;

	private boolean cashOnDelivery;

	private String clientNote;

	private Timestamp created;

	private Timestamp sent;

	private OrderStatus status;

	private PaymentDetailsDto paymentDetails;

	public OrderBaseDto() {
		super();
	}

	public OrderBaseDto(Long id, Long clientId, Address address, Long restaurantId, RestaurantServiceType serviceType,
			Timestamp estimatedPickupOrDeliveryTime, BigDecimal totalPrice, BigDecimal deliveryCharge,
			boolean cashOnDelivery, String clientNote, Timestamp created, Timestamp sent, OrderStatus status,
			PaymentDetailsDto paymentDetails) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.address = address;
		this.restaurantId = restaurantId;
		this.serviceType = serviceType;
		this.estimatedPickupOrDeliveryTime = estimatedPickupOrDeliveryTime;
		this.totalPrice = totalPrice;
		this.deliveryCharge = deliveryCharge;
		this.cashOnDelivery = cashOnDelivery;
		this.clientNote = clientNote;
		this.created = created;
		this.sent = sent;
		this.status = status;
		this.paymentDetails = paymentDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getEstimatedPickupOrDeliveryTime() {
		return estimatedPickupOrDeliveryTime;
	}

	public void setEstimatedPickupOrDeliveryTime(Timestamp estimatedPickupOrDeliveryTime) {
		this.estimatedPickupOrDeliveryTime = estimatedPickupOrDeliveryTime;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
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

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getSent() {
		return sent;
	}

	public void setSent(Timestamp sent) {
		this.sent = sent;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public PaymentDetailsDto getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetailsDto paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (cashOnDelivery ? 1231 : 1237);
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((clientNote == null) ? 0 : clientNote.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((deliveryCharge == null) ? 0 : deliveryCharge.hashCode());
		result = prime * result
				+ ((estimatedPickupOrDeliveryTime == null) ? 0 : estimatedPickupOrDeliveryTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paymentDetails == null) ? 0 : paymentDetails.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((sent == null) ? 0 : sent.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBaseDto other = (OrderBaseDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cashOnDelivery != other.cashOnDelivery)
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientNote == null) {
			if (other.clientNote != null)
				return false;
		} else if (!clientNote.equals(other.clientNote))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (deliveryCharge == null) {
			if (other.deliveryCharge != null)
				return false;
		} else if (!deliveryCharge.equals(other.deliveryCharge))
			return false;
		if (estimatedPickupOrDeliveryTime == null) {
			if (other.estimatedPickupOrDeliveryTime != null)
				return false;
		} else if (!estimatedPickupOrDeliveryTime.equals(other.estimatedPickupOrDeliveryTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paymentDetails == null) {
			if (other.paymentDetails != null)
				return false;
		} else if (!paymentDetails.equals(other.paymentDetails))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (sent == null) {
			if (other.sent != null)
				return false;
		} else if (!sent.equals(other.sent))
			return false;
		if (serviceType != other.serviceType)
			return false;
		if (status != other.status)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderBaseDto [id=" + id + ", clientId=" + clientId + ", address=" + address + ", restaurantId="
				+ restaurantId + ", serviceType=" + serviceType + ", estimatedPickupOrDeliveryTime="
				+ estimatedPickupOrDeliveryTime + ", totalPrice=" + totalPrice + ", deliveryCharge=" + deliveryCharge
				+ ", cashOnDelivery=" + cashOnDelivery + ", clientNote=" + clientNote + ", created=" + created
				+ ", sent=" + sent + ", status=" + status + ", paymentDetails=" + paymentDetails + "]";
	}

}
