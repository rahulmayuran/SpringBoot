package com.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.Exception.UserException;
import com.user.entities.User;
import com.user.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUserByName(String name) {
		return userRepository.getUserByUsername(name);
	}
	
	   public void dummyExceptionRaised() throws UserException {
	        try{
	            System.out.println("in service method");
	                userRepository.count(); // sqlException
	            } catch(NullPointerException e){
	                System.out.println("in service method, catch block");
	                throw new UserException(e);
	            }


	    }
}
