package com.staxter.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class WrongUserCredentialsException extends StaxterException {

	public WrongUserCredentialsException() {
		super("USER_CREDENTIALS_WRONG", "Provided credentials are wrong for login");
	}

}
