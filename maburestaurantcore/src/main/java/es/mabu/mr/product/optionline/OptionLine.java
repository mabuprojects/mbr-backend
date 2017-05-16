package es.mabu.mr.product.optionline;

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

import es.mabu.mr.product.option.Option;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;

@Entity(name = "option_line")
public class OptionLine {
	@Id
	@GenericGenerator(name = "option_line_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "option_line_id_seq")

	})
	@GeneratedValue(generator = "option_line_sequence")
	private Long id;
	@NotNull
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "optionid")
	private Option option;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "optionLine")
	private List<OptionLinePrice> optionLinePrices;

	public OptionLine() {

	}

	public OptionLine(String name, Option option) {
		this(name, option, new ArrayList<>());

	}

	public OptionLine(String name, Option option, List<OptionLinePrice> optionLinePrices) {
		super();
		this.name = name;
		this.option = option;
		this.optionLinePrices = optionLinePrices;
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

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public List<OptionLinePrice> getOptionLinePrices() {
		return optionLinePrices;
	}

	public void setOptionLinePrices(List<OptionLinePrice> optionLinePrices) {
		this.optionLinePrices = optionLinePrices;
	}

	public void addOptionLinePrice(OptionLinePrice optionLinePrice) {
		optionLinePrices.add(optionLinePrice);
	}

	public List<OptionLinePrice> removeOptionLinePrice(OptionLinePrice optionLinePrice) {
		this.optionLinePrices.remove(optionLinePrice);
		return this.optionLinePrices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((option == null) ? 0 : option.hashCode());
		result = prime * result + ((optionLinePrices == null) ? 0 : optionLinePrices.hashCode());
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
		OptionLine other = (OptionLine) obj;
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
		if (option == null) {
			if (other.option != null)
				return false;
		} else if (!option.equals(other.option))
			return false;
		if (optionLinePrices == null) {
			if (other.optionLinePrices != null)
				return false;
		} else if (!optionLinePrices.equals(other.optionLinePrices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OptionLine [id=" + id + ", name=" + name + ", option=" + option + ", optionLinePrices="
				+ optionLinePrices + "]";
	}

}
