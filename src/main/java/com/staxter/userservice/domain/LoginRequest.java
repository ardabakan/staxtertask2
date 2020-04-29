package com.staxter.userservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

	private String userName;
	private String plainTextPassword;

}
