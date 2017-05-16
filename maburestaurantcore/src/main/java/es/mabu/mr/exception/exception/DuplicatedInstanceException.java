package es.mabu.mr.exception.exception;

import es.mabu.mr.exception.CustomBaseException;

public class DuplicatedInstanceException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459133188127207336L;

	public DuplicatedInstanceException(String message) {

		super(message);
	}

}
