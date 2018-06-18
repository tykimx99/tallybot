package com.example.kimberleyfraser.tallybot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText word;
    Button submit;
    Button seeResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word = findViewById(R.id.word);
        submit = findViewById(R.id.submit);
        seeResults = findViewById(R.id.results);

        
    }
}
