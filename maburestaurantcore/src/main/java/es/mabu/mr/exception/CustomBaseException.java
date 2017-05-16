package es.mabu.mr.exception;

/**
 * The Class CustomBaseException.
 */
public abstract class CustomBaseException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8926291370366618619L;

	/**
	 * Instantiates a new custom base exception.
	 *
	 * @param message
	 *            the message
	 */
	public CustomBaseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new custom base exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public CustomBaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new custom base exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CustomBaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
