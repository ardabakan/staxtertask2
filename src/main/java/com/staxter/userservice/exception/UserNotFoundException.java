package com.staxter.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends StaxterException {

	public UserNotFoundException() {
		super("USER_ALREADY_EXISTS", "A user with the given username already exists");
	}
}
