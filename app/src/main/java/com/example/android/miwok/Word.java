package com.example.android.miwok;

import android.support.annotation.NonNull;

/**
 * Created by Mason on 4/16/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;

    //@param miwok
    //@param english
    public Word (String miwok, String english) {
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
    }

    @NonNull
    public String getMiwok() {
        return mMiwokTranslation;
    }

    @NonNull
    public String getDefault() {
        return mDefaultTranslation;
    }
}
