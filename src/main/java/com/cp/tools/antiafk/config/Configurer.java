package com.cp.tools.antiafk.config;

import com.cp.tools.antiafk.config.model.Configuration;
import com.cp.tools.antiafk.config.model.KeyboardButton;
import com.cp.tools.antiafk.config.model.MouseButton;
import com.cp.tools.antiafk.util.SystemInputProcessor;
import com.sun.glass.ui.Robot;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
        SystemInputProcessor processor = new SystemInputProcessor();

        int minTime = processor.nextPositiveInt("Enter the MINIMUM amount of time to wait between executions (in seconds): ");
        int maxTime = processor.nextPositiveIntLargerThanOrEqualTo("Enter the MAXIMUM amount of time to wait between executions (in seconds): ", minTime);

        boolean useMouse = processor.nextYesNoDecision("Use mouse instead of keyboard (yes/no)? ");

        Configuration config;
        if (useMouse) {
            MouseButton key = processor.nextMouseButton("Type out the mouse button to press (valid options are LMB): ");
            config = new Configuration(minTime, maxTime, key);
        } else {
            KeyboardButton key = processor.nextKeyboardButton("Type out the key to press (valid options are SPACE, BACKSPACE, ENTER, I, U): ");
            config = new Configuration(minTime, maxTime, key);
        }

        processor.close();
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
