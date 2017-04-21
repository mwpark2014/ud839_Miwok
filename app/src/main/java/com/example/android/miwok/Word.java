package com.example.android.miwok;

import android.support.annotation.NonNull;

/**
 * Created by Mason on 4/16/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId = NO_AUDIO_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_AUDIO_PROVIDED = -1;

    //@param miwok
    //@param english
    public Word (String miwok, String english, int audioResourceId) {
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mAudioResourceId = audioResourceId;
    }

    //@param miwok
    //@param english
    //@param imageResourceId
    public Word (String miwok, String english, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    @NonNull
    public String getMiwok() {
        return mMiwokTranslation;
    }

    @NonNull
    public String getDefault() {
        return mDefaultTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasImage() {return mImageResourceId != NO_IMAGE_PROVIDED;}
}
