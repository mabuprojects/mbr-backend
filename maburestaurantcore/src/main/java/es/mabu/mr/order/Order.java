package es.mabu.mr.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.address.Address;
import es.mabu.mr.order.orderline.OrderLine;
import es.mabu.mr.order.paymentDetails.PaymentDetails;
import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.restaurant.RestaurantServiceType;
import es.mabu.mr.user.client.Client;

/* Si cambias el nombre de la tabla cambiar la referencia en el 
 * método findOpenOrdersByRestaurant de OrderRepository*/
@Entity(name = "orderr")
public class Order {

	@Id
	@GenericGenerator(name = "order_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "order_id_seq")

	})
	@GeneratedValue(generator = "order_sequence")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientid")
	private Client client;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "addressid")
	private Address address;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurantid")
	private Restaurant restaurant;

	@Enumerated(EnumType.STRING)
	@Column(name = "servicetype")
	private RestaurantServiceType serviceType;

	@Column(name = "estimated_pickup_or_delivery_time")
	private Timestamp estimatedPickupOrDeliveryTime;

	@Column(name = "totalprice")
	private BigDecimal totalPrice;

	@Column(name = "delivery_charge")
	private BigDecimal deliveryCharge;

	@Column(name = "cash_on_delivery")
	private boolean cashOnDelivery;

	@Column(name = "client_note")
	private String clientNote;

	private BigDecimal discount;

	@Enumerated(EnumType.STRING)
	@Column(name = "discountype")
	private DiscountType discountType;

	private Timestamp created;

	private Timestamp sent;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderLine> orderLines;

	@OneToOne(mappedBy = "order")
	private PaymentDetails paymentDetails;

	public Order() {
		super();
	}

	public Order(Client client, Address address, Restaurant restaurant, RestaurantServiceType serviceType,
			Timestamp estimatedPickupOrDeliveryTime, BigDecimal totalPrice, BigDecimal deliveryCharge,
			boolean cashOnDelivery, String clientNote, BigDecimal discount, DiscountType discountType,
			Timestamp created, Timestamp sent, OrderStatus status, List<OrderLine> orderLines) {
		super();
		this.client = client;
		this.address = address;
		this.restaurant = restaurant;
		this.serviceType = serviceType;
		this.estimatedPickupOrDeliveryTime = estimatedPickupOrDeliveryTime;
		this.totalPrice = totalPrice;
		this.deliveryCharge = deliveryCharge;
		this.cashOnDelivery = cashOnDelivery;
		this.clientNote = clientNote;
		this.discount = discount;
		this.discountType = discountType;
		this.created = created;
		this.sent = sent;
		this.status = status;
		this.orderLines = orderLines;
	}

	public Long getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public Address getAddress() {
		return address;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public RestaurantServiceType getServiceType() {
		return serviceType;
	}

	public Timestamp getEstimatedPickupOrDeliveryTime() {
		return estimatedPickupOrDeliveryTime;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public BigDecimal getDeliveryCharge() {
		return deliveryCharge;
	}

	public boolean getCashOnDelivery() {
		return cashOnDelivery;
	}

	public String getClientNote() {
		return clientNote;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public Timestamp getCreated() {
		return created;
	}

	public Timestamp getSent() {
		return sent;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setServiceType(RestaurantServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public void setEstimatedPickupOrDeliveryTime(Timestamp estimatedPickupOrDeliveryTime) {
		this.estimatedPickupOrDeliveryTime = estimatedPickupOrDeliveryTime;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public void setCashOnDelivery(boolean cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}

	public void setClientNote(String clientNote) {
		this.clientNote = clientNote;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public void setSent(Timestamp sent) {
		this.sent = sent;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (cashOnDelivery ? 1231 : 1237);
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((clientNote == null) ? 0 : clientNote.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((deliveryCharge == null) ? 0 : deliveryCharge.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((discountType == null) ? 0 : discountType.hashCode());
		result = prime * result
				+ ((estimatedPickupOrDeliveryTime == null) ? 0 : estimatedPickupOrDeliveryTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cashOnDelivery != other.cashOnDelivery)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
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
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (discountType != other.discountType)
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
		if (orderLines == null) {
			if (other.orderLines != null)
				return false;
		} else if (!orderLines.equals(other.orderLines))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
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
		return "Order [id=" + id + ", client=" + client + ", address=" + address + ", restaurant=" + restaurant
				+ ", serviceType=" + serviceType + ", estimatedPickupOrDeliveryTime=" + estimatedPickupOrDeliveryTime
				+ ", totalPrice=" + totalPrice + ", deliveryCharge=" + deliveryCharge + ", cashOnDelivery="
				+ cashOnDelivery + ", clientNote=" + clientNote + ", discount=" + discount + ", discountType="
				+ discountType + ", created=" + created + ", sent=" + sent + ", status=" + status + ", orderLines="
				+ orderLines + "]";
	}

}
