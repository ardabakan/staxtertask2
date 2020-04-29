package com.staxter.userservice.repositories;

import org.springframework.stereotype.Repository;

import com.staxter.userservice.domain.User;
import com.staxter.userservice.exception.UserAlreadyExistsException;

@Repository
public interface UserRepository {

	User createUser(User user) throws UserAlreadyExistsException;

	User getMemberByUsername(String username);
}