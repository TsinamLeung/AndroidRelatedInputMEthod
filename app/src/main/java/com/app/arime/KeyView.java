package com.app.arime;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KeyView extends FrameLayout {
    public KeyView(@NonNull Context context) {
        super(context);
        init();
    }

    public KeyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }
}
