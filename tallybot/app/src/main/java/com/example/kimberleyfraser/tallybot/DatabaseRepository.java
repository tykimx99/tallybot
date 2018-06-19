package com.example.kimberleyfraser.tallybot;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class DatabaseRepository {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("words");

    public void updateWord(final String word) {

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WordModel newWord;
                if(dataSnapshot.child(word).getValue(WordModel.class) == null) {
                    newWord = new WordModel(1, false);
                } else {

                    int tmp = dataSnapshot.child(word).getValue(WordModel.class).instances;
                    newWord = new WordModel(tmp+1, false);
                }
                mDatabase.child(word).setValue(newWord);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
