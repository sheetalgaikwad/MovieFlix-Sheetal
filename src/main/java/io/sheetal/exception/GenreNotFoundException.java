package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Genre not found")
public class GenreNotFoundException extends Exception {

	
	private static final long serialVersionUID = 5163355966478025210L;

}
