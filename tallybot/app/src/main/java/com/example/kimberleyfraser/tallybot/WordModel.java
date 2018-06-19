package com.example.kimberleyfraser.tallybot;

public class WordModel {
    public int instances;
    public boolean archived;

    public WordModel() {

    }

    public WordModel(int instances, boolean archived){
        this.instances = instances;
        this.archived = archived;
    }
}
