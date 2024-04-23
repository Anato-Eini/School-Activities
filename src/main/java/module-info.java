module com.example.forum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.forum;
    opens com.example.forum to javafx.fxml;
}