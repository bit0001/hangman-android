package com.mathsistor.m.hangman;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

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
    public void whenUserGuessesADigitAnExceptionIsThrown() throws InvalidCharacterException {
        exception.expect(InvalidCharacterException.class);
        hangman.guess("9");
    }

    @Test
    public void whenUserGuessesASpecialCharacterAnExceptionIsThrown() throws InvalidCharacterException {
        exception.expect(InvalidCharacterException.class);
        hangman.guess("?");
    }

    @Test
    public void whenUserGuessesAnEmptyStringAnExceptionIsThrown() throws InvalidCharacterException {
        exception.expect(RuntimeException.class);
        hangman.guess("");
    }

    @Test
    public void whenUserGuessesMoreThanOneCharacterThenAnExceptionIsThrownIfWordIsNotGuessed() throws InvalidCharacterException {
        exception.expect(RuntimeException.class);
        hangman.guess("love");
    }

    @Test
    public void whenUserGuessesAWordCorrectlyThenGameIsOver() throws InvalidCharacterException {
        hangman.guess("word");
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
    public void whenPlayerGuessesCorrectlyThenMaskedWordIsUpdated() throws InvalidCharacterException {
        hangman.guess("w");
        assertThat(hangman.getMaskedWord(), is("w???"));
    }

    @Test
    public void whenGameStartsRemainingGuessesIsMAX_GUESSES() {
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES));
    }

        @Test
    public void whenPlayerGuessesIncorrectlyThenGuessesLeftIsDecreased() throws InvalidCharacterException {
        hangman.guess("t");
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES - 1));
    }

    @Test
    public void whenPlayerGuessesTheSameLetterTwiceGuessesLeftIsNotReduced() throws InvalidCharacterException {
        hangman.guess("t");
        hangman.guess("t");
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES - 1));
    }

    @Test
    public void whenUserGuessesAValidCharacterThenThisIsIncludedInGuessedLetters() throws InvalidCharacterException {
        ArrayList<Character> expected = new ArrayList<>();
        hangman.guess("t");
        expected.add('t');
        assertThat(hangman.getGuessedLetters(), is(expected));
        hangman.guess("w");
        expected.add('w');
        assertThat(hangman.getGuessedLetters(), is(expected));
    }

    @Test
    public void whenPlayerGuessesAPreviouslyGuessedLetterItIsNotAddedTwiceToGuessedLetters() throws InvalidCharacterException {
        ArrayList<Character> expected = new ArrayList<>();
        hangman.guess("t");
        expected.add('t');
        assertThat(hangman.getGuessedLetters(), is(expected));
        hangman.guess("t");
        assertThat(hangman.getGuessedLetters(), is(expected));
        hangman.guess("w");
        expected.add('w');
        hangman.guess("w");
        assertThat(hangman.getGuessedLetters(), is(expected));
    }

    @Test
    public void whenLastLetterIsGuessedGameIsOver() throws InvalidCharacterException {
        hangman.guess("w");
        hangman.guess("o");
        hangman.guess("r");
        hangman.guess("d");
        assertThat(hangman.isGameOver(), is(true));
    }

    @Test
    public void whenNoMoreGuessesLeftThenGameIsOver() throws InvalidCharacterException {
        hangman.guess("a");
        hangman.guess("b");
        hangman.guess("c");
        hangman.guess("e");
        hangman.guess("f");
        hangman.guess("g");
        hangman.guess("h");
        hangman.guess("i");
        hangman.guess("j");
        hangman.guess("k");
        assertThat(hangman.isGameOver(), is(true));
    }

}
