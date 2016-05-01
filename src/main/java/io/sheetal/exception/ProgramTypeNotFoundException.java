package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Program not found")
public class ProgramTypeNotFoundException extends Exception {

	private static final long serialVersionUID = 2895899076253087287L;
	
}
