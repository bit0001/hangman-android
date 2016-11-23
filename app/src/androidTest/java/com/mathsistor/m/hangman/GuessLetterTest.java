package com.mathsistor.m.hangman;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class GuessLetterTest {

    @Rule
    public ActivityTestRule<HangmanActivity> hangmanActivityTestRule =
            new ActivityTestRule<>(HangmanActivity.class);

    private HangmanActivity activity;

    @Before
    public void setUp() {
        activity = hangmanActivityTestRule.getActivity();
    }

    @Test
    public void whenUserGuessesALetterTextFieldIsClean() {
        onView(withId(R.id.text_field)).perform(typeText("x"));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.text_field)).check(matches(withText("")));
    }

    @Test
    public void whenPlayerGuessesWellUIIsUpdatedProperly() {
        String character = String.valueOf(activity.getGame().getWord().charAt(0));
        onView(withId(R.id.text_field)).perform(typeText(character));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.word_to_guess)).check(matches(withText(getMaskedWordButFirstCharacter())));
        onView(withId(R.id.guessed_letters)).check(matches(withText(
                activity.getResources().getString(R.string.you_have_guessed) + " " + character + "."
        )));
    }

    @Test
    public void whenPlayerGuessesWrongUIIsUpdatedProperly() {
        String character = getFirstCharacterNotInWord();
        onView(withId(R.id.text_field)).perform(typeText(character));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.word_to_guess)).check(matches(withText(getMaskedWord())));
        onView(withId(R.id.guessed_letters)).check(matches(withText(
            activity.getResources().getString(R.string.you_have_guessed) + " " + character + "."
        )));
        onView(withId(R.id.remaining_guesses)).check(matches(withText(
            "(" + (Hangman.MAX_GUESSES - 1) + activity.getResources().getString(R.string.guesses_left)
        )));
    }

    private String getFirstCharacterNotInWord() {
        String englishLetters = "abcdefghijklmopqrstuvwxyz";
        String word = activity.getGame().getWord().toLowerCase();
        for (Character c: englishLetters.toCharArray()) {
            if (!word.contains(String.valueOf(c))) {
                return String.valueOf(c);
            }
        }
        return null;
    }

    private String getMaskedWordButFirstCharacter() {
        String word = activity.getGame().getWord();
        StringBuilder maskedWord = new StringBuilder(String.valueOf(word.charAt(0)));
        for (int i = 1; i < word.length(); i++) {
            maskedWord.append("?");
        }

        return maskedWord.toString();
    }

    private String getMaskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (int i = 0; i < activity.getGame().getWord().length(); i++) {
            maskedWord.append("?");
        }

        return maskedWord.toString();
    }
}
