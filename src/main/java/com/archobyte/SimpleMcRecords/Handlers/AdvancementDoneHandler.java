package com.archobyte.SimpleMcRecords.Handlers;

import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.archobyte.SimpleMcRecords.SimpleMcRecordsPlugin;
import com.archobyte.SimpleMcRecords.Web.RequestMethod;
import com.archobyte.SimpleMcRecords.Web.WebApi;

public class AdvancementDoneHandler {
    private final Player PLAYER;
    private final String NAME;

    public AdvancementDoneHandler(Player player, Advancement advancement) {
        PLAYER = player;

        String[] names = advancement.getKey().getKey().split("/");
        NAME = names[names.length - 1];

        if (names[0].trim().equals("recipes"))
            return;

        // Send http request for player advancements update
        new BukkitRunnable() {
            public void run() {
                // Sending request
                try {
                    WebApi.createRequest(
                            SimpleMcRecordsPlugin.getBackendUrl() + "/api/Players/" + PLAYER.getName() + "/Advancements/" + NAME,
                            RequestMethod.POST, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(SimpleMcRecordsPlugin.getInstance());
    }
}
