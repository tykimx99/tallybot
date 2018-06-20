package com.example.kimberleyfraser.tallybot;

public class ListItem implements Comparable<ListItem> {
    public String word;
    public int count;
    public boolean archived;

    public ListItem(String w, int c, boolean a) {
        word = w;
        count = c;
        archived = a;
    }

    @Override
    public  int compareTo(ListItem other) {
        if (this.count < other.count ) {
            return -1;
        } else {
            return 1;
        }
    }
}
