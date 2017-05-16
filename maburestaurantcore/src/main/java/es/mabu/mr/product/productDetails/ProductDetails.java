package es.mabu.mr.product.productDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductState;
import es.mabu.mr.restaurant.Restaurant;

@Entity(name = "product_restaurant")
public class ProductDetails {

	@Id
	@GenericGenerator(name = "product_restaurant_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "product_restaurant_id_seq")

	})
	@GeneratedValue(generator = "product_restaurant_sequence")

	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productid")
	private Product product;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurantid")
	private Restaurant restaurant;

	@Enumerated(EnumType.STRING)
	private ProductState state;

	@Column(name = "mainpage")
	private Integer mainPage;

	private BigDecimal price;

	private Timestamp modified;

	private Timestamp created;

	public ProductDetails() {
	}

	public ProductDetails(Product product, Restaurant restaurant, ProductState state, Integer mainPage,
			BigDecimal price) {
		this(product, restaurant, state, mainPage, price, null, null);
	}

	public ProductDetails(Product product, Restaurant restaurant, ProductState state, Integer mainPage,
			BigDecimal price, Timestamp modified, Timestamp created) {
		super();
		this.product = product;
		this.restaurant = restaurant;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
		ProductDetails other = (ProductDetails) obj;
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
		if (state != other.state)
			return false;
		return true;
	}

}
