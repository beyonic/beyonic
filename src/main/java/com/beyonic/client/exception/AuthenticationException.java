package com.beyonic.client.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = -6257654490161245331L;
	private String message;
	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String message) {
		super(message);
		this.message = message;
	}
	
}
