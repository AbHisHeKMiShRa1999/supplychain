module com.example.supplychainbyabhishek {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.desktop;
    requires java.sql;


    opens com.example.supplychainbyabhishek to javafx.fxml;
    exports com.example.supplychainbyabhishek;
}