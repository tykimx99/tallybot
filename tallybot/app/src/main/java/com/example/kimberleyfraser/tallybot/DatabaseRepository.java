package com.example.kimberleyfraser.tallybot;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class DatabaseRepository {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("words");

    public void updateWord(String word) {
        WordModel newWord = new WordModel(-1, false);
        mDatabase.child(word).setValue(newWord);
        mDatabase.child("jkl;").setValue(newWord);

        mDatabase.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                WordModel updateWord = mutableData.getValue(WordModel.class);
                if(updateWord == null){
                    return Transaction.success(mutableData);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

            }
        });
    }


            //addValueEventListener(postListener);
}
