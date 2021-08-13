package com.user.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("+++++");
		System.out.println(username);
		System.out.println("+++++");

        // hard coded username
		if ("rahul".equals(username)) {
			return new User(
                "rahul", 
                // demo@123
                "$2a$10$xFrwKL8/BuG.ZVHITt6.E.9QWwuurrnC8ieK64Q02MEsgyeBr9jxu",
				new ArrayList<>()
            );
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}