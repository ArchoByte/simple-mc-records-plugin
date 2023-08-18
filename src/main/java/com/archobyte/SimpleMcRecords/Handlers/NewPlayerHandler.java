package com.archobyte.SimpleMcRecords.Handlers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.archobyte.SimpleMcRecords.SimpleMcRecordsPlugin;
import com.archobyte.SimpleMcRecords.Web.RequestMethod;
import com.archobyte.SimpleMcRecords.Web.WebApi;
import com.google.gson.JsonObject;

public class NewPlayerHandler {
    private final Player PLAYER;

    public NewPlayerHandler(Player player) {
        PLAYER = player;

        // Send http request to ensure player presence in database
        new BukkitRunnable() {
            public void run() {
                // Constructing request body
                JsonObject request = new JsonObject();
                request.addProperty("name", PLAYER.getName());
                // Sending request
                try {
                    WebApi.createRequest(
                            SimpleMcRecordsPlugin.getBackendUrl() + "/api/Players",
                            RequestMethod.POST,
                            request.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(SimpleMcRecordsPlugin.getInstance());
    }
}
