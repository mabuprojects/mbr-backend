package es.mabu.mr.order.exception;

import es.mabu.mr.exception.CustomBaseException;

public class ProductNotAvaliable extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4014037202357716028L;

	public ProductNotAvaliable(String message) {

		super(message);
	}

}
