package com.example.kimberleyfraser.tallybot;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ListItem> {

    private Activity context;
    private List<ListItem> wordsList;

    public ListAdapter(Activity context, List<ListItem> items) {
        super(context, R.layout.tally_item, items);
        this.context = context;
        this.wordsList = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.tally_item, null, true);

        TextView textViewTitle = listViewItem.findViewById(R.id.mTitle);
        TextView textViewCount = listViewItem.findViewById(R.id.mCount);

        ListItem item = wordsList.get(position);
        textViewTitle.setText(item.word);
        textViewCount.setText(Integer.toString(item.count));

        return listViewItem;
    }
}
