package com.app.arime;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.LinearLayoutCompat;

public class KeyboardView extends LinearLayoutCompat {
    private static final String TAG = "arime.KeyboardView";


    public KeyboardView(Context context) {
        super(context);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Log.d(TAG, "Init()");
    }
}
