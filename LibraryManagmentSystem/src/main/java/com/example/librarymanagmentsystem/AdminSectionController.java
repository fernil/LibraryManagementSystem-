package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminSectionController implements Initializable {

	@FXML
	private Button logoutButton; // goes to logIn scene
	@FXML
	private Button addButton; // goes to addLibrarian scene
	@FXML
	private Button viewButton; // goes to viewLibrarian scene
	@FXML
	private Button deleteButton; // goes to deleLibrarian scene

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

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "admin section/addLibrarian.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		viewButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "admin section/librarianTable.fxml");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.changeScene(event, "admin section/deleteLibrarian.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
