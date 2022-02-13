package com.cp.tools.antiafk.util;

import com.cp.tools.antiafk.config.model.KeyboardButton;
import com.cp.tools.antiafk.config.model.MouseButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class SystemInputProcessor {
    private static final Logger log = LoggerFactory.getLogger(SystemInputProcessor.class);
    private final Scanner scan;

    public SystemInputProcessor() {
        this.scan = new Scanner(System.in);
    }

    public int nextPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scan.next();

            int num;
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                log.error("Input was not a number. Try again.");
                continue;
            }

            if (num < 0) {
                log.error("Input was negative. Try again.");
                continue;
            }

            return num;
        }
    }

    public int nextPositiveIntLargerThanOrEqualTo(String message, int a) {
        while (true) {
            int num = nextPositiveInt(message);

            if (num < a) {
                log.error("Input was lower than expected. Try again.");
                continue;
            }

            return num;
        }
    }

    public String nextString(String message) {
        while (true) {
            System.out.print(message);
            String input = scan.next();

            if (input.trim().isEmpty()) {
                log.error("Input was empty. Try again.");
                continue;
            }

            return input.trim();
        }
    }

    public KeyboardButton nextKeyboardButton(String message) {
        while (true) {
            String input = nextString(message);

            KeyboardButton btn;
            try {
                btn = KeyboardButton.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException iae) {
                log.error("Not a valid option. Try again.");
                continue;
            }

            return btn;
        }
    }

    public MouseButton nextMouseButton(String message) {
        while (true) {
            String input = nextString(message);

            MouseButton btn;
            try {
                btn = MouseButton.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException iae) {
                log.error("Not a valid option. Try again.");
                continue;
            }

            return btn;
        }
    }

    public boolean nextYesNoDecision(String message) {
        while (true) {
            String input = nextString(message);

            switch (input.toLowerCase()) {
                case "yes":
                    return true;
                case "no":
                    return false;
                default:
                    log.error("Not a valid option. Try again.");
                    break;
            }
        }
    }

    public void close() {
        scan.close();
    }
}
