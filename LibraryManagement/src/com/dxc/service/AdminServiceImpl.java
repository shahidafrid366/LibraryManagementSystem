package com.dxc.service;

import com.dxc.dao.AdminDao;
import com.dxc.dao.AdminDaoImpl;
import com.dxc.pojos.Book;

public class AdminServiceImpl implements AdminService {

	private AdminDao dao = new AdminDaoImpl();

	@Override
	public void addBook(Book b) {
		dao.addBook(b);
	}

	@Override
	public void removeBook(int bId) {
		dao.removeBook(bId);
	}

	@Override
	public void removeUser(int id) {
		dao.removeUser(id);
	}

	@Override
	public boolean confirmAdmin(String id, String password) {
		return dao.confirmAdmin(id, password);
	}

	@Override
	public void viewBookOfUser(int bookId) {
		dao.viewBookOfUser(bookId);
	}

	@Override
	public void viewBalance(int uid) {
		dao.viewBalance(uid);
	}

	@Override
	public void viewAllBooks() {
		dao.viewAllBooks();
	}

	@Override
	public void viewAllUsers() {
		dao.viewAllUsers();
	}

}
