package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Language Already Exists")
public class LanguageAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = -5814727742267626190L;

}
