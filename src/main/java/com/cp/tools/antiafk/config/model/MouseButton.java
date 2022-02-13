package com.cp.tools.antiafk.config.model;

import java.awt.event.MouseEvent;

public enum MouseButton {
    LMB(MouseEvent.BUTTON1);

    private final int keyCode;

    MouseButton(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
