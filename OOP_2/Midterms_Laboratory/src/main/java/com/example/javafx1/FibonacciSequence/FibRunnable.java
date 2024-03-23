package com.example.javafx1.FibonacciSequence;

public class FibRunnable implements Runnable{
    int id;
    FibRunnable(int id){
        this.id = id;
    }
    @Override
    public void run() {
        if(id == 0 || id == 1){
            FibonacciSequence.results[id] = id;
            return;
        }
        synchronized (FibonacciSequence.threads[id - 1]){
            try {
                FibonacciSequence.threads[id - 1].start();
                FibonacciSequence.threads[id - 1].wait();
            } catch (InterruptedException ignored) {}
        }

        FibonacciSequence.results[id] = FibonacciSequence.results[id - 1] + FibonacciSequence.results[id - 2];
    }
}
