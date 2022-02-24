package com.stock.market.User.Exception;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	 public UserException(){}
	    public UserException(String msg){
	        super(msg);
	    }
	    public UserException(Exception exp){
	        super(exp);
	    }
	    public UserException(String msg, Exception exp){
	        super(msg,exp);
	    }
	
}
