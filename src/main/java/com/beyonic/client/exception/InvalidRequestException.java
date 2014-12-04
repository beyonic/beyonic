package com.beyonic.client.exception;

public class InvalidRequestException extends Exception {
	private static final long serialVersionUID = 7942610225843383624L;
	private String message;
	public InvalidRequestException() {
		super();
	}
	
	public InvalidRequestException(String message) {
		super(message);
		this.message = message;
	}
}
