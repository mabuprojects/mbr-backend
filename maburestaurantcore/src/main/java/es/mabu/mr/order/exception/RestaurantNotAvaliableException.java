package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class RestaurantNotAvaliableException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4446135228636403007L;

	public RestaurantNotAvaliableException(String message) {

		super(message);
	}

}
