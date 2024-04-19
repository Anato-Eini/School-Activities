package com.example.forum;

import com.example.forum.Connections.CreateTable;
import com.example.forum.Connections.MySQLConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class ForumApplication extends Application {
    public static Connection connection = MySQLConnection.getConnection();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("login.css")).toExternalForm());
        CreateTable.init_Tables(connection);
        stage.setTitle("Forum Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }
}