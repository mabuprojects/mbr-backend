package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;
import es.mabu.mr.order.OrderStatus;

public class WrongStatusException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6540775548973188469L;

	private final OrderStatus excepted;

	private final OrderStatus found;

	public WrongStatusException(OrderStatus expected, OrderStatus found) {
		super("Status expected " + expected.toString() + " but found " + found.toString());
		this.excepted = expected;
		this.found = found;
	}

	public OrderStatus getFound() {
		return found;
	}

	public OrderStatus getExcepted() {
		return excepted;
	}

}
