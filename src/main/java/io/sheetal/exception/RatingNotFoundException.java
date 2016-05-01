package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Rating not found")
public class RatingNotFoundException extends Exception {

	private static final long serialVersionUID = -358624502136170323L;

}
