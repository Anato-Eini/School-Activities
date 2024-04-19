package com.example.forum;

import com.example.forum.Connections.MySQLConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateAccountController {

    public TextField newUsername, newPassword;
    public Button updateBtn, returnBtn;
    public Label status;

    public void updateAccount() {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("")){
            Statement statement = connection.createStatement();
            if (newUsername.getText().isEmpty() || newPassword.getText().isEmpty() ||
                    newUsername.getText().isBlank() || newPassword.getText().isBlank()) {
                status.setText("Blank input/s");

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
