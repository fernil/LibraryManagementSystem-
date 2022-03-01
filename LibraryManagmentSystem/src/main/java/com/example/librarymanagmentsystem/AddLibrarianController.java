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

public class AddLibrarianController implements Initializable {

	@FXML
	private Button addButton; // adds librarian to database
	@FXML
	private Button backButton; // goes to adminSection scene
	@FXML
	private TextField loginTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField surnameTF;
	@FXML
	private TextField emailTF;
	@FXML
	private TextField phoneNumberTF;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "admin section/adminSection.fxml");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		});
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					DBUtils.addLibrarian(event, loginTF.getText(), passwordTF.getText(), nameTF.getText(),
							surnameTF.getText(), emailTF.getText(), phoneNumberTF.getText());

					loginTF.clear();
					passwordTF.clear();
					nameTF.clear();
					surnameTF.clear();
					emailTF.clear();
					phoneNumberTF.clear();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		});

	}
}