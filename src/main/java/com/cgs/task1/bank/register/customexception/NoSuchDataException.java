package com.cgs.task1.bank.register.customexception;

public class NoSuchDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchDataException() {
		super();
	}

	public NoSuchDataException(String message) {
		super(message);
	}

}
