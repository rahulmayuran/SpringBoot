package com.flight.Exception;

public class BookingException extends Exception {

	private static final long serialVersionUID = 1L;

	 public BookingException(){}
	    public BookingException(String msg){
	        super(msg);
	    }
	    public BookingException(Exception exp){
	        super(exp);
	    }
	    public BookingException(String msg, Exception exp){
	        super(msg,exp);
	    }
}
