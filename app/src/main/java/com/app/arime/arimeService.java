package com.app.arime;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

public class arimeService extends android.inputmethodservice.InputMethodService {
    @Override
    public View onCreateInputView() {

        KeyboardView kv = new KeyboardView(getApplicationContext());
        kv.setOrientation(LinearLayoutCompat.VERTICAL);
        KeyView key = new KeyView(getApplicationContext());
        TextView keyText = key.getKeyText();
        TextView hintText = key.getHintText();
        keyText.setText("Hello");
        hintText.setText("world");
        hintText.setGravity(Gravity.BOTTOM | Gravity.END);
        keyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        hintText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        keyText.setTextColor(Color.CYAN);
        hintText.setTextColor(Color.BLUE);
        hintText.setBackgroundColor(Color.BLACK);
        KeyView key2 = new KeyView(getApplicationContext());
        key2.getKeyText().setText("World");
        key2.setBackgroundColor(Color.GREEN);

        kv.addView(key);
        kv.addView(key2);
        return kv;
    }
}
