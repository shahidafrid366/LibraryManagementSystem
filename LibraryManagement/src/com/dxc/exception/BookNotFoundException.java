package com.dxc.exception;

public class BookNotFoundException extends Exception {

	public BookNotFoundException() {
		super("Oops ! Book Not Found.. Enter the Correct Book Id");
	}
	
	public BookNotFoundException(String message) {
		super(message);
	}
}
