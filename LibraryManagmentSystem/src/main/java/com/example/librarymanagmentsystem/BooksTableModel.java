package com.example.librarymanagmentsystem;

public class BooksTableModel {
	private int bookId;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int quantity;

	public BooksTableModel(int bookId, String isbn, String title, String author, String publisher, int quantity) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.quantity = quantity;
	}

	public int getBookId() {
		return bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
