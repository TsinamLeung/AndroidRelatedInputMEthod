package com.app.arime;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

public class RowView extends LinearLayoutCompat {
    public RowView(Context context) {
        super(context);
        init();
    }

    public RowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setOrientation(HORIZONTAL);
    }

}
