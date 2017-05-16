package es.mabu.mr.book;

import es.mabu.mr.exception.CustomBaseException;

public class CantBookException extends CustomBaseException {

	public CantBookException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5979224939991860629L;

}
