package com.dxc.presentation;

import java.util.Scanner;

import com.dxc.pojos.Book;
import com.dxc.service.AdminService;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.UserService;
import com.dxc.service.UserServiceImpl;

public class Test {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		AdminService adminService = new AdminServiceImpl();
		UserService userService = new UserServiceImpl();

		while (true) {
			System.out.println("1. Login for Admin");
			System.out.println("2. Login for User");
			System.out.println("3. Exit");
			System.out.println("----------------------");
			System.out.println("Choose any option");
			String choice = s.next();

			if (choice.length() > 1) {
				System.out.println("you given value as string!! pls give int");
			} else if (Character.isLetter(choice.charAt(0))) {
				System.out.println("given value char");
			}

			else if (Integer.parseInt(choice) == 1) {

				System.out.println("Enter your admin id");
				String adminid = s.next();
				System.out.println("Enter your admin password");
				String password = s.next();

				boolean b = adminService.confirmAdmin(adminid, password);

				if (b == true) {
					while (true) {

						System.out.println("11. Add a Book");
						System.out.println("12. Remove Book");
						System.out.println("13. View Books Allcoated To Particular User");
						System.out.println("14. View Balance Amount Of Particular User");
						System.out.println("15. Delete User Account");
						System.out.println("16. View All Books From Library");
						System.out.println("17. View All Users From Library");
						System.out.println("18. Exit");
						System.out.println("-------------------------------");

						String adminChoice = s.next();
						if (adminChoice.length() > 2) {
							System.out.println("You have given alphabet as input!! Enter Correct option");
						} else if (Character.isLetter(adminChoice.charAt(0))) {
							System.out.println(
									"You have entered special character which is invalid. Enter Correct Option");

						} else if (Integer.parseInt(adminChoice) == 11) {
							System.out.println("Enter Book Id, Book Name, Authour, Quantity");
							adminService.addBook(new Book(s.nextInt(), s.next(), s.next(), s.nextInt()));

						} else if (Integer.parseInt(adminChoice) == 12) {
							System.out.println("Enter the Book Id you want to Remove from Library");
							int bId = s.nextInt();
							adminService.removeBook(bId);

						} else if (Integer.parseInt(adminChoice) == 13) {
							System.out.println("Enter the userid to view the books issued ");
							int bookId = s.nextInt();
							adminService.viewBookOfUser(bookId);

						} else if (Integer.parseInt(adminChoice) == 14) {
							System.out.println("Enter the User Id to view the balance amount of particular user");
							int uid = s.nextInt();
							adminService.viewBalance(uid);

						} else if (Integer.parseInt(adminChoice) == 15) {
							System.out.println("Enter the user id you want to delete");
							int id = s.nextInt();
							adminService.removeUser(id);

						} else if (Integer.parseInt(adminChoice) == 16) {
							adminService.viewAllBooks();

						} else if (Integer.parseInt(adminChoice) == 17) {
							adminService.viewAllUsers();

						} else if (Integer.parseInt(adminChoice) == 18) {
							System.exit(18);
						}

						else {
							System.out.println("You entered wrong choice !! Now Enter Correct choice");
							System.out.println("-------------------------------");

						}
					}
				}
			} else if (Integer.parseInt(choice) == 2) {

				System.out.println("Enter your user id");
				String userId = s.next();
				System.out.println("Enter your user password");
				String userPassword = s.next();

				boolean userb = userService.userLogin(userId, userPassword);

				if (userb == true) {
					while (true) {

						System.out.println("21. Issue Book");
						System.out.println("22. Get list of books by particular author");
						System.out.println("23. Check available balance in library account");
						System.out.println("24. View All Books From Library");
						System.out.println("25. Exit");
						System.out.println("-------------------------------");

						String userChoice = s.next();
						if (userChoice.length() > 2) {
							System.out.println("You have given alphabet as input!! Enter Correct option");
						} else if (Character.isLetter(userChoice.charAt(0))) {
							System.out.println(
									"You have entered special character which is invalid. Enter Correct Option");

						} else if (Integer.parseInt(userChoice) == 21) {
							System.out.println("Enter book id you want");
							int bid = s.nextInt();
							System.out.println("Enter no of days you want");
							int days = s.nextInt();
							int user = Integer.parseInt(userId);
							userService.issueBook(user, bid, days);

						} else if (Integer.parseInt(userChoice) == 22) {
							System.out.println("Enter author name you want to see");
							String name = s.next();
							userService.author(name);

						} else if (Integer.parseInt(userChoice) == 23) {
							userService.viewLibraryBalance(Integer.parseInt(userId));

						} else if (Integer.parseInt(userChoice) == 24) {
							userService.viewAllBooks();

						} else if (Integer.parseInt(userChoice) == 25) {
							System.exit(25);
						}
					}
				}

			}

			else if (Integer.parseInt(choice) == 3) {
				System.exit(3);
			} else {
				System.out.println("You entered wrong choice !! Now Enter Correct choice");
			}
		}
	}
}
