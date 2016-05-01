package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Actor not found")
public class ActorNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -288034372174272127L;


	

	
}
