package es.mabu.mr.file.image;

import es.mabu.mr.exception.CustomBaseException;

public class NotAnImageException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8905469313691426116L;

	public NotAnImageException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAnImageException(Throwable cause) {
		super(cause);
	}

	public NotAnImageException(String message) {
		super(message);
	}

}
