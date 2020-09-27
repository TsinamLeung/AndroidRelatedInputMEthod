package com.app.arime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;


public class KeyView extends FrameLayout {
    private static final String TAG = "arime.KeyView";
    private TextView hintText;
    private TextView keyText;
    private GestureDetectorCompat gestureDetector;

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
        Log.d(TAG, "showLongpress: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: Action UP!!!");
            default:
                return gestureDetector.onTouchEvent(event);
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
            Log.d(TAG, "onScroll: start:" + e1.toString());
            Log.d(TAG, "onScroll: distanceX " + distanceX + " DistanceY: " + distanceY);
            Log.d(TAG, "onScroll: end: " + e2.toString());
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
