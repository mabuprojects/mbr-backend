package es.mabu.mr.order.orderline;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.order.Order;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.taxe.Taxe;

@Entity(name = "order_line")
public class OrderLine {

	@Id
	@GenericGenerator(name = "order_line_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "order_line_id_seq")

	})
	@GeneratedValue(generator = "order_line_sequence")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid")
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productid")
	private Product product;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "taxeid")
	private Taxe taxe;

	private Integer quantity;

	private BigDecimal price;

	@Column(name = "totalprice")
	private BigDecimal totalPrice;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "order_line_optionline_restaurant", joinColumns = @JoinColumn(name = "orderline_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "optionline_restaurant_id", referencedColumnName = "id"))
	private List<OptionLinePrice> optionLinePrices;

	public OrderLine() {
		super();
	}

	public OrderLine(Order order, Product product, Taxe taxe, Integer quantity, BigDecimal price, BigDecimal totalPrice,
			List<OptionLinePrice> optionLinePrices) {
		super();
		this.order = order;
		this.product = product;
		this.taxe = taxe;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.optionLinePrices = optionLinePrices;
	}

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public Taxe getTaxe() {
		return taxe;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public List<OptionLinePrice> getOptionLinePrices() {
		return optionLinePrices;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setTaxe(Taxe taxe) {
		this.taxe = taxe;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOptionLinePrices(List<OptionLinePrice> optionLinePrices) {
		this.optionLinePrices = optionLinePrices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((optionLinePrices == null) ? 0 : optionLinePrices.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((taxe == null) ? 0 : taxe.hashCode());
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
		OrderLine other = (OrderLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (optionLinePrices == null) {
			if (other.optionLinePrices != null)
				return false;
		} else if (!optionLinePrices.equals(other.optionLinePrices))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (taxe == null) {
			if (other.taxe != null)
				return false;
		} else if (!taxe.equals(other.taxe))
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
		return "OrderLine [id=" + id + ", order=" + order + ", product=" + product + ", taxe=" + taxe + ", quantity="
				+ quantity + ", price=" + price + ", totalPrice=" + totalPrice + ", optionLinePrices="
				+ optionLinePrices + "]";
	}

}
