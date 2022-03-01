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

public class BooksTableController implements Initializable {

	@FXML
	private Button backButton;
	@FXML
	private TableView<BooksTableModel> table;
	@FXML
	private TableColumn<BooksTableModel, Integer> bookIdColumn;
	@FXML
	private TableColumn<BooksTableModel, String> isbnColumn;
	@FXML
	private TableColumn<BooksTableModel, String> titleColumn;
	@FXML
	private TableColumn<BooksTableModel, String> authorColumn;
	@FXML
	private TableColumn<BooksTableModel, String> publisherColumn;
	@FXML
	private TableColumn<BooksTableModel, Integer> quantityColumn;

	private ObservableList<BooksTableModel> observerList = FXCollections.observableArrayList();
	private Connection connection;
	private ResultSet resultSet;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			resultSet = connection.createStatement()
					.executeQuery("select bookid, isbn, title, author, publisher, quantity FROM books");
			while (resultSet.next()) {
				observerList.add(new BooksTableModel(resultSet.getInt("bookid"), resultSet.getString("isbn"),
						resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("publisher"),
						resultSet.getInt("quantity")));
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

			bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));
			isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
			publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
			quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

			table.setItems(observerList);

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
}
