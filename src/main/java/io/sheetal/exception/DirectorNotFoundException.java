package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Director not found")
public class DirectorNotFoundException extends Exception {

	private static final long serialVersionUID = 6450282556205908023L;
}
