package com.user.Exception;

public class FlightException extends Exception{
	private static final long serialVersionUID = 1L;

	 public FlightException(){}
	    public FlightException(String msg){
	        super(msg);
	    }
	    public FlightException(Exception exp){
	        super(exp);
	    }
	    public FlightException(String msg, Exception exp){
	        super(msg,exp);
	    }
	
}
