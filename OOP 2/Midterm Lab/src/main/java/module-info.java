module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.javafx1.LogInGUI;
    opens com.example.javafx1.LogInGUI to javafx.fxml;
    exports com.example.javafx1.PerfectNumber;
    opens com.example.javafx1.PerfectNumber to javafx.fxml;
    exports com.example.javafx1;
    opens com.example.javafx1 to javafx.fxml;
}