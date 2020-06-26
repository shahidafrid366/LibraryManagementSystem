package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDaoImpl implements UserDao {

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
	public boolean userLogin(String userId, String userPassword) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id, password from user where id=? and password=?");
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				System.out.println("User Login Failed !! Enter Correct credentials");
				System.out.println("-------------------------------");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void issueBook(int uid, int bid, int days) {

		int bookp = 0;
		int amt = 0;
		int sDay = days;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			while (rs.next()) {
				if (bid == rs.getInt(1)) {
					bookp = rs.getInt(1);
				}
			}
			if (bookp == bid) {
				if (days <= 15) {
					
					PreparedStatement pstmt = conn.prepareStatement("insert into bookIssued values(?,?,?)");
					pstmt.setInt(1, uid);
					pstmt.setInt(2, bid);
					pstmt.setInt(3, days);
					pstmt.execute();
					pstmt.close();

					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("select * from user");
					while (rs1.next()) {
						if (uid == rs1.getInt(1)) {
							amt = rs1.getInt(4);
							int amtDay = sDay * 5;
							amt = amt - amtDay;
						}
					}
					stmt1.close();

					PreparedStatement pst = conn.prepareStatement("update user set balance=? where id=?");
					pst.setInt(1, amt);
					pst.setInt(2, uid);
					pst.execute();
					pst.close();
					System.out.println("Sucessfully Book issued");
					System.out.println("--------------------------------------------------------------");

				} else {
					System.out.println("you can't take book for more than 15 days");
					System.out.println("-------------------------------");
				}
			} else {
				System.out.println("The book with this id `" + bid + "` is not present in library");
				System.out.println("-------------------------------");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void author(String name) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from books where author =?");
			pstmt.setString(1, name);
			boolean b = pstmt.execute();
			if (b) {
				ResultSet rs = pstmt.getResultSet();
				System.out.println("Book Id"+"\t"+"Name"+"\t"+"Authour"+"\t"+"Quantity");
				System.out.println("-------  ------ ------- --------");
				while (rs.next()) {	
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4));
				}
				System.out.println("-------------------------------");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewLibraryBalance(int uid) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select balance from user where id=?");
			pstmt.setInt(1, uid);
			boolean b = pstmt.execute();
			if (b) {
				ResultSet rs = pstmt.getResultSet();
				while (rs.next()) {
					System.out.println("-------------------------------");
					System.out.println("Available Balance in account `"+uid+"` is :" + rs.getString(1));
					System.out.println("-------------------------------");
				}
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
			System.out.println("Book Id - Book Name - Author - Quantity");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"-"+rs.getString(2)+"-"+rs.getString(3)+"-"+rs.getInt(4)); 
			}
			System.out.println("-------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
