package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.User;
import com.flight.services.UserService;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService UserService;
	
	@GetMapping("/greet")
	public String greet() {
		System.out.println("in greet method");
		return "Welcome user";
	}
	
	@GetMapping("/getUsers")
	public List<User> names() {
		System.out.println("Get all users");
		List<User> users = UserService.getAllUsers();
		return users;
	}
	
	//Both for Login & Register, This check must happen
	@GetMapping("/{name}")
	@ResponseBody
	public User getSingleUser(@PathVariable String name) {
		System.out.println("Get user with name "+name);
		User user = UserService.getUserByName(name);
		
		//Login to User page
		if(name == user.getUsername()) {
			System.out.println("User Matched in DB");
			return user;
		}
		//Register that user
		else
			return UserService.saveUser(user);
	}
	
	//For Registering User
	@PostMapping("/login")
	public User saveUser(User user) {
		System.out.println("Save the user - "+user);
		return UserService.saveUser(user);
	}
	
}
