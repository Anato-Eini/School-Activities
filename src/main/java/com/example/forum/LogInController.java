package com.example.forum;

import com.example.forum.Connections.MySQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LogInController {
    public Label status;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button logInButton, registerBtn;
    public AnchorPane logInContainer;
    static String username;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        try (Connection con = MySQLConnection.getConnection(); Statement statement = con.createStatement()) {
            String query;
            query = "CREATE TABLE IF NOT EXISTS userAcct(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully");
            status.setAlignment(Pos.CENTER);
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                    usernameField.getText().isBlank() || passwordField.getText().isBlank()) {
                status.setText("Blank input/s");
            } else {
                PreparedStatement statement1 = con.prepareStatement("SELECT * FROM userAcct WHERE name = ? and password = ?");
                statement1.setString(1, usernameField.getText());
                statement1.setString(2, passwordField.getText());
                if (statement1.executeQuery().next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
                    Parent scene = loader.load();
                    username = usernameField.getText();
                    logInContainer.getChildren().clear();
                    logInContainer.getChildren().add(scene);
                    MainPageController mainPageController = loader.getController();
                    mainPageController.getPosts();
                } else
                    status.setText("Incorrect Credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void registerPage() throws IOException {
        Parent scene = FXMLLoader.load(
                Objects.requireNonNull(ForumApplication.class.getResource("registerPage.fxml")));
        logInContainer.getChildren().clear();
        logInContainer.getChildren().add(scene);
    }
}