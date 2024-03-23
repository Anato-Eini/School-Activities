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
            int number = j;
            for (int i = 1; i <= j / 2; i++)
                if (j % i == 0)
                    total += i;
            Label label = new Label(String.valueOf(total));
            if (total < j)
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.numLTPN) {
                        PerfectNumberMainPage.numLTPN.getChildren().add(label);
                    }
                });
            else if (total == j)
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.numPN) {
                        PerfectNumberMainPage.numPN.getChildren().add(label);
                    }
                });
            else
                Platform.runLater(() -> {
                    synchronized (PerfectNumberMainPage.numMTPN) {
                        PerfectNumberMainPage.numMTPN.getChildren().add(label);
                    }
                });
            Platform.runLater(() -> PerfectNumberMainPage.progressIndicators.get(i).
                    setProgress(end - number == 0 ? 1 : 1 - ((end - number) / (double) totalNumbers)));
        }
    }
}
