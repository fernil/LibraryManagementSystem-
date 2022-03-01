package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController implements Initializable {

	@FXML
	private Button addBookButton;
	@FXML
	private Button backButton;
	@FXML
	private TextField isbnTF;
	@FXML
	private TextField titleTF;
	@FXML
	private TextField authorTF;
	@FXML
	private TextField publisherTF;
	@FXML
	private TextField quantityTF;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "librarian section/librarianSection.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		addBookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					DBUtils.addBook(event, isbnTF.getText(), titleTF.getText(), authorTF.getText(),
							publisherTF.getText(), Integer.parseInt(quantityTF.getText()));

					isbnTF.clear();
					titleTF.clear();
					authorTF.clear();
					publisherTF.clear();
					quantityTF.clear();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

}
