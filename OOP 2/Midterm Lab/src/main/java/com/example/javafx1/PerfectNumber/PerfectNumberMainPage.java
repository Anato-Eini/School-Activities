package com.example.javafx1.PerfectNumber;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PerfectNumberMainPage {
    public VBox numLTPN;
    public VBox numPN;
    public VBox numMTPN;
    public Button returnBtn;
    @FXML
    public HBox progressBars;
    public void startProcess(){
        Thread[] threads = new Thread[PerfectNumberController.numThreads];
        for(int i = 0; i < PerfectNumberController.numThreads; i++){
            ProgressBar progressBar = new ProgressBar();
            progressBar.setProgress(0);
            progressBars.getChildren().add(progressBar);
        }
    }
}
