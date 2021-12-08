package com.cp.tools.antiafk.config.model;

import java.awt.event.KeyEvent;

public enum KeyboardButton {
    SPACE(KeyEvent.VK_SPACE),
    ENTER(KeyEvent.VK_ENTER),
    BACKSPACE(KeyEvent.VK_BACK_SPACE),
    I(KeyEvent.VK_I),
    U(KeyEvent.VK_U);

    private final int keyCode;

    KeyboardButton(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
