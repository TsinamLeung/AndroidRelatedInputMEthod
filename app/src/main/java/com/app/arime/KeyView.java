package com.app.arime;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GestureDetectorCompat;


public class KeyView extends FrameLayout {
    private static final String TAG = "arime.KeyView";
    private TextView hintText;
    private TextView keyText;
    private GestureDetectorCompat gestureDetector;
    private PopupWindow popup;


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
        gestureDetector = new GestureDetectorCompat(getContext(), new KeyboardGestureDetector());
    }

    protected int convertDP2PX(int dp) {
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

    public void showPopup() {
        Log.d(TAG, "showPopup: ");
    }

    public void hidePopup() {
        Log.d(TAG, "hidePopup: ");
    }

    public void showLongpress() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

            default:
                return gestureDetector.onTouchEvent(event);
        }

    }

    protected class KeyPopup extends LinearLayoutCompat {

        private GradientDrawable border;
        private GradientDrawable seletedBorder;
        private FrameLayout showup;
        private TextView hintL;
        private TextView hintR;
        private TextView hintU;
        private TextView hintD;
        private TextView hintC;
        private TextView selected;


        public KeyPopup(@NonNull Context context, int keyWidth, int keyHeight) {
            super(context);
            init(keyWidth, keyHeight);
        }

        private void init(int keyWidth, int keyHeight) {
            this.setLayoutParams(new LinearLayoutCompat.LayoutParams(keyWidth, keyHeight));
            showup = new FrameLayout(getContext());
            // Key hint of popup on top half of popup
            showup.setLayoutParams(new FrameLayout.LayoutParams(keyWidth, keyHeight));
            border = new GradientDrawable();
            seletedBorder = new GradientDrawable();
            this.setBackground(border);
            //init textView
            hintL = new TextView(getContext());
            hintR = new TextView(getContext());
            hintU = new TextView(getContext());
            hintD = new TextView(getContext());
            hintC = new TextView(getContext());
            selected = new TextView(getContext());
        }

        protected void setHintStyle(TextView tv, int tSize, int tUnit, int textColor, @Nullable Typeface tf) {
            tv.setTypeface(tf);
            tv.setTextColor(textColor);
            tv.setTextSize(tSize, tUnit);
            tv.setAutoSizeTextTypeUniformWithConfiguration(tSize / 2, tSize, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM, tUnit);
        }

        public void setBorderStyle(int backgroundColor, float raidusPX) {
            border.setSize(this.getWidth(), this.getHeight());
            border.setCornerRadius(raidusPX);
            border.setColor(backgroundColor);
        }

        public void setSeletedStyle(int seletedColor) {
            seletedBorder.setCornerRadius(border.getGradientRadius());
            seletedBorder.setColor(seletedColor);
            seletedBorder.setVisible(false, false);
        }

        /**
         * for popup key hint
         */
        public void setHint(String center, String left, String right, String up, String down) {
            boolean isSingle = !(left.isEmpty() | right.isEmpty() | up.isEmpty() | down.isEmpty());
            if (isSingle) {

            } else {

            }
        }

        public void longPressed() {
            // TODO: 20年10月4日 show seleted after long pressed
        }
    }

    protected class KeyboardGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            showPopup();
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            showLongpress();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    }
}
