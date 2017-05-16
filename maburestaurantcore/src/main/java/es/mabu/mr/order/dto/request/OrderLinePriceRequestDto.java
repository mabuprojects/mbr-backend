package es.mabu.mr.order.dto.request;

public class OrderLinePriceRequestDto {

	private Long id;

	public OrderLinePriceRequestDto() {
		super();
	}

	public OrderLinePriceRequestDto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
