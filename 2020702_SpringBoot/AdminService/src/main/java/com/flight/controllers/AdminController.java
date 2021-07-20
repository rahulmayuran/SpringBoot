package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.Admin;
import com.flight.services.AdminService;

@RestController
@RequestMapping("/api/v1.0/flight")
public class AdminController {
	
	@Autowired
	AdminService AdminService;
	
	@GetMapping("/greet")
	public String greet() {
		System.out.println("in greet method");
		return "Welcome user";
	}
	
	//To get all the registered users from DB
	@GetMapping("/getUsers")
	public List<Admin> names() {
		System.out.println("Get all users");
		List<Admin> admins = AdminService.getAllUsers();
		return admins;
	}
	
	@PostMapping("/user/login")
	public Admin saveUser(Admin admin) {
		System.out.println("Save the user - "+admin);
		return AdminService.saveAdminUser(admin);
	}
	
}
