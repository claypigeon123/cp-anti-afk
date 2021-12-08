package com.cp.tools.antiafk.config;

import com.cp.tools.antiafk.config.model.Configuration;
import com.cp.tools.antiafk.config.model.KeyboardButton;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Configurer {
    private static final String CONFIG_FILENAME = "config.xml";
    private static final Logger log = LoggerFactory.getLogger(Configurer.class);

    public static Configuration configure() throws JAXBException {
        File config = new File(CONFIG_FILENAME);
        if (!config.exists()) {
            return setupConfig();
        }

        return loadConfig(config);
    }

    private static Configuration setupConfig() throws JAXBException {
        log.info("No configuration found - starting guided setup");
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the minimum amount of time to wait between executions (in seconds): ");
        int minTime = scan.nextInt();

        System.out.print("Enter the maximum amount of time to wait between executions (in seconds): ");
        int maxTime = scan.nextInt();

        System.out.print("Enter the key to press (valid options are SPACE, BACKSPACE, ENTER, I, U): ");
        KeyboardButton key = KeyboardButton.valueOf(scan.next());

        Configuration config = new Configuration(minTime, maxTime, key);
        writeConfig(config);

        return config;
    }

    private static void writeConfig(Configuration config) throws JAXBException {
        Marshaller out = JAXBContext.newInstance(Configuration.class).createMarshaller();
        out.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        out.marshal(config, new File(CONFIG_FILENAME));
    }

    private static Configuration loadConfig(File file) throws JAXBException {
        Unmarshaller in = JAXBContext.newInstance(Configuration.class).createUnmarshaller();
        return (Configuration) in.unmarshal(file);
    }
}
