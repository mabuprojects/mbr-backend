package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class OrderCanNotBePaidException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5949233961442701087L;

	public OrderCanNotBePaidException(String message) {

		super(message);
	}

}
