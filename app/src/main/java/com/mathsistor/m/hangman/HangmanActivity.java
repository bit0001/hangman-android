package com.mathsistor.m.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HangmanActivity extends AppCompatActivity {

    @BindView(R.id.text_field)
    EditText editText;

    @BindView(R.id.word_to_guess)
    TextView wordToGuess;

    private Hangman game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        ButterKnife.bind(this);
        game = new Hangman("word");
        wordToGuess.setText(game.getMaskedWord());
    }

    public void guess(View view) {
        editText.setText("");
    }

    public Hangman getGame() {
        return game;
    }
}
