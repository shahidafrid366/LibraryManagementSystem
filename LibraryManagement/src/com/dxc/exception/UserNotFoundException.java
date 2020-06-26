package com.dxc.exception;

public class UserNotFoundException extends  Exception{
	
	public UserNotFoundException() {
		super("Invalid User / User Is Not Present In Library");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
