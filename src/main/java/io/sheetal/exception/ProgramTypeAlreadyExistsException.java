package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Program Already Exists")
public class ProgramTypeAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = 8980312029542373334L;	
	
}
