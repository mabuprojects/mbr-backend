package es.mabu.mr.product.option;

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

import es.mabu.mr.product.Product;
import es.mabu.mr.product.optionline.OptionLine;

@Entity(name = "productoption")
public class Option {
	@Id
	@GenericGenerator(name = "productoption_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "productoption_id_seq")

	})
	@GeneratedValue(generator = "productoption_sequence")
	private Long id;
	@NotNull
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "option")
	private List<OptionLine> optionLines;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	private Product product;

	private boolean main;

	public Option() {
	}

	public Long getId() {
		return id;
	}

	public Option(String name, Product product, boolean main) {
		this(name, new ArrayList<>(), product, main);
	}

	public Option(String name, List<OptionLine> optionLines, Product product, boolean main) {
		super();
		this.name = name;
		this.optionLines = optionLines;
		this.product = product;
		this.main = main;
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

	public List<OptionLine> getOptionLines() {
		return optionLines;
	}

	public void setOptionLines(List<OptionLine> optionLines) {
		this.optionLines = optionLines;
	}

	public List<OptionLine> removeOptionLine(OptionLine optionLine) {
		this.optionLines.remove(optionLine);
		return this.optionLines;
	}

	public void addOptionLine(OptionLine optionLine) {
		optionLines.add(optionLine);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (main ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((optionLines == null) ? 0 : optionLines.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Option other = (Option) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (main != other.main)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (optionLines == null) {
			if (other.optionLines != null)
				return false;
		} else if (!optionLines.equals(other.optionLines))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", name=" + name + ", optionLines=" + optionLines + ", product=" + product
				+ ", main=" + main + "]";
	}

}
