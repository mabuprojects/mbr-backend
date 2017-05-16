package es.mabu.mr.order.dto.request;

import java.util.List;

import es.mabu.mr.order.dto.base.OrderLineBaseDto;

public class OrderLineRequestDto extends OrderLineBaseDto {

	private Long productId;

	private Integer quantity;

	private List<OrderLinePriceRequestDto> orderLinePrices;

	public OrderLineRequestDto() {
		super();
	}

	public OrderLineRequestDto(Long productId, Integer quantity, List<OrderLinePriceRequestDto> orderLinePrices) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.orderLinePrices = orderLinePrices;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<OrderLinePriceRequestDto> getOrderLinePrices() {
		return orderLinePrices;
	}

	public void setOrderLinePrices(List<OrderLinePriceRequestDto> orderLinePrices) {
		this.orderLinePrices = orderLinePrices;
	}

}
