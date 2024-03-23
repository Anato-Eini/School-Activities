package com.example.javafx1.SmarterBruteForce;

import java.util.Scanner;

public class SmarterBruteForce {
    static String input;
    static Boolean isFinished = false;
    public static void main(String[] args) throws InterruptedException {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an unknown word: ");
        input = sc.nextLine();
        int length = input.length();
        Thread[] threads = new Thread[length * 5];
        int iteratorForVowels = 0;
        for(int i = 0; i < threads.length;){
            for(int j = input.length() - 1; j >= 0 && i < threads.length; j--)
                threads[i] = new Thread(new CrackRunnable(i++, j, vowels[iteratorForVowels]));
            iteratorForVowels = ++iteratorForVowels % vowels.length;
        }
        for(Thread t: threads)
            t.start();
        for(Thread t: threads)
            t.join();
    }
}
