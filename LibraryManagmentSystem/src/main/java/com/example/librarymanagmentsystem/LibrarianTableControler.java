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
import javafx.scene.control.cell.PropertyValueFactory;

public class LibrarianTableControler implements Initializable {
	@FXML
	private TableView<LibrarianTableModel> table;
	@FXML
	private TableColumn<LibrarianTableModel, Integer> userIdColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> loginColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> paswordColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> nameColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> surnameColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> emailColumn;
	@FXML
	private TableColumn<LibrarianTableModel, String> numberColumn;
	@FXML
	private Button backButton;

	private ObservableList<LibrarianTableModel> observerList = FXCollections.observableArrayList();
	private Connection connection;
	private ResultSet resultSet;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			resultSet = connection.createStatement().executeQuery(
					"select user_id, login, password, name, surname, email, number FROM librarian WHERE admin = 0;");
			while (resultSet.next()) {
				observerList.add(new LibrarianTableModel(resultSet.getInt("user_id"), resultSet.getString("login"),
						resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("surname"),
						resultSet.getString("email"), resultSet.getString("number")));
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
			loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
			paswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
			table.setItems(observerList);

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
		}

	}
}
