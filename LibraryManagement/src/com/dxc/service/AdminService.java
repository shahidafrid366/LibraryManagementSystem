package com.dxc.service;

import com.dxc.pojos.Book;

public interface AdminService {

	public void addBook(Book b);

	public void removeBook(int bId);

	public void removeUser(int id);

	public void viewBookOfUser(int bookId);

	public void viewBalance(int uid);

	public boolean confirmAdmin(String id, String password);
	
	public void viewAllBooks();
	
	public void viewAllUsers();

}
