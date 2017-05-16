package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class RestaurantHasNotThisService extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5613140868143115701L;

	public RestaurantHasNotThisService(String message) {

		super(message);
	}

}
