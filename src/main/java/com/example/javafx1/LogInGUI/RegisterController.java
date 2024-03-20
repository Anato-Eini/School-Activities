package com.example.javafx1.LogInGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.Objects;

public class RegisterController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button returnBtn, registerBtn;
    public Label status;
    public AnchorPane registerContainer;
    @FXML
    protected void register() throws IOException {
        status.setAlignment(Pos.CENTER);
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                usernameField.getText().isBlank() || passwordField.getText().isBlank())
          status.setText("Blank input/s");
        else{
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            boolean alreadyRegistered = false;
            while ((line = br.readLine()) != null) {
                String[] token = line.split(" ");
                if (token[0].equals(usernameField.getText())) {
                    alreadyRegistered = true;
                    break;
                }
            }
            br.close();
            if (alreadyRegistered)
                status.setText("Username already exists");
            else{
                BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
                bw.write(usernameField.getText() + " " + passwordField.getText() + '\n');
                bw.close();
                status.setText("User Registered");
            }
        }
    }
    @FXML
    protected void logInPage() throws IOException {
        Parent node = FXMLLoader.load(
                Objects.requireNonNull(LogInApplication.class.getResource("login-view.fxml")));
        AnchorPane parent = (AnchorPane) registerContainer.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(node);
    }
}
