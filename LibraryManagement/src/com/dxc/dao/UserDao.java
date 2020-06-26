package com.dxc.dao;

public interface UserDao {

	public boolean userLogin(String userId, String userPassword);

	public void issueBook(int uid, int bid, int days);

	public void viewLibraryBalance(int uid);

	public void author(String name);

	public void viewAllBooks();
}
