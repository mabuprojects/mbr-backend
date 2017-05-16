package es.mabu.mr.exception;

import org.springframework.data.rest.webmvc.support.ExceptionMessage;

/**
 * The Class CustomReturnException.
 */
public class CustomReturnException extends ExceptionMessage {

	/** The id. */
	private long id;

	/** The exception. */
	private final String exception;

	/**
	 * Instantiates a new custom return exception.
	 *
	 * @param exception
	 *            the exception
	 */
	public CustomReturnException(CustomBaseException exception, long id) {
		super(exception);
		this.id = id;
		this.exception = exception.getClass().getSimpleName();

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

}
