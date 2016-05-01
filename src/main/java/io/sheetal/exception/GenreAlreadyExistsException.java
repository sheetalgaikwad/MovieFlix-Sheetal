package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Genre Already Exists")
public class GenreAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = -2766755178466876615L;

}
