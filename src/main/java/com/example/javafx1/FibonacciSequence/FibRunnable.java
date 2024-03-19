package com.example.javafx1.FibonacciSequence;

public class FibRunnable implements Runnable{
    int id;
    FibRunnable(int id){
        this.id = id;
    }
    @Override
    public void run() {
        if(id == 0){
            FibonacciSequence.results[id] = 1;
            synchronized (FibonacciSequence.threads.get(id)){
                FibonacciSequence.threads.get(id).notifyAll();
            }
            return;
        }
        synchronized (FibonacciSequence.threads.get(id - 1)){
            try {
                FibonacciSequence.threads.get(id - 1).start();
                FibonacciSequence.threads.get(id - 1).wait();
            } catch (InterruptedException ignored) {}
        }
        if(id != 1)
            FibonacciSequence.results[id] = FibonacciSequence.results[id - 1] + FibonacciSequence.results[id - 2];
        else
            FibonacciSequence.results[id] = 2;

        synchronized (FibonacciSequence.threads.get(id)){
            FibonacciSequence.threads.get(id).notifyAll();
        }
    }
}
