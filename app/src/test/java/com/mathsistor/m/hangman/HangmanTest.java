package com.mathsistor.m.hangman;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HangmanTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Hangman hangman;

    @Before
    public void setUp() {
        hangman = new Hangman("word");
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

    @Test
    public void whenUserGuessesAWordCorrectlyThenGameIsOver() {
        hangman.guessLetter("word");
        assertThat(hangman.isGameOver(), is(true));
    }

    @Test
    public void givenWordToGuessIsWordWhenGameStartsThenWordToGuessIsMasked() {
        assertThat(hangman.getMaskedWord(), is("????"));
    }

    @Test
    public void givenWordToGuessIsVelocityWhenGameStartsThenWordToGuessIsMasked() {
        hangman = new Hangman("velocity");
        assertThat(hangman.getMaskedWord(), is("????????"));
    }

    @Test
    public void whenPlayerGuessesCorrectlyThenMaskedWordIsUpdated() {
        hangman.guessLetter("w");
        assertThat(hangman.getMaskedWord(), is("w???"));
    }

}
