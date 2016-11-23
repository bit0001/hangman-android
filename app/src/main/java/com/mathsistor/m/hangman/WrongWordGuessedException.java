package com.mathsistor.m.hangman;

public class WrongWordGuessedException extends Exception {

    WrongWordGuessedException(String message) {
        super(message);
    }

}
