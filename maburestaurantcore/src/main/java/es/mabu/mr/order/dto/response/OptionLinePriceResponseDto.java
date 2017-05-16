package es.mabu.mr.order.dto.response;

import java.math.BigDecimal;

import es.mabu.mr.order.dto.base.OptionLinePriceBaseDto;

public class OptionLinePriceResponseDto extends OptionLinePriceBaseDto {

	private String optionName;

	private String chossenOptionName;

	private boolean main;

	public OptionLinePriceResponseDto(Long id, BigDecimal priceAdded, Long restaurantId, String optionName,
			String chossenOptionName, boolean main) {
		super(id, priceAdded, restaurantId);
		this.optionName = optionName;
		this.chossenOptionName = chossenOptionName;
		this.main = main;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String name) {
		this.optionName = name;
	}

	public String getChossenOptionName() {
		return chossenOptionName;
	}

	public void setChossenOptionName(String optionChossen) {
		this.chossenOptionName = optionChossen;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

}
