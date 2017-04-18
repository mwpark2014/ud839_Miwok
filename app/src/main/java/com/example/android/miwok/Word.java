package com.example.android.miwok;

import android.support.annotation.NonNull;

/**
 * Created by Mason on 4/16/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static int NO_IMAGE_PROVIDED = -1;

    //@param miwok
    //@param english
    public Word (String miwok, String english) {
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
    }

    //@param miwok
    //@param english
    //@param imageResourceId
    public Word (String miwok, String english, int imageResourceId) {
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceId = imageResourceId;
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

    public boolean hasImage() {return mImageResourceId == NO_IMAGE_PROVIDED;}
}
