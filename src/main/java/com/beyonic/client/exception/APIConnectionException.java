package com.beyonic.client.exception;

public class APIConnectionException extends Exception {
	private static final long serialVersionUID = -2021678130595872541L;
	private String message;
	public APIConnectionException() {
		super();
	}
	
	public APIConnectionException(String message) {
		super(message);
		this.message = message;
	}
	

}
