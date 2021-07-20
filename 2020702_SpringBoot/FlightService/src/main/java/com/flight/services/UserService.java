package com.flight.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entities.User;
import com.flight.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	//To fetch all Users from DB registered through angular and return them - JPA
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//The Register button in angular should save it to DB.
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
