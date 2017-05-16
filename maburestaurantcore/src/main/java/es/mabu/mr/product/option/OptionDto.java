package es.mabu.mr.product.option;

import java.util.List;

import es.mabu.mr.product.optionline.OptionLineDto;

public class OptionDto {

	private Long id;

	private String name;

	private List<OptionLineDto> optionLines;

	private boolean main;

	public OptionDto() {
		super();
	}

	public OptionDto(Long id, String name, List<OptionLineDto> optionLines, boolean main) {
		super();
		this.id = id;
		this.name = name;
		this.optionLines = optionLines;
		this.main = main;
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

	public List<OptionLineDto> getOptionLines() {
		return optionLines;
	}

	public void setOptionLines(List<OptionLineDto> optionLines) {
		this.optionLines = optionLines;
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
		OptionDto other = (OptionDto) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "OptionDto [id=" + id + ", name=" + name + ", optionLines=" + optionLines + ", main=" + main + "]";
	}

}
