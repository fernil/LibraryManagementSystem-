package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ReturnBookController implements Initializable {

	@FXML
	private Button backButton;
	@FXML
	private TextField isbnTF;
	@FXML
	private TextField studentIDTF;
	
	
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

	}

}
