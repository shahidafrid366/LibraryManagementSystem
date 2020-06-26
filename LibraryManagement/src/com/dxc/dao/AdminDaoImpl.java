package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.dxc.pojos.Book;

public class AdminDaoImpl implements AdminDao {

	int bookId;
	String bookName;
	String authour;
	int quantity;
	String bAuthour;

	private static Connection conn;

	Scanner s = new Scanner(System.in);

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean confirmAdmin(String id, String password) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id, password from admin where id=? and password=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				System.out.println("Admin Login Failed !! Enter Correct credentials");
				System.out.println("-------------------------------");

				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addBook(Book b) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into books values(?,?,?,?)");
			pstmt.setInt(1, b.getBookid());
			pstmt.setString(2, b.getBookname());
			pstmt.setString(3, b.getAuthor());
			pstmt.setInt(4, b.getQuantity());
			pstmt.execute();
			System.out.println("Book added sucessfully");
			System.out.println("-------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeBook(int bId) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from books where bookId=?");
			pstmt.setInt(1, bId);
			int count = pstmt.executeUpdate();
			if (count > 0) {

				System.out.println("Book with id `" + bId + "` is Deleted");
				System.out.println("-------------------------------");
			} else {
				System.out.println("The book with id `" + bId + "` is not present in library");
				System.out.println("-------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewBookOfUser(int bookId) {

		PreparedStatement pstmt;
		String bookname = null;
		try {

			pstmt = conn.prepareStatement("select * from bookIssued where uid=?");
			pstmt.setInt(1, bookId);
			pstmt.execute();

			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				System.out.println("The user `" + bookId + "` has been issued below books");
				System.out.println("Book Name");
				System.out.println("-------------------------------");
				while (rs.next()) {
					Statement stmt = conn.createStatement();
					ResultSet rs1 = stmt.executeQuery("select * from books");

					while (rs1.next()) {
						if (rs.getInt(2) == rs1.getInt(1)) {
							bookname = rs1.getString(2);
							break;
						}
					}
					System.out.println(bookname);
				}
				System.out.println("-------------------------------");

			} else {
				System.out.println("Books has not been issued / user account is not present");
				System.out.println("-------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewBalance(int uid) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select balance from user where id=?");
			pstmt.setInt(1, uid);
			pstmt.execute();

			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				System.out.println("-------------------------------");
				System.out.println("Available Balance in user id : " + uid + " is :" + rs.getInt(1));
				System.out.println("-------------------------------");
			}

			else {
				System.out.println("User with id " + uid + " is not present");
				System.out.println("-------------------------------");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	@Override
	public void removeUser(int id) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("User With`" + id + "` Deleted");
				System.out.println("-------------------------------");

			} else {
				System.out.println("The user with id `" + id + "` is not present in library");
				System.out.println("-------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewAllBooks() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			System.out.println("Book Id" + "\t" + "Name" + "\t" + "Authour" + "\t" + "Quantity");
			System.out.println("-------  ------ ------- --------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4));
			}
			System.out.println("-------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void viewAllUsers() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			System.out.println("UserId" + "\t" + "Password" + " Name" + "  Amount");
			System.out.println("-------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t " + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			System.out.println("-------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
