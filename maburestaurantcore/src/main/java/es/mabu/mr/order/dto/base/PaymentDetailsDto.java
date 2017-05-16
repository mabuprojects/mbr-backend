package es.mabu.mr.order.dto.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import es.mabu.mr.order.paymentDetails.PaymentDetails;

public class PaymentDetailsDto {

	private String system;

	private String chargedId;

	private Timestamp createdCharge;

	private BigDecimal fee;

	private BigDecimal amount;

	private boolean refunded;

	private String refundId;

	private Timestamp createdRefund;

	public PaymentDetailsDto() {
		super();
	}

	public PaymentDetailsDto(PaymentDetails paymentDetails) {
		super();
		this.system = paymentDetails.getSystem();
		this.chargedId = paymentDetails.getChargedId();
		this.createdCharge = paymentDetails.getCreatedCharge();
		this.fee = paymentDetails.getFee();
		this.amount = paymentDetails.getAmount();
		this.refunded = paymentDetails.isRefunded();
		this.refundId = paymentDetails.getRefundId();
		this.createdRefund = paymentDetails.getCreatedRefund();
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
