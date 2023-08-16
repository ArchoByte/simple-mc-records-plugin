package com.archobyte.SimpleMcRecords;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleMcRecordsPlugin extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("simple-mc-records");

    public void onEnable() {
        LOGGER.info("simple-mc-records enabled");
    }

    public void onDisable() {
        LOGGER.info("simple-mc-records disabled");
    }
}
