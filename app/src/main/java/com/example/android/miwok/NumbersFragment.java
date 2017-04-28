package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;
    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {@Override public void
            onAudioFocusChange(int focusChange){
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        mMediaPlayer.start();
                        // Set volume level to desired levels
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        mMediaPlayer.stop();
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        mMediaPlayer.pause();
                        //mMediaPlayer.seekTo(0);
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        mMediaPlayer.pause();
                        //mMediaPlayer.seekTo(0);
                        break;
                }
            }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.root_view, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
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


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listview = (ListView)rootView.findViewById(R.id.rootView);
        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word)parent.getAdapter().getItem(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(),
                            word.getAudioResourceId());
                    mMediaPlayer.start(); // no need to call prepare(); create() does that for you

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mMediaPlayer = null;
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
