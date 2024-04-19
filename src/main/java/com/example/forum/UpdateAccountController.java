package com.example.forum;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.forum.ForumApplication.connection;

public class UpdateAccountController {

    public TextField newUsername, newPassword;
    public Button updateBtn, returnBtn;
    public Label status;
    public AnchorPane container;

    public void updateAccount() {
        try(
            PreparedStatement preparedStatement = connection.prepareStatement("")){
            Statement statement = connection.createStatement();
            if (newUsername.getText().isEmpty() || newPassword.getText().isEmpty() ||
                    newUsername.getText().isBlank() || newPassword.getText().isBlank()) {
                status.setText("Blank input/s");
            }else {
                //TODO implement update account credentials
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void returnToMainPage(){
        AnchorPane parent = (AnchorPane) container.getParent();
        //TODO implement return button function
    }
}
