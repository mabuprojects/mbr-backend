package es.mabu.mr.product;

import java.sql.Timestamp;
import java.util.List;

import es.mabu.mr.product.category.Category;
import es.mabu.mr.product.option.OptionDto;
import es.mabu.mr.product.productDetails.ProductDetailsDto;
import es.mabu.mr.product.taxe.Taxe;

public class ProductDto {

	private Long id;

	private String name;

	private String description;

	private Category category;

	private Taxe taxe;

	private Timestamp created;

	private List<ProductDetailsDto> productDetails;

	private List<OptionDto> options;

	private String imageName;

	public ProductDto() {
		super();
	}

	public ProductDto(Long id, String name, String description, Category category, Taxe taxe, Timestamp created,
			List<ProductDetailsDto> productDetails, List<OptionDto> options, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.taxe = taxe;
		this.created = created;
		this.productDetails = productDetails;
		this.options = options;
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

	public List<ProductDetailsDto> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetailsDto> productDetails) {
		this.productDetails = productDetails;
	}

	public List<OptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<OptionDto> options) {
		this.options = options;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((productDetails == null) ? 0 : productDetails.hashCode());
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
		ProductDto other = (ProductDto) obj;
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
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (productDetails == null) {
			if (other.productDetails != null)
				return false;
		} else if (!productDetails.equals(other.productDetails))
			return false;
		if (taxe == null) {
			if (other.taxe != null)
				return false;
		} else if (!taxe.equals(other.taxe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", taxe=" + taxe + ", created=" + created + ", productDetails=" + productDetails + ", options="
				+ options + ", imageName=" + imageName + "]";
	}
}
