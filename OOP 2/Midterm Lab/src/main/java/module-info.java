module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx1 to javafx.fxml;
    exports com.example.javafx1;
}