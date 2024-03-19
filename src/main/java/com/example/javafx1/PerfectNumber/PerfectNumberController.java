package com.example.javafx1.PerfectNumber;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.regex.*;
public class PerfectNumberController {
    public TextField maxNumber, numberOfThreads;
    public Button Start;
    public Label status;
    static int numThreads, maxNum;
    public AnchorPane mainContainer;
    @FXML
    public void startButton() throws IOException {
        if (maxNumber.getText().isEmpty() || numberOfThreads.getText().isEmpty()) {
            status.setText("Blank input/s");
        } else if (Pattern.compile("\\D").matcher(maxNumber.getText()).find() ||
                Pattern.compile("\\D").matcher(numberOfThreads.getText()).find()) {
            status.setText("Incorrect Format");
        }else if(Integer.parseInt(maxNumber.getText()) <= 0){
            status.setText("Max N cannot be <= 0");
        }else if(Integer.parseInt(numberOfThreads.getText()) <= 0){
            status.setText("Number of Threads cannot be <= 0");
        } else{
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
