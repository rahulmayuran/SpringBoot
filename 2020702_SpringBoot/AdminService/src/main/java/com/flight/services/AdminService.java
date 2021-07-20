package com.flight.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entities.Admin;
import com.flight.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	//To fetch all Users from DB registered through angular and return them - JPA
	public List<Admin> getAllUsers(){
		return adminRepository.findAll();
	}
	
	//The Register button in angular should save it to DB.
	public Admin saveAdminUser(Admin admin) {
		return adminRepository.save(admin);
	}
}
