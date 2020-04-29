package com.staxter.userservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String plainTextPassword;
	private String hashedPassword;

}
