package com.user.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAndFlightExceptionHandler {

	//500
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomMessage> handleException(Exception e)
    {
        return ResponseEntity.status(200).body(new CustomMessage(204, e.getMessage()));
    }
    
    //204 error
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<CustomMessage> handleUserException(Exception e)
    {
        return ResponseEntity.status(200).body(new CustomMessage(204, e.getMessage()));
    }
}
