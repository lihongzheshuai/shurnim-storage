package com.coderli.shurnim.storage.exception;

public class ShurnimException extends RuntimeException {

	private static final long serialVersionUID = 981707006026634156L;

	public ShurnimException() {
	}

	public ShurnimException(String message) {
		super(message);
	}

	public ShurnimException(String message, Throwable t) {
		super(message, t);
	}

}
