package es.mabu.mr.exception.exception;

import es.mabu.mr.exception.CustomBaseException;

public class NotValidMinPriceDeliveryException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7519811014001606223L;

	public NotValidMinPriceDeliveryException(String message) {
		super(message);
	}
}
