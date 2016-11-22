package com.mathsistor.m.hangman;

import android.support.test.rule.ActivityTestRule;

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

    @Test
    public void whenUserGuessesALetterTextFieldIsClean() {
        onView(withId(R.id.text_field)).perform(typeText("a"));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.text_field)).check(matches(withText("")));
    }

}
