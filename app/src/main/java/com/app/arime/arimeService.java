package com.app.arime;

import android.view.View;

public class arimeService extends android.inputmethodservice.InputMethodService {
    @Override
    public View onCreateInputView() {
        KeyboardView kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        return kv;
    }
}
