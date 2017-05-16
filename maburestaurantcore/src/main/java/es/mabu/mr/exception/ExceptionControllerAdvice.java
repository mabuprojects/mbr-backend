package es.mabu.mr.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.ExceptionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ExceptionControllerAdvice. Generates the exception response for the
 * application based exceptions
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	ExceptionResolver resolver;

	/**
	 * Exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(value = CustomBaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> exception(CustomBaseException exception) {
		return errorResponse(exception, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Error response.
	 *
	 * @param exception
	 *            the exception
	 * @param status
	 *            the status
	 * @return the response entity
	 */
	protected ResponseEntity<ExceptionMessage> errorResponse(CustomBaseException exception, HttpStatus status) {
		if (null != exception) {
			return response(resolver.getException(exception), status);
		} else {
			return response(null, status);
		}
	}

	/**
	 * Response.
	 *
	 * @param <T>
	 *            the generic type
	 * @param body
	 *            the body
	 * @param status
	 *            the status
	 * @return the response entity
	 */
	protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
		return new ResponseEntity<T>(body, new HttpHeaders(), status);
	}
}
