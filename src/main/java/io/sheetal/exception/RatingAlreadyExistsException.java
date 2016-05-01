package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Rating Already present")
public class RatingAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 7231800355167261346L;

}
