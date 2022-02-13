package com.cp.tools.antiafk.logic;

import com.cp.tools.antiafk.config.Configurer;
import com.cp.tools.antiafk.config.model.Configuration;
import com.cp.tools.antiafk.config.model.KeyboardButton;
import com.cp.tools.antiafk.config.model.MouseButton;
import com.github.javafaker.Faker;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class AntiAfkProcess {
    private static final Logger log = LoggerFactory.getLogger(AntiAfkProcess.class);

    private final Robot robot;
    private final Faker faker;
    private Configuration config;

    public AntiAfkProcess() throws JAXBException, AWTException {
        log.info("{}", MouseInfo.getNumberOfButtons());
        log.info("Starting program");
        configure();
        this.robot = new Robot();
        this.faker = new Faker();
    }

    private void configure() throws JAXBException {
        this.config = Configurer.configure();
        boolean isMouse = config.getMouseButtonToPress() != null;
        log.info("Configuration loaded - using {}", isMouse ? "MOUSE" : "KEYBOARD");
    }

    public void start() {
        log.info("Starting anti-afk process");
        log.info("Bring your game into the foreground, and enjoy your afk-free experience while you're away");
        while (true) {
            sleep();
            press();
        }
    }

    private void sleep() {
        long seconds = faker.number().numberBetween(config.getMinimumTimeBetweenExecutions(), config.getMaximumTimeBetweenExecutions() + 1L);
        log.info("Sleeping for {} second(s)", seconds);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("Thread was interrupted: ", e);
        }
    }

    private void press() {
        if (config.getMouseButtonToPress() != null) {
            MouseButton mb = config.getMouseButtonToPress();
            log.info("Pressing [{}] mouse button now", mb);
            robot.mousePress(InputEvent.getMaskForButton(mb.getKeyCode()));
            robot.delay(faker.number().numberBetween(10, 101));
            robot.mouseRelease(InputEvent.getMaskForButton(mb.getKeyCode()));
            return;
        }

        KeyboardButton key = config.getKeyToPress();
        log.info("Pressing [{}] key now", key);

        robot.keyPress(key.getKeyCode());
        robot.delay(faker.number().numberBetween(10, 101));
        robot.keyRelease(key.getKeyCode());
    }
}
