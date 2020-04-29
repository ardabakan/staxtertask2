package com.staxter.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = { UserAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.CONFLICT) // 409
	public ExceptionResponse handleUserAlreadyExists(UserAlreadyExistsException exception) {
		return new ExceptionResponse(exception.getCode(), exception.getDescription());
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ExceptionResponse handleUserNotFound(UserNotFoundException exception) {
		return new ExceptionResponse(exception.getCode(), exception.getDescription());
	}

	@ExceptionHandler(value = { WrongUserCredentialsException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN) // 403
	public ExceptionResponse handleUserCredentialsWrong(WrongUserCredentialsException exception) {
		return new ExceptionResponse(exception.getCode(), exception.getDescription());
	}
}
