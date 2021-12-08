package com.cp.tools.antiafk.config.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
    @XmlElement(name = "minimum-time-between-executions-in-seconds")
    private long minimumTimeBetweenExecutions;
    @XmlElement(name = "maximum-time-between-executions-in-seconds")
    private long maximumTimeBetweenExecutions;
    @XmlElement(name = "key-to-press")
    private KeyboardButton keyToPress;
}
