package com.cp.tools.antiafk;

import com.cp.tools.antiafk.logic.AntiAfkProcess;
import jakarta.xml.bind.JAXBException;

import java.awt.*;

public class AppRunner {
    public static void main(String[] args) throws JAXBException, AWTException {
        AntiAfkProcess process = new AntiAfkProcess();

        process.start();
    }
}
