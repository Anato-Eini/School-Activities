package com.example.javafx1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public Label status;
    public TextField usernameField, passwordField;
    public Button logOutButton;
    public VBox logInContainer;
    public AnchorPane mainPageContainer;
    public ColorPicker colorPicker;
    String nextColor = "#ffffff";

    @FXML
    protected void onHelloButtonClick() throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            status.setText("Blank input/s");
        }else{
            Parent scene = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("main-page.fxml")));
            logInContainer.getChildren().clear();
            logInContainer.getChildren().add(scene);
            ((Pane)((VBox)logInContainer.getChildren().get(0)).getChildren().get(0)).getChildren().get(0).setStyle(" -fx-background-color: " + nextColor);
        }
    }
    @FXML
    protected void logOutClick() throws IOException {
        nextColor = "#".concat(colorPicker.getValue().toString().substring(2, 8));
        /*BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\L11X09W10\\IdeaProjects\\JavaFX-1\\src\\main\\resources\\com\\example\\javafx1\\user.css"));
        bw.write(".button{\n" +
                "    -fx-background-color: #".concat(colorPicker.getValue().toString().substring(2, 8)).concat(
                ";\n}"));
        bw.close();*/
        Parent scene = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("login-view.fxml")));
        VBox p = (VBox) mainPageContainer.getParent();
        p.getChildren().clear();
        p.getChildren().add(scene);
    }

}