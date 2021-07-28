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
		String firstName = "praveen";
		String secondName = "vicky";
		Assertions.assertEquals(firstName, secondName);
	}
	
	//Testing a functionality - List of Users
	@Test
	public void testAllUsers(){
		Mockito
		.when(userRepositoryTester.findAll())
		.thenReturn(Arrays.asList(
					new User("someone", "something","USER"),
					new User("nobody", "noone","ADMIN")
			));
		List<User> users = userService.getAllUsers();
		Assertions.assertSame(false, users.isEmpty());
	}
	
	//Testing the custom exception
	@Test
	public void testException(){
		Mockito
			.when(userRepositoryTester.count())
			.thenThrow(new NullPointerException("I tested an exception."));

		Assertions.assertThrows(
				UserException.class, ()->{
					userService.dummyExceptionRaised();
		});

	}
	//Testing the RestTemplate.
}
