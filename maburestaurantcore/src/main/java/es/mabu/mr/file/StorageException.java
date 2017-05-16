package es.mabu.mr.file;

import es.mabu.mr.exception.CustomBaseException;

public class StorageException extends CustomBaseException {

	private static final long serialVersionUID = 8771046905707260251L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
