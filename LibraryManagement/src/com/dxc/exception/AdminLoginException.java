package com.dxc.exception;

public class AdminLoginException extends Exception {

	public AdminLoginException(){
		super("Invalid Credentials / Admin Is Not Present In Library");
	}
	
	public AdminLoginException(String message) {
		super(message);
	}
}
