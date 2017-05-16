package es.mabu.mr.product.optionline;

import java.util.List;

import es.mabu.mr.product.optionlineprice.OptionLinePriceDto;

public class OptionLineDto {

	private Long id;

	private String name;

	private List<OptionLinePriceDto> optionLinePrices;

	public OptionLineDto() {
		super();
	}

	public OptionLineDto(Long id, String name, List<OptionLinePriceDto> optionLinePrices) {
		super();
		this.id = id;
		this.name = name;
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

	public List<OptionLinePriceDto> getOptionLinePrices() {
		return optionLinePrices;
	}

	public void setOptionLinePrices(List<OptionLinePriceDto> optionLinePrices) {
		this.optionLinePrices = optionLinePrices;
	}

}
