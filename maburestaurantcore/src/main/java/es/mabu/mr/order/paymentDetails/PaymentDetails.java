package es.mabu.mr.order.paymentDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import es.mabu.mr.order.Order;

@Entity(name = "payment_details")
public class PaymentDetails {

	@Id
	@Column(name = "orderid")
	private Long orderId;

	@OneToOne
	@PrimaryKeyJoinColumn(name = "orderid", referencedColumnName = "payment_details_id")
	private Order order;

	private String system;

	@Column(name = "chargedid")
	private String chargedId;

	@Column(name = "createdcharge")
	private Timestamp createdCharge;

	private BigDecimal fee;

	private BigDecimal amount;

	private boolean refunded;

	@Column(name = "refundid")
	private String refundId;

	@Column(name = "createdrefund")
	private Timestamp createdRefund;

	public PaymentDetails() {
		super();
	}

	public PaymentDetails(Long orderId, Order order, String system, String chargedId, Timestamp createdCharge,
			BigDecimal fee, BigDecimal amount) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.system = system;
		this.chargedId = chargedId;
		this.createdCharge = createdCharge;
		this.fee = fee;
		this.amount = amount;
		this.refunded = false;
		this.refundId = null;
		this.createdRefund = null;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getChargedId() {
		return chargedId;
	}

	public void setChargedId(String chargedId) {
		this.chargedId = chargedId;
	}

	public Timestamp getCreatedCharge() {
		return createdCharge;
	}

	public void setCreatedCharge(Timestamp createdCharge) {
		this.createdCharge = createdCharge;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isRefunded() {
		return refunded;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Timestamp getCreatedRefund() {
		return createdRefund;
	}

	public void setCreatedRefund(Timestamp createdRefund) {
		this.createdRefund = createdRefund;
	}

}
