package com.mathsistor.m.hangman;

public class Hangman {
    public void guessLetter(String guess) {
        if (guess.matches("[^A-Za-z]")) {
            throw new RuntimeException("Invalid character");
        }
    }
}
