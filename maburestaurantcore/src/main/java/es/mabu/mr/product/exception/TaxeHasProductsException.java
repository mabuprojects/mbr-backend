package es.mabu.mr.product.exception;

import es.mabu.mr.exception.CustomBaseException;

public class TaxeHasProductsException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257187150192909204L;

	public TaxeHasProductsException(String message) {

		super(message);
	}

}
