package es.mabu.mr.product.productDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;

import es.mabu.mr.product.ProductState;

public class ProductDetailsDto {

	private Long id;

	private Long restaurantId;

	private ProductState state;

	private Integer mainPage;

	private BigDecimal price;

	private Timestamp modified;

	private Timestamp created;

	public ProductDetailsDto() {
		super();
	}

	public ProductDetailsDto(Long id, Long restaurantId, ProductState state, Integer mainPage, BigDecimal price,
			Timestamp modified, Timestamp created) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.state = state;
		this.mainPage = mainPage;
		this.price = price;
		this.modified = modified;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}

	public Integer getMainPage() {
		return mainPage;
	}

	public void setMainPage(Integer mainPage) {
		this.mainPage = mainPage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainPage == null) ? 0 : mainPage.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		ProductDetailsDto other = (ProductDetailsDto) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainPage == null) {
			if (other.mainPage != null)
				return false;
		} else if (!mainPage.equals(other.mainPage))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductDetailsDto [id=" + id + ", restaurantId=" + restaurantId + ", state=" + state + ", mainPage="
				+ mainPage + ", price=" + price + ", modified=" + modified + ", created=" + created + "]";
	}

}
