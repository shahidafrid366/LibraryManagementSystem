package com.dxc.dao;

import com.dxc.pojos.Book;

public interface AdminDao {

	public void addBook(Book b);

	public void removeBook(int bId);

	public void removeUser(int uid);

	public boolean confirmAdmin(String id, String password);

	public void viewBookOfUser(int bookId);

	public void viewBalance(int uid);
	
	public void viewAllBooks();
	
	public void viewAllUsers();

}
