package com.mathsistor.m.hangman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HangmanActivity extends AppCompatActivity {

    @BindView(R.id.text_field)
    EditText editText;

    @BindView(R.id.word_to_guess)
    TextView wordToGuess;

    @BindView(R.id.guessed_letters)
    TextView guessedLetters;

    @BindView(R.id.remaining_guesses)
    TextView guessesLeft;

    private Hangman game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        ButterKnife.bind(this);
        game = new Hangman("word");
        updateUI();
    }

    private void updateUI() {
        wordToGuess.setText(game.getMaskedWord());
        guessedLetters.setText(
                getGuessedLettersLabelText());
        guessesLeft.setText("(" + game.getGuessesLeft() +  getString(R.string.guesses_left));
    }

    @NonNull
    private String getGuessedLettersLabelText() {
        String preamble = getResources().getString(R.string.you_have_guessed);
        return preamble + (game.getGuessedLetters().isEmpty() ? "" : " " + StringUtils.join(game.getGuessedLetters(), ", ") + ".");
    }

    public void guess(View view) {
        game.guess(editText.getText().toString());
        updateUI();
        editText.setText("");
    }

    public Hangman getGame() {
        return game;
    }

}
