package com.example.javafx1.FibonacciSequence;

import java.util.Scanner;

public class FibonacciSequence {
    static Thread[] threads;
    static int[] results;
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Until what number? ");
        int n = sc.nextInt();
        threads = new Thread[n];
        results = new int[n];
        for(int i = 0; i < n; i++)
            threads[i] = new Thread(new FibRunnable(i));

        threads[n - 1].start();
        threads[n - 1].join();
        System.out.println("Fibonacci Sequence: ");
        for(int i: results)
            System.out.print(i + " ");
    }
}
