package io.sheetal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Award not found")
public class AwardNotFoundException extends Exception {

	private static final long serialVersionUID = 2505783266619764262L;
}
