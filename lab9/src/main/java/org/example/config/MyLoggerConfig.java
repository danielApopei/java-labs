package org.example.config;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLoggerConfig {

    public static final Logger LOGGER = Logger.getLogger("");

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Set the level for the handler
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.ALL); // Set the level for the logger itself
    }
}
