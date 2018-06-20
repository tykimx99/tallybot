package com.example.kimberleyfraser.tallybot;

import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TallyActivity extends AppCompatActivity {

    private ListView mainListView ;
    private List<ListItem> wordsList;
    DatabaseReference db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally);
        Button enterInfo = findViewById(R.id.enterInfo);
        db = FirebaseDatabase.getInstance().getReference().child("words");

        mainListView = (ListView) findViewById(R.id.tallyList);
        wordsList = new ArrayList<>();

        enterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TallyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                wordsList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    ListItem word = new ListItem(snapshot.getKey().toString(), dataSnapshot.child(snapshot.getKey().toString()).getValue(WordModel.class).instances, dataSnapshot.child(snapshot.getKey().toString()).getValue(WordModel.class).archived);
                    if(!word.archived) {
                        wordsList.add(word);
                    }
                }


                // sort list  List<T> list, Comparator<? super T> c
                Collections.sort(wordsList);
                Collections.reverse(wordsList);
                ListAdapter adapter = new ListAdapter(TallyActivity.this, wordsList);
                mainListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ListItem archiveWord = wordsList.get(position);
                archiveWord.archived = true;

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        WordModel newWord;
                        int tmp = dataSnapshot.child(archiveWord.word).getValue(WordModel.class).instances;
                        newWord = new WordModel(tmp, true);
                        db.child(archiveWord.word).setValue(newWord);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
