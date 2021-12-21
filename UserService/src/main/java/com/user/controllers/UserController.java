package com.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.Exception.UserException;
import com.user.entities.User;
import com.user.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin()
public class UserController {
	
	@Autowired
	UserService UserService;
	
	@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
		System.out.println("Save the user from Spring Boot - "+user);
		return UserService.saveUser(user);
	}

	@GetMapping("/getUsers")
	@ApiOperation(value = "Get all users",
		        notes = "Already registered for flight booking",
		        response = List.class)
	public List<User> names() throws UserException{
		System.out.println("Get all users");
		List<User> users = UserService.getAllUsers();
		return users;
	}
	
	//Both for Login & Register, This check must happen
	@GetMapping("/{name}")
	@ResponseBody
	public User getSingleUser(@PathVariable String name) throws UserException{
		System.out.println("Get SpringBoot user with name "+name);
		User user = UserService.getUserByName(name);
		return UserService.saveUser(user);
	}
}
