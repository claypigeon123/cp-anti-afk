package com.cp.tools.antiafk.config.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
    @XmlElement(name = "minimum-time-between-executions-in-seconds")
    private long minimumTimeBetweenExecutions;
    @XmlElement(name = "maximum-time-between-executions-in-seconds")
    private long maximumTimeBetweenExecutions;
    @XmlElement(name = "key-to-press")
    private KeyboardButton keyToPress;
    @XmlElement(name = "mouse-button-to-press")
    private MouseButton mouseButtonToPress;

    public Configuration(long minimumTimeBetweenExecutions, long maximumTimeBetweenExecutions, KeyboardButton keyToPress) {
        this.minimumTimeBetweenExecutions = minimumTimeBetweenExecutions;
        this.maximumTimeBetweenExecutions = maximumTimeBetweenExecutions;
        this.keyToPress = keyToPress;
        this.mouseButtonToPress = null;
    }

    public Configuration(long minimumTimeBetweenExecutions, long maximumTimeBetweenExecutions, MouseButton mouseButtonToPress) {
        this.minimumTimeBetweenExecutions = minimumTimeBetweenExecutions;
        this.maximumTimeBetweenExecutions = maximumTimeBetweenExecutions;
        this.keyToPress = null;
        this.mouseButtonToPress = mouseButtonToPress;
    }
}
