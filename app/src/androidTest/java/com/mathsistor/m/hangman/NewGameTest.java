package com.mathsistor.m.hangman;

import android.support.annotation.NonNull;
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
import static org.hamcrest.Matchers.not;

public class NewGameTest {

    @Rule
    public ActivityTestRule<HangmanActivity> hangmanActivityTestRule =
            new ActivityTestRule<>(HangmanActivity.class);

    private HangmanActivity activity;

    @Before
    public void setUp() {
        activity = hangmanActivityTestRule.getActivity();
    }

    @Test
    public void givenUserIsPlayingWhenClickingNewGameANewGameIsStarted() {
        String character = String.valueOf(activity.getGame().getWord().charAt(0));
        onView(withId(R.id.text_field)).perform(typeText(character));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        onView(withId(R.id.word_to_guess)).check(matches(withText(getMaskedWord())));
    }

    @Test
    public void givenPlayerHasWonAGameWhenHePressesNewGameUIIsUpdatedProperly() {
        String word = activity.getGame().getWord();
        onView(withId(R.id.text_field)).perform(typeText(word));
        onView(withId(R.id.guess_button)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        onView(withId(R.id.game_result)).check(matches(not(isDisplayed())));
    }

    @NonNull
    private String getMaskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (int i = 0; i < activity.getGame().getWord().length(); i++) {
            maskedWord.append("?");
        }

        return maskedWord.toString();
    }

}
