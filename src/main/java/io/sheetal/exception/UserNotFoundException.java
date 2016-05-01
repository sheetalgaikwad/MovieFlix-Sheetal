package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="User not found")
public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

}
