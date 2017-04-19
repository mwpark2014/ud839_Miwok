package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mason on 4/16/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColor;

    //@param
    public WordAdapter(@NonNull Activity context, @NonNull ArrayList<Word> words, int color) {
        super(context, 0, words);
        mColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok);
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english);
        ImageView imgView = (ImageView) listItemView.findViewById(R.id.listImage);
        LinearLayout linLayout = (LinearLayout) listItemView.findViewById(R.id.linear_layout);

        int color = ContextCompat.getColor(getContext(), mColor);
        linLayout.setBackgroundColor(color);

        miwokTextView.setText(currentWord.getMiwok());
        englishTextView.setText(currentWord.getDefault());
        if(currentWord.hasImage())
            imgView.setImageResource(currentWord.getImageResourceId());
        else
            imgView.setVisibility(View.GONE);

        return listItemView;
    }
}
