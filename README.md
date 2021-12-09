## Changelog

- 1.0.1
  - Added a short, random delay (10ms - 100ms) between pressing a key and releasing it to mimic human-like behaviour.
  

- 1.0.0
  - Pressing of a key at random intervals
  - Persistent configuration using xml
  - Currently supported keys: `SPACE`, `ENTER`, `BACKSPACE`, `I`, `U`

## Description
A simple utility to press a chosen button at configurable random intervals. It does **not** attach to any processes, the
key presses will always be reflected in the window / app that's currently in the foreground.

## Requirements
- JRE (Java Runtime Environment) of version 1.8_311 or greater (download latest from [here](https://www.java.com/en/download/)).

## Configuration
When first ran, the app will ask for a few inputs to configure its behaviour. Afterwards, the customized config is
saved in an xml file in the same directory - subsequent runs will not ask for manual setup, as the saved xml will
be loaded instead.

To reconfigure, either delete the xml to trigger the setup upon next start, or modify the saved xml directly.

| Configuration name                         | Description                                                                                 |
| ------------------------------------------ | -----------                                                                                 |
| minimum-time-between-executions-in-seconds | The **minimum** amount of seconds to sleep between key presses. Must be positive.           |
| maximum-time-between-executions-in-seconds | The **maximum** amount of seconds to sleep between key presses. Must be positive.           |
| key-to-press                               | The actual key to press. Current valid choices are: `SPACE`, `ENTER`, `BACKSPACE`, `I`, `U` |

The key chosen in `key-to-press` will be pressed at random intervals conforming to the
`minimum-time-between-executions-in-seconds` and `maximum-time-between-executions-in-seconds` configuration properties.