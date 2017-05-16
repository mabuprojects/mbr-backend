package es.mabu.mr.user.exception;

import es.mabu.mr.exception.CustomBaseException;

public class InvalidUserException extends CustomBaseException {

	private static final long serialVersionUID = -7435389496417602799L;

	public InvalidUserException(String message) {
		super("User not valid" + message);
	}

	public InvalidUserException() {
		this("");
	}

}
