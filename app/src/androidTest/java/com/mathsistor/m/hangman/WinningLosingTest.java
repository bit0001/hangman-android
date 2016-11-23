package com.mathsistor.m.hangman;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class WinningLosingTest {
    @Rule
    public ActivityTestRule<HangmanActivity> hangmanActivityTestRule =
            new ActivityTestRule<>(HangmanActivity.class);

    private HangmanActivity activity;

    @Before
    public void setUp() {
        activity = hangmanActivityTestRule.getActivity();
    }

    @Test
    public void whenUserGuessTheWordUIIsUpdated() {
        String word = activity.getGame().getWord();
        for (Character c: word.toCharArray()) {
            onView(withId(R.id.text_field)).perform(typeText(String.valueOf(c)));
            onView(withId(R.id.guess_button)).perform(click());
        }
        onView(withId(R.id.word_to_guess)).check(matches(withText(word)));
        onView(withId(R.id.game_result)).check(matches(isDisplayed()));
        onView(withId(R.id.game_result)).check(matches(withText(activity.getString(R.string.you_did_it))));
    }
}
