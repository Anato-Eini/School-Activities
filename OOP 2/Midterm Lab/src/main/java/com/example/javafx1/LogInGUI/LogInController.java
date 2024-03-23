package com.example.javafx1.LogInGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.Objects;

public class LogInController {
    public Label status;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button logInButton;
    public AnchorPane logInContainer;
    public Button registerBtn;
    static String username;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        status.setAlignment(Pos.CENTER);
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                usernameField.getText().isBlank() || passwordField.getText().isBlank()){
            status.setText("Blank input/s");
        }else{
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            boolean isRegistered = false;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if(tokens[0].equals(usernameField.getText()) && tokens[1].equals(passwordField.getText())){
                    isRegistered = true;
                    username = usernameField.getText();
                    break;
                }
            }
            br.close();
            if(isRegistered){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
                Parent scene = loader.load();
                logInContainer.getChildren().clear();
                logInContainer.getChildren().add(scene);
                MainPageController mainPageController = loader.getController();
                mainPageController.getPosts();
            }else status.setText("Incorrect Credentials");
        }
    }
    @FXML
    protected void registerPage() throws IOException {
        Parent scene = FXMLLoader.load(
                Objects.requireNonNull(LogInApplication.class.getResource("registerPage.fxml")));
        logInContainer.getChildren().clear();
        logInContainer.getChildren().add(scene);
    }
}