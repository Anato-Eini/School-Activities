package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

public class LogInController {
    public Label status;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button logInButton, registerBtn;
    public AnchorPane logInContainer;
    public static int idSession;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                    usernameField.getText().isBlank() || passwordField.getText().isBlank()) {
                status.setText("Blank input/s");
            } else {
                ResultSet resultSet = ForumApplication.connection.prepareStatement(
                        STR."SELECT * FROM tbluseracct WHERE name = '\{usernameField.getText()}' and password = '\{PasswordHashing.hashPassword(passwordField.getText())}'")
                        .executeQuery();
                if (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
                    Parent scene = loader.load();
                    idSession = resultSet.getInt("id");
                    logInContainer.getChildren().clear();
                    logInContainer.getChildren().add(scene);
                    MainPageController mainPageController = loader.getController();
                    mainPageController.getPosts();
                } else
                    status.setText("Incorrect Credentials");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
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