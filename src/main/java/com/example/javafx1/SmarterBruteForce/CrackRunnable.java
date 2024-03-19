package com.example.javafx1.SmarterBruteForce;

public class CrackRunnable implements Runnable{
    int id, posIndex;
    char vowel;

    public CrackRunnable(int id, int posIndex, char vowel) {
        this.id = id;
        this.posIndex = posIndex;
        this.vowel = vowel;
    }

    @Override
    public void run() {
        int lengthOfTarget = SmarterBruteForce.input.length();
        System.out.printf("Thread %0" + String.valueOf(lengthOfTarget * 5).length() + "d: " +
                "_ ".repeat(posIndex) + vowel + " " +
                "_ ".repeat(lengthOfTarget - posIndex - 1) + '\n', id);
    }
}
