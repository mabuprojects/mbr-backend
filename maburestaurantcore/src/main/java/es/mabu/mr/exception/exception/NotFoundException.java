package es.mabu.mr.exception.exception;

import es.mabu.mr.exception.CustomBaseException;

public class NotFoundException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459133188127207336L;

	public NotFoundException(String message) {

		super(message);
	}

}
