package com.si20103262.bank.exception;

public class InvalidAccountNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumberException() {
		super();
	}
	
	public InvalidAccountNumberException(String message ) {
		super(message);
	}
}
