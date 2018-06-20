package com.example.kimberleyfraser.tallybot;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText word;
    Button submit;
    Button seeResults;
    DatabaseRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button tallyButton;
        tallyButton = (Button)findViewById(R.id.tallyButton);

        word = (EditText) findViewById(R.id.word);
        db = new DatabaseRepository();

        tallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TallyActivity.class);
                startActivity(intent);
            }
        });

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateWord(word.getText().toString());
                word.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Success").setMessage("Word Saved.").create().show();
            }
        });

        word = findViewById(R.id.word);
        submit = findViewById(R.id.submit);
        seeResults = findViewById(R.id.tallyButton);

    }
}
