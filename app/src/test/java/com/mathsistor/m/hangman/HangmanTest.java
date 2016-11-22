package com.mathsistor.m.hangman;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HangmanTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Hangman hangman;

    @Before
    public void setUp() {
        hangman = new Hangman();
    }

    @Test
    public void whenUserGuessesADigitAnExceptionIsThrown() {
        exception.expect(RuntimeException.class);
        hangman.guessLetter("9");
    }

    @Test
    public void whenUserGuessesASpecialCharacterAnExceptionIsThrown() {
        exception.expect(RuntimeException.class);
        hangman.guessLetter("?");
    }

    @Test
    public void whenUserGuessesAnEmptyStringAnExceptionIsThrown() {
        exception.expect(RuntimeException.class);
        hangman.guessLetter("");
    }

    @Test
    public void whenUserGuessesMoreThanOneCharacterThenAnExceptionIsThrownIfWordIsNotGuessed() {
        exception.expect(RuntimeException.class);
        hangman.guessLetter("love");
    }
}
