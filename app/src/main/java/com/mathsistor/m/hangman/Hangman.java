package com.mathsistor.m.hangman;

public class Hangman {

    private final String word;
    private boolean isGameOver;
    private String maskedWord;


    public Hangman(String word) {
        this.word = word;
        this.maskedWord = maskWord(word);
    }

    private String maskWord(String word) {
        StringBuilder maskedWord = new StringBuilder();

        for (Character _: word.toCharArray()) {
            maskedWord.append("?");
        }
        return maskedWord.toString();
    }

    public void guessLetter(String guess) {
        if (guess.matches("[^A-Za-z]{1}")) {
            throw new RuntimeException("Invalid character");
        }

        if (guess.isEmpty()) {
            throw new RuntimeException("Empty string");
        }

        if (guess.length() > 1) {
            if (guess.equals(word)) {
                isGameOver = true;
                return;
            }

            throw new RuntimeException("Incorrect word guess");
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getMaskedWord() {
        return maskedWord;
    }

}
