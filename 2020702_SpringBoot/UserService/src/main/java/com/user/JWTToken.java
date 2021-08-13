package com.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JWTToken {

	//JWT tokens to be used to authenticate other services too.
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		
        String encodedString = passwordEncoder.encode("rahul");
        System.out.println("Encoded: "+encodedString);

        boolean test = passwordEncoder.matches("rahul", "$2a$10$V7GpMaSwJnkAnYW.BX.w0uDHdyFnuAWOb2atmELa4wj0iSTGXK4o6");
        System.out.println("Tested the encrypted and decrypted password "
        		+ "and it matched ? : "+test);
	}
}
