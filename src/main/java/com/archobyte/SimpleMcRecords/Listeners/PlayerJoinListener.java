package com.archobyte.SimpleMcRecords.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.archobyte.SimpleMcRecords.Handlers.NewPlayerHandler;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onStatisticChange(PlayerJoinEvent event) {
        new NewPlayerHandler(event.getPlayer());
    }
}
