module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.javafx1.ForumApplication;
    opens com.example.javafx1.ForumApplication to javafx.fxml;
    exports com.example.javafx1.PerfectNumber;
    opens com.example.javafx1.PerfectNumber to javafx.fxml;
}