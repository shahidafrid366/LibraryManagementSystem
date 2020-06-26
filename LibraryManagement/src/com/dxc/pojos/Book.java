package com.dxc.pojos;

public class Book {

	private int bookid;
	private String bookname;
	private String author;
	private int quantity;

	public Book() {

	}

	public void display() {
		System.out.println(bookid + " " + bookname + " " + author + " " + quantity);
	}

	public Book(int bookid, String bookname, String author, int quantity) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.author = author;
		this.quantity = quantity;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", bookname=" + bookname + ", author=" + author + ", quantity=" + quantity
				+ "]";
	}

}
