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

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_view);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "wetetti", R.raw.color_red));
        words.add(new Word("green", "chokokki", R.raw.color_green));
        words.add(new Word("brown", "takaakki", R.raw.color_brown));
        words.add(new Word("gray", "topoppi", R.raw.color_gray));
        words.add(new Word("black", "kululli", R.raw.color_black));
        words.add(new Word("white", "kelelli", R.raw.color_white));
        words.add(new Word("dusty yellow", "topiise", R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiite", R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listview = (ListView)findViewById(R.id.rootView);
        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        word.getAudioResourceId());
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        });

    }
}
