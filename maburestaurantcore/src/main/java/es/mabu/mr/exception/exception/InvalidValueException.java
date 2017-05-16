package es.mabu.mr.exception.exception;

import es.mabu.mr.exception.CustomBaseException;

public class InvalidValueException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3476407120197858252L;

	public InvalidValueException(String message) {

		super(message);
	}

}
