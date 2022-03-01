module com.example.librarymanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires org.xerial.sqlitejdbc;


    opens com.example.librarymanagmentsystem to javafx.fxml;
    exports com.example.librarymanagmentsystem;
}