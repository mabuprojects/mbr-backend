package es.mabu.mr.product.exception;

import es.mabu.mr.exception.CustomBaseException;

public class IncorrectProductOptionsException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4091277733403087884L;

	public IncorrectProductOptionsException(String message) {

		super(message);
	}

}
