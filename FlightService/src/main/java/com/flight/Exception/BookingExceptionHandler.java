package com.flight.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler {
	
	public class UserExceptionHandler {
	    
	    //204 error
	    @ExceptionHandler(value = BookingException.class)
	    public ResponseEntity<CustomMessage> handleUserException(Exception e)
	    {
	        return ResponseEntity.status(200).body(new CustomMessage(204, e.getMessage()));
	    }
	}

}
