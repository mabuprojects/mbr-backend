package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class StripeCardException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127358083945290360L;

	public StripeCardException(String message) {

		super(message);
	}

}
