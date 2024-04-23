package com.example.forum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

public class RegisterController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button returnBtn, registerBtn;
    public Label status;
    public AnchorPane registerContainer;
    @FXML
    protected void register() {
        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                    usernameField.getText().isBlank() || passwordField.getText().isBlank())
                status.setText("Blank input/s");
            else {
                ResultSet resultSet = ForumApplication.connection.createStatement().executeQuery(
                                STR."Select * from tbluseracct where name = '\{usernameField.getText()}'");
                if (resultSet.next())
                    status.setText("Username already exists");
                else {
                    ForumApplication.connection.createStatement().execute(
                            STR."insert into tbluseracct (name, password) values ('\{usernameField.getText()}', '\{PasswordHashing.hashPassword(passwordField.getText())}')"
                    );
                    status.setText("User Registered");
                    usernameField.clear();
                    passwordField.clear();
                }
            }
        }catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void logInPage() throws IOException {
        Parent node = FXMLLoader.load(
                Objects.requireNonNull(ForumApplication.class.getResource("login-view.fxml")));
        AnchorPane parent = (AnchorPane) registerContainer.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(node);
    }
}
