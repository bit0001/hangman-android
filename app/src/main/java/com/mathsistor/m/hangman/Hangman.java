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

    public void guess(String guess) throws Exception {
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

    private boolean userGuessedWordDirectly(String guess) throws WrongWordGuessedException {
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

            throw new WrongWordGuessedException("Guessed word is wrong.");
        }
        return false;
    }

    private void checkGuessIsNotEmpty(String guess) throws EmptyGuessException {
        if (guess.isEmpty()) {
            throw new EmptyGuessException("Empty guess.");
        }
    }

    private void checkGuessIsAValidCharacter(String guess) throws InvalidCharacterException {
        if (guess.matches("[^A-Za-z]{1}")) {
            throw new InvalidCharacterException("Invalid character.");
        }
    }

    private void checkGameIsNotOver() throws GameOverException {
        if (isGameOver) {
            throw new GameOverException("Game is over. Try a new game!");
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
