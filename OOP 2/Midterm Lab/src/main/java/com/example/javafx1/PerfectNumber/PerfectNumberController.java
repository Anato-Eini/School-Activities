package com.example.javafx1.PerfectNumber;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PerfectNumberController {
    public TextField maxNumber, numberOfThreads;
    public Button Start;
    public Label status;
    static int numThreads, maxNum;
    public AnchorPane mainContainer;

    @FXML
    public void startButton() throws IOException {
        if(maxNumber.getText().isEmpty() || numberOfThreads.getText().isEmpty()){
            status.setText("Blank input/s");
        }else if(maxNumber.getText().contains("[^a-zA-Z0-9\\.]") || numberOfThreads.getText().contains("^a-zA-Z0-9\\.")){
            status.setText("Incorrect Format");
        }else{
            numThreads = Integer.parseInt(numberOfThreads.getText());
            maxNum = Integer.parseInt(maxNumber.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("perfectNumberMainPage.fxml"));
            Parent scene = loader.load();
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(scene);
            PerfectNumberMainPage controller = loader.getController();
            controller.startProcess();
        }
    }
}
