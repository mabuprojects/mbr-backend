package es.mabu.mr.product.exception;

import es.mabu.mr.exception.CustomBaseException;

/**
 * Se lanza si hay dos o mas detalles de producto con el mismo restaurante
 * 
 * @author christian
 *
 */
public class DuplicatedProductDetailsInProductException extends CustomBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7090075337327555875L;

	public DuplicatedProductDetailsInProductException(String message) {

		super(message);
	}

}
