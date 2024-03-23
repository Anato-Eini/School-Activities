package com.example.javafx1.SmarterBruteForce;

import java.util.Arrays;

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
        String[] attempts = new String[SmarterBruteForce.input.length()];
        Arrays.fill(attempts, "_");
        attempts[posIndex] = String.valueOf(vowel);
        System.out.printf("Thread %0" + String.valueOf(lengthOfTarget * 5).length() + "d: " +
                String.join(" ", attempts) + '\n', id);
        int i, j = SmarterBruteForce.input.length() - 1;
        if(SmarterBruteForce.input.charAt(posIndex) == vowel){
            for(;j >= 0 && !SmarterBruteForce.isFinished; j--){
                if(attempts[j].equals("_")) {
                    for (i = 97; SmarterBruteForce.input.charAt(j) != (char) i; i++){}
                    attempts[j] = String.valueOf((char) i);
                }
                System.out.printf("Thread %0" + String.valueOf(lengthOfTarget * 5).length() + "d: " +
                        String.join(" ", attempts) + "\n", id);
                if(String.join("", attempts).equals(SmarterBruteForce.input))
                    SmarterBruteForce.isFinished = true;
            }
        }
    }
}
