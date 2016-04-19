package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Writer Already Exists")
public class WriterAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = 5856641135991567644L;
	
}
