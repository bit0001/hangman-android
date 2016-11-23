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
        checkGameIsNotOver();
        checkGuessIsNotEmpty(guess);
        checkGuessIsAValidCharacter(guess);

        if (userGuessedWordDirectly(guess)) return;

        if (guessedLetters.contains(guess.charAt(0))) {
            return;
        }

        if (word.contains(guess)) {
            StringBuilder newMaskedWord = new StringBuilder();
            for(int i = 0; i < word.length(); i++) {
                if (maskedWord.charAt(i) == '?') {
                    newMaskedWord.append(word.charAt(i) == guess.charAt(0) ? word.charAt(i) : "?");
                } else {
                    newMaskedWord.append(word.charAt(i));
                }
            }
            maskedWord = newMaskedWord.toString();
            guessedLetters.add(guess.charAt(0));
        } else {
            if (!guessedLetters.contains(guess.charAt(0))) {
                guessesLeft--;
                guessedLetters.add(guess.charAt(0));
            }
        }

        if (maskedWord.equals(word) || guessesLeft == 0) {
            isGameOver = true;
        }
    }

    private boolean userGuessedWordDirectly(String guess) {
        if (guess.length() > 1) {
            if (guess.equals(word)) {
                maskedWord = word;
                isGameOver = true;
                return true;
            }
            guessesLeft--;

            if (guessesLeft == 0) {
                isGameOver = true;
            }

            throw new RuntimeException("Incorrect word guess");
        }
        return false;
    }

    private void checkGuessIsNotEmpty(String guess) {
        if (guess.isEmpty()) {
            throw new RuntimeException("Empty string");
        }
    }

    private void checkGuessIsAValidCharacter(String guess) {
        if (guess.matches("[^A-Za-z]{1}")) {
            throw new RuntimeException("Invalid character");
        }
    }

    private void checkGameIsNotOver() {
        if (isGameOver) {
            throw new RuntimeException("Game is over");
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

    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public String getWord() {
        return word;
    }
}
