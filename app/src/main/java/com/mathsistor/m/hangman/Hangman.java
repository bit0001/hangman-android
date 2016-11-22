package com.mathsistor.m.hangman;

public class Hangman {

    private final String word = "word";

    public void guessLetter(String guess) {
        if (guess.matches("[^A-Za-z]{1}")) {
            throw new RuntimeException("Invalid character");
        }

        if (guess.isEmpty()) {
            throw new RuntimeException("Empty string");
        }

        if (guess.length() > 1) {
            if (!guess.equals(word)) {
                throw new RuntimeException("Incorrect word guess");
            }
        }
    }
}
