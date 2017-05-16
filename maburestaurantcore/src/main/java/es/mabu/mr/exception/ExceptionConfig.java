package es.mabu.mr.exception;

import java.util.HashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "es.mabu.mr")
class ExceptionConfig {

	private HashMap<String, HashMap<String, Long>> exception;

	public HashMap<String, HashMap<String, Long>> getException() {
		return exception;
	}

	public void setException(HashMap<String, HashMap<String, Long>> exception) {
		this.exception = exception;
	}

	private HashMap<String, Long> exceptions;

	public Long getExceptionId(String excetionClass) {

		return exceptions.get(excetionClass);
	}

	@PostConstruct
	public void flattProperties() {
		this.exceptions = (HashMap<String, Long>) this.exception.entrySet().stream()
				.flatMap(m -> m.getValue().entrySet().stream())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

	}

}