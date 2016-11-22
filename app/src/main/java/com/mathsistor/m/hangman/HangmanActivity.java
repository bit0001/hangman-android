package com.mathsistor.m.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HangmanActivity extends AppCompatActivity {

    @BindView(R.id.text_field)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        ButterKnife.bind(this);
    }

    public void guess(View view) {
        editText.setText("");
    }
}
