package com.app.arime;

import android.util.Log;

public class KeyHandler {

    private static final String TAG = "KeyHandler";
    private static KeyHandler instance;

    public static final KeyHandler getHandler() {
        if (instance == null) {
            instance = new KeyHandler();
        }
        return instance;
    }

    public void handleKey(String input) {
        Log.d(TAG, "handleKey: " + input);
    }
}
