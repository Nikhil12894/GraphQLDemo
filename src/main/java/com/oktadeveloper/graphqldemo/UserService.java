package com.oktadeveloper.graphqldemo;

import java.util.Optional;

import org.springframework.stereotype.Service;

import graphql.GraphQLException;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserService {

	private final UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GraphQLQuery(name = "userByEmail") // READ BY email
	public Optional<User> findByEmail(@GraphQLArgument(name = "email") String email) {
		return userRepo.findByEmail(email);
	}

	@GraphQLQuery(name = "userById") // READ BY ID
	public Optional<User> findById(@GraphQLArgument(name = "id") Long id) {
		return userRepo.findById(id);
	}

	@GraphQLMutation(name = "saveUser") // CREATE
	public User saveUser(@GraphQLArgument(name = "user") User user) {
		return userRepo.save(user);
	}

	@GraphQLMutation(name = "deleteUser") // DELETE
	public void deleteUser(@GraphQLArgument(name = "id") Long id) {
		userRepo.deleteById(id);
	}
	
}
