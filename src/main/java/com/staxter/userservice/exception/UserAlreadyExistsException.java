package com.staxter.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends StaxterException {

	public UserAlreadyExistsException() {
		super("USER_ALREADY_EXISTS", "A user with the given username already exists");
	}

}
