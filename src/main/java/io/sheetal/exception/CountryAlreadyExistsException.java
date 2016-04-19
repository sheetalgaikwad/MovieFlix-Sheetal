package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Country Already Exists")
public class CountryAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 2404151011929271234L;
}
