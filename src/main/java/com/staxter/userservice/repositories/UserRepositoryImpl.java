package com.staxter.userservice.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.staxter.userservice.domain.User;
import com.staxter.userservice.exception.UserAlreadyExistsException;

@Component
public class UserRepositoryImpl implements UserRepository {

	private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();

	private static AtomicInteger lastUserId = new AtomicInteger(0);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {

		User userFound = users.get(user.getUserName());

		if (userFound != null)
			throw new UserAlreadyExistsException();

		user.setHashedPassword(bCryptPasswordEncoder.encode(user.getPlainTextPassword()));
		user.setId("" + lastUserId.incrementAndGet());

		users.put(user.getUserName(), user);

		return emptyAllCriticalData(user);
	}

	private User emptyAllCriticalData(User user) {
		return User.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.userName(user.getUserName()).build();

	}

	private User emptyOnlyPlainPass(User user) {
		return User.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.hashedPassword(user.getHashedPassword()).userName(user.getUserName()).build();

	}

	@Override
	public User getMemberByUsername(String username) {

		User result = users.get(username);

		if (result != null) {
			result = emptyOnlyPlainPass(result);
		}
		return result;
	}

}
