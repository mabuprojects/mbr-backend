package es.mabu.mr.exception;

public interface ExceptionResolver {

	CustomReturnException getException(CustomBaseException exception);

}
