package com.mathsistor.m.hangman;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mathsistor.m.hangman.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.not;

public class UISpec {
    @Rule
    public ActivityTestRule<HangmanActivity> hangmanActivityTestRule =
            new ActivityTestRule<>(HangmanActivity.class);

    @Test
    public void whenUserLaunchesTheAppThenUIElementsAreDisplayed() {
        int[] ids = {
                R.id.hangman_title, R.id.hangman_image,
                R.id.the_word_label, R.id.word_to_guess,
                R.id.text_field, R.id.guess_button,
                R.id.new_game_button, R.id.guessed_letters,
                R.id.remaining_guesses
        };

        for (int id: ids) {
            onView(withId(id)).check(matches(isDisplayed()));
        }
        onView(withId(R.id.game_result)).check(matches(not(isDisplayed())));
    }

    @Test
    public void whenUserLaunchesTheAppThenContentOfUIElementsIsDisplayedCorrectly() {
        String maskedWord = hangmanActivityTestRule.getActivity().getGame().getMaskedWord();
        onView(withId(R.id.hangman_title)).check(matches(withText("Hangman")));
        onView(withId(R.id.the_word_label)).check(matches(withText("The word:")));
        onView(withId(R.id.word_to_guess)).check(matches(withText(maskedWord)));
        onView(withId(R.id.hangman_image)).check(matches(withDrawable(R.drawable.hangman0)));
        onView(withId(R.id.text_field)).check(matches(withText("")));
        onView(withId(R.id.new_game_button)).check(matches(withText("New Game")));
        onView(withId(R.id.guess_button)).check(matches(withText("Guess")));
        onView(withId(R.id.guessed_letters)).check(matches(withText("You have guessed:")));
        onView(withId(R.id.remaining_guesses)).check(matches(withText("(10 guesses remaining)")));
    }
}
