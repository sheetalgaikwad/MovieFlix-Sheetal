package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Director Already Exists")
public class DirectorAlreadyExistsException extends Exception {


	private static final long serialVersionUID = 4234204438551596966L;
	
}
