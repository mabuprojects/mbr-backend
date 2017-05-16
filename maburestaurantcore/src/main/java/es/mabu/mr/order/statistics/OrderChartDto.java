package es.mabu.mr.order.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderChartDto {

	private int month;
	private long sales;
	private BigDecimal money;

	public OrderChartDto() {
	}

	public OrderChartDto(BigInteger sales, BigDecimal money, Double month) {
		super();
		this.month = month.intValue();
		this.sales = sales.longValue();
		this.money = money;
	}

	public OrderChartDto(int sales, int money, int month) {
		super();
		this.month = month;
		this.sales = sales;
		this.money = new BigDecimal(money);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}