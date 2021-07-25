package com.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entities.Admin;
import com.admin.services.AdminService;

@RestController
@RequestMapping("/api/v1.0/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	AdminService AdminService;
	
	//To get all the registered users from DB
	@GetMapping("/getUsers")
	public List<Admin> names() {
		System.out.println("Get all users");
		List<Admin> admins = AdminService.getAllUsers();
		return admins;
	}
	
	@PostMapping("/register/admin")
	public Admin saveUser(Admin admin) {
		System.out.println("Save the admin - "+admin);
		return AdminService.saveAdminUser(admin);
	}
	
}
