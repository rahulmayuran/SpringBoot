package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.User;
import com.flight.services.UserService;

@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {
	
	@Autowired
	UserService UserService;
	
	@GetMapping("/greet")
	public String greet() {
		System.out.println("in greet method");
		return "Welcome user";
	}
	
	//To get all the registered users from DB
	@GetMapping("/getUsers")
	public List<User> names() {
		System.out.println("Get all users");
		List<User> users = UserService.getAllUsers();
		return users;
	}
	
	@PostMapping("/user/login")
	public User saveUser(User user) {
		System.out.println("Save the user - "+user);
		return UserService.saveUser(user);
	}
	
}
