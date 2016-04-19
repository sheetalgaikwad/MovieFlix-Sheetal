package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Language not found")
public class LanguageNotFoundException extends Exception {

	private static final long serialVersionUID = -2440181016500671807L;

}
