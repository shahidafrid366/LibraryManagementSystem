package com.dxc.service;

import com.dxc.dao.UserDao;
import com.dxc.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private UserDao dao = new UserDaoImpl();

	@Override
	public boolean userLogin(String userId, String userPassword) {
		return dao.userLogin(userId, userPassword);
	}

	@Override
	public void viewLibraryBalance(int uid) {
		dao.viewLibraryBalance(uid);
	}

	@Override
	public void author(String name) {
		dao.author(name);
	}

	@Override
	public void issueBook(int uid, int bid, int days) {
		dao.issueBook(uid, bid, days);
	}

	@Override
	public void viewAllBooks() {
		dao.viewAllBooks();
	}

}
