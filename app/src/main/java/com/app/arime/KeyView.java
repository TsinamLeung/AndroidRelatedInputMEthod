package com.app.arime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class KeyView extends FrameLayout {
    private TextView hintText;
    private TextView keyText;

    public KeyView(@NonNull Context context) {
        super(context);
        init();
    }

    public KeyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Default Layout settings, could be rewrite
        this.setLayoutParams(new LayoutParams(convertDP2PX(40), convertDP2PX(40)));
        this.hintText = new TextView(getContext());
        this.keyText = new TextView(getContext());
        this.keyText.setGravity(Gravity.CENTER);
        this.hintText.setGravity(Gravity.TOP);
        this.addView(hintText);
        this.addView(keyText);
    }

    private int convertDP2PX(int dp) {
        DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public void setKeyHeightAndWidth(int widthDP, int heightDP) {
        this.setLayoutParams(new LayoutParams(convertDP2PX(widthDP), convertDP2PX(heightDP)));
    }

    public final TextView getHintText() {
        return hintText;
    }

    public final TextView getKeyText() {
        return keyText;
    }

    public void setKeyBorder(Drawable border) {
        this.setBackground(border);
    }
}
