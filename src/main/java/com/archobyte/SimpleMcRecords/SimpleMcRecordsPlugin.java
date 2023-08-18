package com.archobyte.SimpleMcRecords;

import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.archobyte.SimpleMcRecords.Handlers.NewAdvancementHandler;
import com.archobyte.SimpleMcRecords.Handlers.NewPlayerHandler;
import com.archobyte.SimpleMcRecords.Listeners.AdvancementDoneListener;
import com.archobyte.SimpleMcRecords.Listeners.PlayerJoinListener;
import com.archobyte.SimpleMcRecords.Listeners.StatisticIncrementListner;

public class SimpleMcRecordsPlugin extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("simple-mc-records");

    private static SimpleMcRecordsPlugin instance;

    public static SimpleMcRecordsPlugin getInstance() {
        return instance;
    }

    private static String defaultUri;

    public static String getBackendUrl() {
        return defaultUri;
    }

    public SimpleMcRecordsPlugin() {
        super();
        instance = this;
    }

    public void onEnable() {
        // Connecting listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new AdvancementDoneListener(), this);
        getServer().getPluginManager().registerEvents(new StatisticIncrementListner(), this);

        // Get backend url
        if (!getConfig().isSet("backendUrl")) {
            saveDefaultConfig();
            LOGGER.info("Setting " + getConfig().getString("backendUrl") + " as backend URL");
        }
        defaultUri = getConfig().getString("backendUrl");

        // Ensure players presence in database
        for (Player player : Bukkit.getOnlinePlayers())
            new NewPlayerHandler(player);

        // Ensure advancements presence in database
        Iterator<Advancement> it = Bukkit.advancementIterator();

        while(it.hasNext()) {
            Advancement adv = it.next();
            new NewAdvancementHandler(adv);
        }

        LOGGER.info("simple-mc-records enabled");
    }

    public void onDisable() {
        LOGGER.info("simple-mc-records disabled");
    }
}
