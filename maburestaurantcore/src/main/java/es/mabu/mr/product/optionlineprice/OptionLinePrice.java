package es.mabu.mr.product.optionlineprice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.product.optionline.OptionLine;
import es.mabu.mr.restaurant.Restaurant;

@Entity(name = "optionline_restaurant")
public class OptionLinePrice {

	@Id
	@GenericGenerator(name = "optionline_restaurant_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "optionline_restaurant_id_seq")

	})
	@GeneratedValue(generator = "optionline_restaurant_sequence")
	private Long id;

	@NotNull
	@Column(name = "priceadded")
	private BigDecimal priceAdded;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "optionlineid")
	private OptionLine optionLine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurantid")
	private Restaurant restaurant;

	public OptionLinePrice() {
		super();
	}

	public OptionLinePrice(BigDecimal priceAdded, OptionLine optionLine, Restaurant restaurant) {
		super();
		this.priceAdded = priceAdded;
		this.optionLine = optionLine;
		this.restaurant = restaurant;
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

	public OptionLine getOptionLine() {
		return optionLine;
	}

	public void setOptionLine(OptionLine optionLine) {
		this.optionLine = optionLine;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((optionLine == null) ? 0 : optionLine.hashCode());
		result = prime * result + ((priceAdded == null) ? 0 : priceAdded.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
		OptionLinePrice other = (OptionLinePrice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (optionLine == null) {
			if (other.optionLine != null)
				return false;
		} else if (!optionLine.equals(other.optionLine))
			return false;
		if (priceAdded == null) {
			if (other.priceAdded != null)
				return false;
		} else if (!priceAdded.equals(other.priceAdded))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OptionLinePrice [id=" + id + ", priceAdded=" + priceAdded + ", optionLine=" + optionLine
				+ ", restaurant=" + restaurant + "]";
	}

}
