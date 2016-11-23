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
    public void whenUserGuessesADigitAnExceptionIsThrown() throws Exception {
        exception.expect(InvalidCharacterException.class);
        hangman.guess("9");
    }

    @Test
    public void whenUserGuessesASpecialCharacterAnExceptionIsThrown() throws Exception {
        exception.expect(InvalidCharacterException.class);
        hangman.guess("?");
    }

    @Test
    public void whenUserGuessesAnEmptyStringAnExceptionIsThrown() throws Exception {
        exception.expect(EmptyGuessException.class);
        hangman.guess("");
    }

    @Test
    public void whenUserGuessesMoreThanOneCharacterThenAnExceptionIsThrownIfWordIsNotGuessed() throws Exception {
        exception.expect(RuntimeException.class);
        hangman.guess("love");
    }

    @Test
    public void whenUserGuessesAWordCorrectlyThenGameIsOver() throws Exception {
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
    public void whenPlayerGuessesCorrectlyThenMaskedWordIsUpdated() throws Exception {
        hangman.guess("w");
        assertThat(hangman.getMaskedWord(), is("w???"));
    }

    @Test
    public void whenGameStartsRemainingGuessesIsMAX_GUESSES() {
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES));
    }

        @Test
    public void whenPlayerGuessesIncorrectlyThenGuessesLeftIsDecreased() throws Exception {
        hangman.guess("t");
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES - 1));
    }

    @Test
    public void whenPlayerGuessesTheSameLetterTwiceGuessesLeftIsNotReduced() throws Exception {
        hangman.guess("t");
        hangman.guess("t");
        assertThat(hangman.getGuessesLeft(), is(Hangman.MAX_GUESSES - 1));
    }

    @Test
    public void whenUserGuessesAValidCharacterThenThisIsIncludedInGuessedLetters() throws Exception {
        ArrayList<Character> expected = new ArrayList<>();
        hangman.guess("t");
        expected.add('t');
        assertThat(hangman.getGuessedLetters(), is(expected));
        hangman.guess("w");
        expected.add('w');
        assertThat(hangman.getGuessedLetters(), is(expected));
    }

    @Test
    public void whenPlayerGuessesAPreviouslyGuessedLetterItIsNotAddedTwiceToGuessedLetters() throws Exception {
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
    public void whenLastLetterIsGuessedGameIsOver() throws Exception {
        hangman.guess("w");
        hangman.guess("o");
        hangman.guess("r");
        hangman.guess("d");
        assertThat(hangman.isGameOver(), is(true));
    }

    @Test
    public void whenNoMoreGuessesLeftThenGameIsOver() throws Exception {
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
