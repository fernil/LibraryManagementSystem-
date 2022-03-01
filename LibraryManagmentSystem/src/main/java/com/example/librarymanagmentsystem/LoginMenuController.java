package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginMenuController implements Initializable {

	@FXML
	private Button loginButton;
	@FXML
	private Button exitButton;
	@FXML
	private TextField loginTF;
	@FXML
	private PasswordField passwordF;
	@FXML
	private Scene scene;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.logIn(event, loginTF.getText(), passwordF.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) exitButton.getScene().getWindow();
				stage.close();
			}
		});

	}

}
