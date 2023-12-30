package com.future.error;

public class CustomException extends RuntimeException {

    public CustomException(){

    }

    public CustomException(Throwable cause){
        super(cause);
    }

    public CustomException(String message){
        super(message);
    }

    public CustomException(Throwable cause, String message){
        super(message, cause);
    }

}
