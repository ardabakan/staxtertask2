package com.staxter.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staxter.userservice.domain.LoginRequest;
import com.staxter.userservice.domain.User;
import com.staxter.userservice.exception.UserAlreadyExistsException;
import com.staxter.userservice.exception.UserNotFoundException;
import com.staxter.userservice.exception.WrongUserCredentialsException;
import com.staxter.userservice.repositories.UserRepository;

@RestController
@RequestMapping("/userservice")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		logger.info(user.getFirstName() + " " + user.getLastName() + " create request started");

		if (userRepository.getMemberByUsername(user.getUserName()) != null) {

			throw new UserAlreadyExistsException();
		}

		User savedMember = userRepository.createUser(user);

		logger.info(user.getFirstName() + " " + user.getLastName() + " create request ended");

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

		logger.info(loginRequest.getUserName() + " login request started");

		User foundUser = userRepository.getMemberByUsername(loginRequest.getUserName());

		if (foundUser == null) {

			throw new UserNotFoundException();
		}

		if (!passwordEncoder.matches(loginRequest.getPlainTextPassword(), foundUser.getHashedPassword())) {
			throw new WrongUserCredentialsException();
		}

		logger.info(loginRequest.getUserName() + " login request ended");

		return ResponseEntity.status(HttpStatus.OK).body(foundUser);
	}

}
