
package es.mabu.mr.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.product.category.Category;
import es.mabu.mr.product.option.Option;
import es.mabu.mr.product.productDetails.ProductDetails;
import es.mabu.mr.product.taxe.Taxe;

@Entity(name = "product")
public class Product {
	@Id
	@GenericGenerator(name = "product_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "product_id_seq")

	})
	@GeneratedValue(generator = "product_sequence")
	private Long id;

	@NotNull
	private String name;

	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid")
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taxeid")
	private Taxe taxe;

	private Timestamp created;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductDetails> productDetails;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Option> options;

	private String imageName;

	public Product() {
		super();
	}

	public Product(String name, String description, Category category, Taxe taxe) {
		this(name, description, category, taxe, null, new ArrayList<ProductDetails>(), new ArrayList<Option>());

	}

	public Product(String name, String description, Category category, Taxe taxe, Timestamp created) {
		this(name, description, category, taxe, created, new ArrayList<ProductDetails>(), new ArrayList<Option>());

	}

	public Product(String name, String description, Category category, Taxe taxe, Timestamp created,
			List<ProductDetails> productDetails, List<Option> options) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.taxe = taxe;
		this.created = created;
		this.productDetails = productDetails;
		this.options = options;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Taxe getTaxe() {
		return taxe;
	}

	public void setTaxe(Taxe taxe) {
		this.taxe = taxe;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public List<ProductDetails> addProductDetail(ProductDetails productDetail) {
		this.productDetails.add(productDetail);
		return this.productDetails;
	}

	public List<ProductDetails> removeProductDetail(ProductDetails productDetail) {
		this.productDetails.remove(productDetail);
		return this.productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<Option> addOption(Option option) {
		this.options.add(option);
		return this.options;
	}

	public List<Option> removeOption(Option option) {
		this.options.remove(option);
		return this.options;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((taxe == null) ? 0 : taxe.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taxe == null) {
			if (other.taxe != null)
				return false;
		} else if (!taxe.equals(other.taxe))
			return false;
		return true;
	}

}
