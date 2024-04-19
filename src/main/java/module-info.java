module com.example.forum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports com.example.forum;
    opens com.example.forum to javafx.fxml;
}