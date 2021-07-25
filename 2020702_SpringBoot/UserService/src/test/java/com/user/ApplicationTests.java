package com.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.repositories.UserRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	UserRepository userRepositoryTester;
	
	//A dummy Test
	@Test
	public void contextLoads() {
		Assertions.assertTrue(userRepositoryTester.findAll().size() != 0);
	}
	
}
