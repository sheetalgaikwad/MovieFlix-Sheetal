package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Actor Already Exists")
public class ActorAlreadyExistsException extends Exception {


	private static final long serialVersionUID = -4560625019663040387L;
	
	
}
