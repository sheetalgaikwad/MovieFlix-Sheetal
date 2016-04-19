package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Writer Role Already Exists")
public class WriterRoleAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8358112042482233616L;

	
	
}
