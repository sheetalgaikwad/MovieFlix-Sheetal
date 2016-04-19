package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Writer not found")
public class WriterNotFoundException extends Exception {

	private static final long serialVersionUID = -6286581876895786677L;
}
