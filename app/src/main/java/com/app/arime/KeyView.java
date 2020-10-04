package com.app.arime;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GestureDetectorCompat;


public class KeyView extends FrameLayout {
    private static final String TAG = "arime.KeyView";

    private KeyHandler keyHandler;
    private TextView hintText;
    private TextView keyText;
    private GestureDetectorCompat gestureDetector;
    private PopupWindow popup;
    private KeyPopup popupView;

    private String pressing;
    private String key;


    private String keyLongPressed;
    private String keySwipeUp;
    private String keySwipeDown;
    private String keySwipeLeft;
    private String keySwipeRight;

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
        keyHandler = KeyHandler.getHandler();
        this.setLayoutParams(new LayoutParams(convertDP2PX(40), convertDP2PX(40)));
        this.hintText = new TextView(getContext());
        this.keyText = new TextView(getContext());
        this.keyText.setGravity(Gravity.CENTER);
        this.hintText.setGravity(Gravity.TOP);
        this.addView(hintText);
        this.addView(keyText);
        popup = new PopupWindow();
        gestureDetector = new GestureDetectorCompat(getContext(), new KeyboardGestureDetector());
    }

    protected int convertDP2PX(int dp) {
        DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public void setKeyHeightAndWidth(int widthDP, int heightDP) {
        this.setLayoutParams(new LayoutParams(convertDP2PX(widthDP), convertDP2PX(heightDP)));
        popupView = new KeyPopup(getContext(), convertDP2PX(widthDP), convertDP2PX(heightDP));
    }

    public final TextView getHintText() {
        return hintText;
    }

    public void setHintText(String text) {
        hintText.setText(text);
    }

    public final TextView getKeyText() {
        return keyText;
    }

    public void setKeyText(String text) {
        keyText.setText(text);
        if (key == null) {
            key = text;
        }
    }

    // users might want to specify a key displayed as other things
    // 人 displayed but key 'o' pressed
    public void setKey(String key) {
        this.key = key;
    }

    public void setKeyLongPressed(String keyLongPressed) {
        this.keyLongPressed = keyLongPressed;
    }

    public void setKeySwipeUp(String keySwipeUp) {
        this.keySwipeUp = keySwipeUp;
    }

    public void setKeySwipeDown(String keySwipeDown) {
        this.keySwipeDown = keySwipeDown;
    }

    public void setKeySwipeLeft(String keySwipeLeft) {
        this.keySwipeLeft = keySwipeLeft;
    }

    public void setKeySwipeRight(String keySwipeRight) {
        this.keySwipeRight = keySwipeRight;
    }

    public void setKeyTextStyle(int sizeDIP, int color, Typeface tf) {
        keyText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeDIP);
        keyText.setTypeface(tf);
        keyText.setTextColor(color);
        keyText.setGravity(Gravity.CENTER);
    }

    public String getCurrentPressing() {
        return pressing;
    }

    public void setHintTextStyle(int sizeDIP, int color, int gravity, Typeface tf) {
        hintText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeDIP);
        hintText.setTypeface(tf);
        hintText.setTextColor(color);
        hintText.setGravity(Gravity.CENTER);
        hintText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, gravity));
    }

    public void setKeyBorder(Drawable border) {
        this.setBackground(border);
    }

    public void showPopup() {
        Log.d(TAG, "showPopup: ");
        // TODO: 20年10月4日 here big boom boom !!!! cannot use popup window damn
        // popup.showAtLocation(popupView, Gravity.CENTER, (int) this.getX(), (int) this.getY());
        Log.d(TAG, "showPopup: popup" + popup.toString());
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
                commitKey(getCurrentPressing());
            default:
                return gestureDetector.onTouchEvent(event);
        }

    }

    protected void commitKey(String currentPressing) {
        keyHandler.handleKey(currentPressing);
    }

    protected class KeyPopup extends LinearLayoutCompat {

        private GradientDrawable keyBorder;
        private GradientDrawable selectedBorder;
        private FrameLayout hintContainer;
        private int colorSelected;
        private TextView hintL;
        private TextView hintR;
        private TextView hintU;
        private TextView hintD;
        private TextView hintC;
        private TextView hintSelected;

        private boolean isLongPressed = false;

        public KeyPopup(@NonNull Context context, int keyWidth, int keyHeight) {
            super(context);
            init(keyWidth, keyHeight);
        }

        private void init(int keyWidth, int keyHeight) {
            this.setLayoutParams(new LinearLayoutCompat.LayoutParams(keyWidth, keyHeight));
            hintContainer = new FrameLayout(getContext());
            // Key hint of popup on top half of popup
            hintContainer.setLayoutParams(new FrameLayout.LayoutParams(keyWidth, keyHeight));
            keyBorder = new GradientDrawable();
            selectedBorder = new GradientDrawable();
            setupDefaultBorder(keyWidth, keyHeight);

            this.setBackground(keyBorder);
            //init textView
            hintL = generateTextView();
            hintR = generateTextView();
            hintU = generateTextView();
            hintD = generateTextView();
            hintC = generateTextView();
            initHintTextStyle();
            hintContainer.addView(hintL);
            hintContainer.addView(hintR);
            hintContainer.addView(hintU);
            hintContainer.addView(hintD);
            hintContainer.addView(hintC);

            hintSelected = generateTextView();
            hintSelected.setVisibility(INVISIBLE);
            this.addView(hintSelected);
        }

        /**
         * @param width
         * @param height ________         ____________
         *               | PopUp |        |Some Color |
         *               |       |    ->  |  Pressed  |
         *               |_______|        |___________|
         *               ______
         *               | key |
         *               -------
         */
        protected void setupDefaultBorder(int width, int height) {
            keyBorder.setSize(width, height);
            selectedBorder.setSize(width, height);
            setKeyBorderStyle(0xFF242424, convertDP2PX(6));
            setSelectedBorderStyle(0xFF0002BE);
        }

        /**
         * generate default sytle of hint textview
         *
         * @return TextView
         */
        private TextView generateTextView() {
            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            tv.setLayoutParams(params);
            setHintStyle(tv, Color.WHITE, 1.0f, null);
            return tv;
        }

        private void initHintTextStyle() {
            /** set hint width and height and  layout is somehow like
             *      ____________________
             *     |        Up          |
             *     |____________________|
             *     |    |            | R|
             *     | L  |            | I|
             *     | E  |   CENTER   | G|
             *     | F  |            | H|
             *     | T  |            | T|
             *     |____|____________|__|
             *     |         Down       |
             *     |____________________|
             */
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) hintU.getLayoutParams();
            params.gravity |= Gravity.TOP;
            params.height = hintContainer.getHeight() / 4;
            hintU.setLayoutParams(params);
            hintU.setAlpha(0.8f);

            params = (FrameLayout.LayoutParams) hintD.getLayoutParams();
            params.gravity |= Gravity.BOTTOM;
            params.height = hintContainer.getHeight() / 4;
            hintD.setLayoutParams(params);
            hintD.setAlpha(0.8f);

            params = (FrameLayout.LayoutParams) hintL.getLayoutParams();
            params.gravity |= Gravity.LEFT;
            params.width = hintContainer.getWidth() / 4;
            params.height = hintContainer.getHeight() / 2;
            hintL.setLayoutParams(params);
            hintL.setAlpha(0.8f);

            params = (FrameLayout.LayoutParams) hintR.getLayoutParams();
            params.gravity |= Gravity.RIGHT;
            params.width = hintContainer.getWidth() / 4;
            params.height = hintContainer.getHeight() / 2;
            hintR.setLayoutParams(params);
            hintR.setAlpha(0.8f);

            params = (FrameLayout.LayoutParams) hintC.getLayoutParams();
            params.width = hintContainer.getWidth() / 2;
            params.height = hintContainer.getHeight() / 2;
            hintC.setLayoutParams(params);

        }

        protected void setHintStyle(TextView tv, int textColor, float alpha, @Nullable Typeface tf) {
            tv.setTypeface(tf);
            tv.setTextColor(textColor);
            tv.setAlpha(alpha);
            tv.setAutoSizeTextTypeUniformWithConfiguration(8, 100, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM, TypedValue.COMPLEX_UNIT_DIP);
        }

        public void setKeyBorderStyle(int backgroundColor, float raidusPX) {
            keyBorder.setSize(this.getWidth(), this.getHeight());
            keyBorder.setCornerRadius(raidusPX);
            keyBorder.setColor(backgroundColor);
        }

        public void setSelectedBorderStyle(int backgroundColor) {
            selectedBorder.setSize(this.getWidth(), this.getHeight());
            selectedBorder.setCornerRadius(keyBorder.getCornerRadius());
            selectedBorder.setColor(backgroundColor);
            colorSelected = backgroundColor;
        }

        public void setHintStyle(int textColor, @Nullable Typeface tf) {
            setHintStyle(hintC, textColor, 1.0f, tf);
            setHintStyle(hintL, textColor, 0.8f, tf);
            setHintStyle(hintR, textColor, 0.8f, tf);
            setHintStyle(hintU, textColor, 0.8f, tf);
            setHintStyle(hintD, textColor, 0.8f, tf);
            setHintStyle(hintSelected, 0xFF000000 | ((~(colorSelected << 2) + 1) >> 2), 1.0f, tf);
        }

        /**
         * for popup key hint
         */
        public void setHint(String center, String left, String right, String up, String down) {
            hintC.setText(center);
            hintL.setText(left);
            hintR.setText(right);
            hintU.setText(up);
            hintD.setText(down);
        }

        public void longPressed() {
            hintContainer.setVisibility(INVISIBLE);
            hintSelected.setVisibility(VISIBLE);
            this.setBackground(selectedBorder);
            isLongPressed = true;
        }

        protected void resetView() {
            hintContainer.setVisibility(VISIBLE);
            hintSelected.setVisibility(INVISIBLE);
            this.setBackground(keyBorder);
            isLongPressed = false;
        }
    }

    protected class KeyboardGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            pressing = key;
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
