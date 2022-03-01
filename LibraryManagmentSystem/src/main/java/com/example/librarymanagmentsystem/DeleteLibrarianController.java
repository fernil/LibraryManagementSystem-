package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteLibrarianController implements Initializable {

	@FXML
	private TableView<LibrarianTableModel> table;
	@FXML
	private TableColumn<LibrarianTableModel, Integer> userIdColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> nameColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> surnameColumn;
	@FXML
	private Button backButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TextField idTF;

	private ObservableList<LibrarianTableModel> observerList = FXCollections.observableArrayList();
	private Connection connection;
	private ResultSet resultSet;

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

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					DBUtils.deleteLibrarian(event, Integer.parseInt(idTF.getText()));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			resultSet = connection.createStatement()
					.executeQuery("select user_id, name, surname from librarian WHERE admin = 0");
			while (resultSet.next()) {
				observerList.add(new LibrarianTableModel(resultSet.getInt("user_id"), resultSet.getString("name"),
						resultSet.getString("surname")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
			table.setItems(observerList);

		}
	}
}
