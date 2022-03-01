package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class LibrarianSectionController implements Initializable {

	@FXML
	private Button logoutButton; // goes to logIn scene
	@FXML
	private Button addBookButton; // goes to addBook scene
	@FXML
	private Button viewBooksButton; // goes to books table scene
	@FXML
	private Button issueBookButton; // goes to issueBook scene
	@FXML
	private Button viewIssuedBookButton; // goes to issued books table scene
	@FXML
	private Button returnBookButton; // goes to return book scene
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "logIn.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		addBookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/addBook.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		viewBooksButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/booksTable.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		issueBookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/issueBook.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		viewIssuedBookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/issuedTable.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		returnBookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/returnBook.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
