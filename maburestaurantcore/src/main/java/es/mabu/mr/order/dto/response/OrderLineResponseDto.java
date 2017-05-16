package es.mabu.mr.order.dto.response;

import java.math.BigDecimal;
import java.util.List;

import es.mabu.mr.order.dto.base.OrderLineBaseDto;
import es.mabu.mr.product.taxe.Taxe;

public class OrderLineResponseDto extends OrderLineBaseDto {

	private String productName;
	private String productImage;
	private List<OptionLinePriceResponseDto> optionLinePrices;

	public OrderLineResponseDto() {
		super();
	}

	public OrderLineResponseDto(Long id, Long productId, Taxe taxe, Integer quantity, BigDecimal price,
			BigDecimal totalPrice, List<OptionLinePriceResponseDto> optionLinePrices, String productName,
			String productImage) {
		super(id, productId, taxe, quantity, price, totalPrice);
		this.optionLinePrices = optionLinePrices;
		this.productImage = productImage;
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public List<OptionLinePriceResponseDto> getOptionLinePrices() {
		return optionLinePrices;
	}

	public void setOptionLinePrices(List<OptionLinePriceResponseDto> optionLines) {
		this.optionLinePrices = optionLines;
	}

}
