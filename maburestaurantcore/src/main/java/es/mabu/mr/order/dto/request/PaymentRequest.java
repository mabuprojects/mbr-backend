package es.mabu.mr.order.dto.request;

public class PaymentRequest {

	private Long orderId;

	private String tokenStripe;

	boolean savePaymentDetails;

	boolean useDefaultCard;

	public PaymentRequest() {
		super();
	}

	public PaymentRequest(Long orderId, String tokenStripe, boolean savePaymentDetails, boolean useDefaultCard) {
		super();
		this.orderId = orderId;
		this.tokenStripe = tokenStripe;
		this.savePaymentDetails = savePaymentDetails;
		this.useDefaultCard = useDefaultCard;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTokenStripe() {
		return tokenStripe;
	}

	public void setTokenStripe(String tokenStripe) {
		this.tokenStripe = tokenStripe;
	}

	public boolean isSavePaymentDetails() {
		return savePaymentDetails;
	}

	public void setSavePaymentDetails(boolean savePaymentDetails) {
		this.savePaymentDetails = savePaymentDetails;
	}

	public boolean isUseDefaultCard() {
		return useDefaultCard;
	}

	public void setUseDefaultCard(boolean useDefaultCard) {
		this.useDefaultCard = useDefaultCard;
	}

}
