module com.capstoneGUI {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    exports com.example;
    opens com.example to javafx.fxml;

    exports com.example.Controllers;
    opens com.example.Controllers to javafx.fxml;
}