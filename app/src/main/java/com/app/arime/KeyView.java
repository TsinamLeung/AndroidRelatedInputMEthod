package com.app.arime;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class KeyView extends FrameLayout {
    private AppCompatTextView hintText;
    private AppCompatTextView keyText;

    public KeyView(@NonNull Context context) {
        super(context);
        init();
    }

    public KeyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setLayoutParams(new LayoutParams(convertDP2PX(40), convertDP2PX(40)));

        this.hintText = new AppCompatTextView(getContext());
        this.keyText = new AppCompatTextView(getContext());
        this.keyText.setGravity(Gravity.CENTER);
        this.hintText.setGravity(Gravity.TOP);
        this.addView(hintText);
        this.addView(keyText);
    }

    private int convertDP2PX(int dp) {
        DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public final AppCompatTextView getHintText() {
        return hintText;
    }

    public final AppCompatTextView getKeyText() {
        return keyText;
    }
}
