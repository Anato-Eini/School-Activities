package com.example.javafx1.PerfectNumber;

import com.example.javafx1.BFSNodeSearch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PerfectNumberMainPage {
    @FXML
    static public VBox numLTPN, numPN, numMTPN;
    @FXML
    public Button returnBtn;
    @FXML
    public HBox progressBars;
    @FXML
    public AnchorPane container;
    static ArrayList<ProgressIndicator> progressIndicators = new ArrayList<>();
    public ScrollPane containerLTPN, containerPN, containerMTPN;
    public void startProcess() {
        initializeFields();
        Thread[] threads = new Thread[PerfectNumberController.numThreads];
        int startingNum = 1, endingNum;
        for(int i = 0; i < PerfectNumberController.numThreads; i++){
            ProgressIndicator progressIndicator = new ProgressIndicator();
            progressIndicator.setProgress(0);
            progressBars.getChildren().add(progressIndicator);
            progressIndicators.add(progressIndicator);
            endingNum = (int) (PerfectNumberController.maxNum * (1 / (double)(PerfectNumberController.numThreads - i)));
            threads[i] = new Thread(
                    new ThreadRunnable(startingNum, Math.min(PerfectNumberController.maxNum, endingNum), i));
            startingNum = endingNum + 1;
        }
        for(Thread t: threads)
            t.start();
    }
    public void initializeFields(){
        AnchorPane parent = (AnchorPane) container.getParent();
        numLTPN = (VBox) BFSNodeSearch.findNode(parent, "numLTPN");
        numPN = (VBox) BFSNodeSearch.findNode(parent, "numPN");
        numMTPN = (VBox) BFSNodeSearch.findNode(parent, "numMTPN");
    }
    public void doReturnBtn() throws IOException {
        Parent p = FXMLLoader.load(
                Objects.requireNonNull(PerfectNumberApplication.class.getResource("perfectNumber.fxml")));
        AnchorPane parent = (AnchorPane) container.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(p);
    }
}
