package com.mathsistor.m.hangman;

import java.util.ArrayList;

public class Hangman {

    public static final int MAX_GUESSES = 10;
    private final String word;
    private boolean isGameOver;
    private String maskedWord;
    private int guessesLeft;
    private ArrayList<Character> guessedLetters;


    public Hangman(String word) {
        this.word = word;
        this.maskedWord = maskWord(word);
        this.guessesLeft = MAX_GUESSES;
        guessedLetters = new ArrayList<>();
    }

    private String maskWord(String word) {
        StringBuilder maskedWord = new StringBuilder();

        for (Character _: word.toCharArray()) {
            maskedWord.append("?");
        }
        return maskedWord.toString();
    }

    public void guess(String guess) {
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

        if (word.contains(guess)) {
            StringBuilder newMaskedWord = new StringBuilder();
            for(Character c: word.toCharArray()) {
                newMaskedWord.append(c == guess.charAt(0) ? c : "?");
            }
            maskedWord = newMaskedWord.toString();
        } else {
            if (!guessedLetters.contains(guess.charAt(0))) {
                guessesLeft--;
                guessedLetters.add(guess.charAt(0));
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }
}
