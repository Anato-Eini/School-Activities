package com.example.javafx1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public Label status;
    public TextField usernameField, passwordField;
    public Button logOutButton;
    public Button logInButton;
    public VBox logInContainer;
    public AnchorPane mainPageContainer;
    public ColorPicker colorPicker;
    static String nextColor = "#ffffff";

    @FXML
    protected void onHelloButtonClick() throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            status.setText("Blank input/s");
        }else{
            Parent scene = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("main-page.fxml")));
            logInContainer.getChildren().clear();
            logInContainer.getChildren().add(scene);
            Button logOutButton1 = (Button)BFSNodeSearch.findNode(scene, "logOutButton");
            assert logOutButton1 != null;
            logOutButton1.setStyle("-fx-background-color: ".concat(nextColor).concat(";"));
        }
    }
    @FXML
    protected void logOutClick() throws IOException {
        System.out.println(logOutButton);
        nextColor = "#".concat(colorPicker.getValue().toString().substring(2, 8));
        Parent scene = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("login-view.fxml")));
        VBox p = (VBox) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

}