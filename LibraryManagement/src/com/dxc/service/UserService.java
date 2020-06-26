package com.dxc.service;

public interface UserService {

	public boolean userLogin(String userId, String userPassword);

	public void issueBook(int uid, int bid, int days);

	public void viewLibraryBalance(int uid);

	public void author(String name);
	
	public void viewAllBooks();

}
