package com.admin.Exceptions;

public class DiscountException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DiscountException() {
		// TODO Auto-generated constructor stub
	}
	
	public DiscountException(String msg) {
		super(msg);
	}

	public DiscountException(String msg, Exception e) {
		super(msg,e);
	}
}
