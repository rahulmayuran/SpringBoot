package com.user;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.user.Exception.UserException;
import com.user.entities.User;
import com.user.repositories.UserRepository;
import com.user.services.UserService;

@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	UserService userService;

	//
	@MockBean
	UserRepository userRepositoryTester;

	//A dummy Test
	@Test
	public void contextLoads() {
		Assertions.assertFalse(1==0);
	}
	
	//A Disabled Test
	@Test
	@Disabled
	public void testNames() {
		String firstName = "someone";
		String secondName = "noone";
		Assertions.assertEquals(firstName, secondName);
	}
	
	//Testing a functionality - List of Users
	@Test
	public void testAllUsers(){
		
		//Junit 5 Mockito
		Mockito
		//.when to check for repository methods
		.when(userRepositoryTester.findAll())
		//. thenReturn to check the expected result
			.thenReturn(Arrays.asList(
					new User("someone", "something","USER"),
					new User("nobody", "noone","ADMIN")
			));
		
		//trigger the service layer with an object
		List<User> users = userService.getAllUsers();
		//Assert it and compare with what you are expecting
		Assertions.assertSame(false, users.isEmpty());
	}
	
	//Testing User Service
		@Test
		public void testFetchNameFunction(){
			String name = "user";
			Mockito
				.when(userRepositoryTester.getUserByUsername(name))
					.thenReturn(
						new User("user", "something","USER")
						);
			User users = userService.getUserByName(name);
			Assertions.assertEquals(name, users.getUsername());
		}
	
	//Testing the custom exception
	@Test
	public void testException(){
		Mockito
			.when(userRepositoryTester.count())
			//.thenReturn is for normal methods, here it is thenThrow
			.thenThrow(new NullPointerException("I tested an exception."));

		//.assertThrows is to be used to check the class which is written for exception
		Assertions.assertThrows(
				UserException.class, ()->{
					userService.ExceptionRaisedFromService();
		});

	}
	//Testing the RestTemplate.
}
