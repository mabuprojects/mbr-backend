package es.mabu.mr.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionResolverImpl implements ExceptionResolver {

	@Autowired
	ExceptionConfig config;

	public ExceptionResolverImpl() {
	}

	@Override
	public CustomReturnException getException(CustomBaseException customException) {
		return new CustomReturnException(customException,
				config.getExceptionId(customException.getClass().getSimpleName()));
	}

}
