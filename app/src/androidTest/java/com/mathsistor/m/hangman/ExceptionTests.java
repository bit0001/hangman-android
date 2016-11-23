package com.mathsistor.m.hangman;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

public class ExceptionTests {
    @Rule
    public ActivityTestRule<HangmanActivity> hangmanActivityTestRule =
            new ActivityTestRule<>(HangmanActivity.class);

    private HangmanActivity activity;

    @Before
    public void setUp() {
        activity = hangmanActivityTestRule.getActivity();
    }

    @Test
    public void whenPlayerGuessesWithoutInputAToastMessageIsDisplayed() {
        onView(withId(R.id.text_field)).perform(typeText(""));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withText(R.string.empty_guess)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void whenUserGuessesAnInvalidCharacterAToastMessageIsDisplayed() {
        onView(withId(R.id.text_field)).perform(typeText("1"));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withText(R.string.invalid_character)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void whenUserGuessesAWrongWordUIIsUpdated() {
        onView(withId(R.id.text_field)).perform(typeText("pptq"));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withText(R.string.wrong_guess)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.remaining_guesses)).check(matches(withText(
                "(" + (Hangman.MAX_GUESSES - 1) + activity.getResources().getString(R.string.guesses_left)
        )));
    }
}
