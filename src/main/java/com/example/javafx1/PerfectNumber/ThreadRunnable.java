package com.example.javafx1.PerfectNumber;

import javafx.application.Platform;
import javafx.scene.control.Label;


public class ThreadRunnable implements Runnable{
    int start, end, i;

    public ThreadRunnable(int start, int end, int i) {
        this.start = start;
        this.end = end;
        this.i = i;
    }

    @Override
    public void run() {
        int totalNumbers = end - start;
        for (int j = start; j <= end; j++) {
            int total = 0;
            for (int i = 1; i <= j / 2; i++)
                if (j % i == 0)
                    total += i;
            if (total < j)
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock1) {
                        PerfectNumberMainPage.numLTPN.setText(
                                Integer.parseInt(PerfectNumberMainPage.numLTPN.getText()) + 1 + "");
                    }
                });
            else if (total == j) {
                int finalTotal = total;
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock2) {
                        PerfectNumberMainPage.numPN.getChildren().add(new Label(String.valueOf(finalTotal)));
                    }
                });
            }
            else
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock3) {
                        PerfectNumberMainPage.numMTPN.setText(
                                Integer.parseInt(PerfectNumberMainPage.numMTPN.getText()) + 1 + "");
                    }
                });
            PerfectNumberMainPage.progressIndicators.get(i).
                    setProgress(end == j ? 1 : 1 - ((end - j) / (double) totalNumbers));
        }
    }
}
