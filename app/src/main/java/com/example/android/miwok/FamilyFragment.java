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
public class FamilyFragment extends Fragment {
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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.root_view, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "epe", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "eta", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",
                R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "tete",
                R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama",
                R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",
                R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
