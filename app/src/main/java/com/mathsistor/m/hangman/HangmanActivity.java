package com.mathsistor.m.hangman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.game_result)
    TextView gameResult;

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
        try {
            game.guess(editText.getText().toString());
        } catch (RuntimeException e) {
            String message = e.getMessage();

            switch (message) {
                case "Empty string":
                    Toast.makeText(this, R.string.empty_guess, Toast.LENGTH_SHORT).show();
                    break;
                case "Invalid character":
                    Toast.makeText(this, R.string.invalid_character, Toast.LENGTH_SHORT).show();
                    break;
                case "Incorrect word guess":
                    Toast.makeText(this, R.string.wrong_guess, Toast.LENGTH_SHORT).show();
                    break;
                case "Game is over":
                    Toast.makeText(this, R.string.game_is_over, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        updateUI();
        editText.setText("");

        if (game.isGameOver()) {
            gameResult.setVisibility(View.VISIBLE);
            if (game.getWord().equals(game.getMaskedWord())) {
                gameResult.setText(R.string.you_did_it);
            } else {
                gameResult.setText(R.string.you_lost);
            }
        }
    }

    public Hangman getGame() {
        return game;
    }

    public void newGame(View view) {
        game = new Hangman("word");
        updateUI();
    }
}
