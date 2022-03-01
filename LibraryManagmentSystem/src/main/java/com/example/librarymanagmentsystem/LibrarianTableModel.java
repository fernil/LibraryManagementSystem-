package com.example.librarymanagmentsystem;

public class LibrarianTableModel {

	private int userId;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String number;

	public LibrarianTableModel(int userId, String login, String password, String name, String surname, String email,
			String number) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.number = number;
	}

	public LibrarianTableModel(int userId, String name, String surname) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
	}

	public int getUserId() {
		return userId;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getNumber() {
		return number;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
