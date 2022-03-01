package com.example.librarymanagmentsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtils {

	public static void changeScene(ActionEvent event, String fxmlFile) throws IOException {
		Parent root = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
			root = fxmlLoader.load();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public static void addLibrarian(ActionEvent event, String login, String password, String name, String surname,
			String email, String phoneNumber) throws SQLException {
		Connection connection = null;
		PreparedStatement psAddLibrarian = null;
		Alert alert = null;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			psAddLibrarian = connection.prepareStatement(
					"INSERT into librarian ('login', 'password', 'name','surname','email','number') VALUES (?,?,?,?,?,?)");
			psAddLibrarian.setString(1, login);
			psAddLibrarian.setString(2, password);
			psAddLibrarian.setString(3, name);
			psAddLibrarian.setString(4, surname);
			psAddLibrarian.setString(5, email);
			psAddLibrarian.setString(6, phoneNumber);

			if (!psAddLibrarian.execute()) {
				alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setContentText("Librarian was added succesfuly.");
				alert.show();
			} else {
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Librarian was not added succesfuly. Please type proper values.");
				alert.show();
			}

		} catch (SQLiteException e) {
			e.printStackTrace();
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Login is already used."); // login has unique value
			alert.show();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (psAddLibrarian != null) {
				try {
					psAddLibrarian.close();
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
		}

	}

	public static void logIn(ActionEvent event, String login, String password) throws IOException {
		Connection connection = null;
		PreparedStatement logInStatement = null;
		Alert alert = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			logInStatement = connection.prepareStatement("Select password, admin FROM librarian WHERE login = ?");
			logInStatement.setString(1, login);
			resultSet = logInStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Provided login not found in the database.");
				alert.show();
			} else {
				while (resultSet.next()) {
					String retrivedPassword = resultSet.getString("password");
					String retrivedAdmin = resultSet.getString("admin");
					if (retrivedPassword.equals(password)) {
						if (retrivedAdmin.equals("0")) {
							changeScene(event, "librarian section/librarianSection.fxml");
						} else {
							changeScene(event, "admin section/adminSection.fxml");
						}
					} else {
						alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Password is not correct.");
						alert.show();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (logInStatement != null) {
				try {
					logInStatement.close();
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
		}
	}

	public static void addBook(ActionEvent event, String isbn, String title, String author, String publisher,
			int quantity) {
		Connection connection = null;
		PreparedStatement psAddLibrarian = null;
		Alert alert = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			psAddLibrarian = connection.prepareStatement(
					"INSERT into books ('isbn', 'title', 'author','publisher','quantity') VALUES (?,?,?,?,?)");
			psAddLibrarian.setString(1, isbn);
			psAddLibrarian.setString(2, title);
			psAddLibrarian.setString(3, author);
			psAddLibrarian.setString(4, publisher);
			psAddLibrarian.setInt(5, quantity);

			if (!psAddLibrarian.execute()) {
				alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setContentText("Book was added succesfuly.");
				alert.show();
			} else {
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Book was not added succesfuly. Please type proper values.");
				alert.show();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (psAddLibrarian != null) {
				try {
					psAddLibrarian.close();
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
		}

	}

	public static void deleteLibrarian(ActionEvent event, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement psCheckLibrarian = null;
		PreparedStatement psDeleteLibrarian = null;

		Alert alert = null;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\DB Browser for SQLite\\lms.db");
			psCheckLibrarian = connection.prepareStatement("SELECT user_id from librarian where user_id = ?");
			psCheckLibrarian.setInt(1, id);

			if (psCheckLibrarian.execute()) {

				if (psCheckLibrarian != null) {
					try {
						psCheckLibrarian.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				psDeleteLibrarian = connection.prepareStatement("delete from librarian where user_id = ?");
				psDeleteLibrarian.setInt(1, id);
				if (!psDeleteLibrarian.execute()) {
					alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setContentText("Librarian was deleted succesfuly.");
					alert.show();
				}
			} else {
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Librarian was not deleted succesfuly. Please type proper values.");
				alert.show();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (psDeleteLibrarian != null) {
				try {
					psDeleteLibrarian.close();
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
		}
	}

}
