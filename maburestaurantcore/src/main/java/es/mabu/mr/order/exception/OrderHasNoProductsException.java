package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class OrderHasNoProductsException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2646769777774468708L;

	public OrderHasNoProductsException(String message) {

		super(message);
	}

}
