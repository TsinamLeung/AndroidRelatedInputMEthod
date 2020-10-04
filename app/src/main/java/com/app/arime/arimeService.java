package com.app.arime;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;

public class arimeService extends android.inputmethodservice.InputMethodService {
    @Override
    public View onCreateInputView() {

        KeyboardView kv = new KeyboardView(getApplicationContext());
        kv.setOrientation(LinearLayoutCompat.VERTICAL);
        RowView row = new RowView(getApplicationContext());
        KeyView key = new KeyView(getApplicationContext());
        key.setKeyText("Hello");
        key.setKey("h");
        key.setKeyTextStyle(12, Color.BLUE, null);
        key.setKeyHeightAndWidth(30, 40);

        GradientDrawable border = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.roundedborder);
        border.setCornerRadius(20);
        border.setColor(0xBB00FFFF);

        key.setKeyBorder(border);

        KeyView key2 = new KeyView(getApplicationContext());
        key2.setKeyText("Back");
        key2.setKeyTextStyle(8, Color.GREEN, null);
        key2.setKeyHeightAndWidth(40, 40);
        border.setColor(0xAAFFA822);
        key2.setKeyBorder(border);

        row.addView(key);
        row.addView(key2);
        kv.addView(row);
        return kv;
    }
}
