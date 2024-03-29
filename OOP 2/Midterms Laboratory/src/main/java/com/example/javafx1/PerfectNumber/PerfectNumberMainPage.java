package com.example.javafx1.PerfectNumber;

import com.example.javafx1.BFSNodeSearch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class PerfectNumberMainPage {
    public static final ReentrantLock lock1 = new ReentrantLock(),
            lock2 = new ReentrantLock(), lock3 = new ReentrantLock();
    @FXML
    static public VBox numPN;
    @FXML
    public Button returnBtn;
    @FXML
    public HBox progressBars;
    @FXML
    public AnchorPane container;
    static ArrayList<ProgressIndicator> progressIndicators = new ArrayList<>();
    public ScrollPane containerPN;
    @FXML
    static public Label numMTPN, numLTPN;

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
        numPN = (VBox) BFSNodeSearch.findNode(parent, "numPN");
        numLTPN = (Label) BFSNodeSearch.findNode(parent, "numLTPN");
        numMTPN = (Label) BFSNodeSearch.findNode(parent, "numMTPN");
        assert numPN != null;
        numPN.getChildren().clear();
        numMTPN.setText("0");
        numLTPN.setText("0");
        progressIndicators.clear();
    }
    public void doReturnBtn() throws IOException {
        Parent p = FXMLLoader.load(
                Objects.requireNonNull(PerfectNumberApplication.class.getResource("perfectNumber.fxml")));
        AnchorPane parent = (AnchorPane) container.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(p);
    }
}
