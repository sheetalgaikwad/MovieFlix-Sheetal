package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Writer role not found")
public class WriterRoleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 94600068887757403L;

	
}
