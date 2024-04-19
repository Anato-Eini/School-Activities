package com.example.forum;

import com.example.forum.Connections.MySQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.sql.*;
import java.util.Objects;

public class RegisterController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button returnBtn, registerBtn;
    public Label status;
    public AnchorPane registerContainer;
    @FXML
    protected void register() throws IOException {
        String query;
        try (Connection con = MySQLConnection.getConnection(); Statement statement = con.createStatement();
            PreparedStatement ps = con.prepareStatement("insert into useracct (name, password) values (?, ?)")) {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                    usernameField.getText().isBlank() || passwordField.getText().isBlank())
                status.setText("Blank input/s");
            else {
                query = STR."Select * from users where name = '\{usernameField.getText()}'";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next())
                    status.setText("Username already exists");
                else {
                    ps.setString(1, usernameField.getText());
                    ps.setString(2, passwordField.getText());
                    ps.execute();
                    status.setText("User Registered");
                }
            }
        }catch (SQLException e) {
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
