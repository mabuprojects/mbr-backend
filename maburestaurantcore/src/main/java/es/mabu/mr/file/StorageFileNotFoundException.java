package es.mabu.mr.file;

import es.mabu.mr.exception.CustomBaseException;

public class StorageFileNotFoundException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5614943933792880704L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
