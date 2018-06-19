package com.example.kimberleyfraser.tallybot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        Button tallyButton;
        tallyButton = (Button)findViewById(R.id.tallyButton);

        tallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TallyActivity.class);
                startActivity(intent);
            }
        });

        word = findViewById(R.id.word);
        submit = findViewById(R.id.submit);
        seeResults = findViewById(R.id.tallyButton);

    }
}
