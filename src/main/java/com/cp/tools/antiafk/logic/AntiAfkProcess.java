package com.cp.tools.antiafk.logic;

import com.cp.tools.antiafk.config.Configurer;
import com.cp.tools.antiafk.config.model.Configuration;
import com.cp.tools.antiafk.config.model.KeyboardButton;
import com.github.javafaker.Faker;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class AntiAfkProcess {
    private static final Logger log = LoggerFactory.getLogger(AntiAfkProcess.class);

    private final Robot robot;
    private final Faker faker;
    private Configuration config;

    public AntiAfkProcess() throws JAXBException, AWTException {
        configure();
        this.robot = new Robot();
        this.faker = new Faker();
    }

    private void configure() throws JAXBException {
        log.info("Starting program");
        this.config = Configurer.configure();
        log.info("Configuration loaded");
    }

    public void start() {
        log.info("Starting anti-afk process");
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
        KeyboardButton key = config.getKeyToPress();
        log.info("Pressing [{}] key now", key);

        robot.keyPress(key.getKeyCode());
        robot.keyRelease(key.getKeyCode());
    }
}
