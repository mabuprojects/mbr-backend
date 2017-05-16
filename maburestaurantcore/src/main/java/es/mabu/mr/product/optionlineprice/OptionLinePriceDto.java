package es.mabu.mr.product.optionlineprice;

import java.math.BigDecimal;

public class OptionLinePriceDto {

	private Long id;

	private BigDecimal priceAdded;

	private Long restaurantId;

	public OptionLinePriceDto() {
		super();
	}

	public OptionLinePriceDto(Long id, BigDecimal priceAdded, Long restaurantId) {
		super();
		this.id = id;
		this.priceAdded = priceAdded;
		this.restaurantId = restaurantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceAdded() {
		return priceAdded;
	}

	public void setPriceAdded(BigDecimal priceAdded) {
		this.priceAdded = priceAdded;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "OptionLinePriceDto [id=" + id + ", priceAdded=" + priceAdded + ", restaurantId=" + restaurantId + "]";
	}

}
