package es.mabu.mr.product.exception;

import es.mabu.mr.exception.CustomBaseException;

public class CategoryHasProductsException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -377724109370580366L;

	public CategoryHasProductsException(String message) {

		super(message);
	}

}
