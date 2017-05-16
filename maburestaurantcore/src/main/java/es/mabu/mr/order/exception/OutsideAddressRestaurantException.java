package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class OutsideAddressRestaurantException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4159272198631917981L;

	public OutsideAddressRestaurantException(String message) {

		super(message);
	}

}
