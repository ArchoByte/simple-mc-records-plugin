package com.archobyte.SimpleMcRecords.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import com.archobyte.SimpleMcRecords.Handlers.AdvancementDoneHandler;

public class AdvancementDoneListener implements Listener {
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        new AdvancementDoneHandler(
                event.getPlayer(),
                event.getAdvancement());
    }
}
