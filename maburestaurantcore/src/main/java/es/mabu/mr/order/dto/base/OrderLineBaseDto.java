package es.mabu.mr.order.dto.base;

import java.math.BigDecimal;

import es.mabu.mr.product.taxe.Taxe;

public class OrderLineBaseDto {

	private Long id;

	private Long productId;

	private Taxe taxe;

	private Integer quantity;

	private BigDecimal price;

	private BigDecimal totalPrice;

	public OrderLineBaseDto() {

	}

	public OrderLineBaseDto(Long id, Long productId, Taxe taxe, Integer quantity, BigDecimal price,
			BigDecimal totalPrice) {
		super();
		this.id = id;
		this.productId = productId;
		this.taxe = taxe;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Taxe getTaxe() {
		return taxe;
	}

	public void setTaxe(Taxe taxe) {
		this.taxe = taxe;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
