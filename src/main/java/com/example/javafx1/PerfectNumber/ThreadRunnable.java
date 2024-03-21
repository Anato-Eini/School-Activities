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
            Label label = new Label(String.valueOf(total));
            if (total < j)
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock1) {
                        PerfectNumberMainPage.numLTPN.getChildren().add(label);
                    }
                });
            else if (total == j)
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock2) {
                        PerfectNumberMainPage.numPN.getChildren().add(label);
                    }
                });
            else
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.lock3) {
                        PerfectNumberMainPage.numMTPN.getChildren().add(label);
                    }
                });
            PerfectNumberMainPage.progressIndicators.get(i).
                    setProgress(end == j ? 1 : 1 - ((end - j) / (double) totalNumbers));
        }
    }
}
