package com.staxter.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaxterException extends RuntimeException {

	String code;
	String description;

}
