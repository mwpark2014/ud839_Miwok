/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_view);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "lutti", R.raw.number_one));
        words.add(new Word("Two", "otiiko", R.raw.number_two));
        words.add(new Word("Three", "tolookosu",R.raw.number_three));
        words.add(new Word("Four", "oyyisa", R.raw.number_four));
        words.add(new Word("Five", "massokka", R.raw.number_five));
        words.add(new Word("Six", "temmokka", R.raw.number_six));
        words.add(new Word("Seven", "kenekaku", R.raw.number_seven));
        words.add(new Word("Eight", "kawinta", R.raw.number_eight));
        words.add(new Word("Nine", "wo'e", R.raw.number_nine));
        words.add(new Word("Ten", "na'aacha", R.raw.number_ten));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listview = (ListView)findViewById(R.id.rootView);
        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word)parent.getAdapter().getItem(position);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        word.getAudioResourceId());
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        });
    }
}
