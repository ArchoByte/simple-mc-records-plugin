package com.archobyte.SimpleMcRecords.Handlers;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.archobyte.SimpleMcRecords.SimpleMcRecordsPlugin;
import com.archobyte.SimpleMcRecords.Web.RequestMethod;
import com.archobyte.SimpleMcRecords.Web.WebApi;
import com.archobyte.SimpleMcRecords.Web.WebApiResponse;
import com.google.gson.JsonObject;

public class StatisticIncrementHandler {
    private final Player PLAYER;
    private final Statistic STATISTIC;
    private final int VALUE;

    public StatisticIncrementHandler(Player player, Statistic statistic, int value) {
        PLAYER = player;
        STATISTIC = statistic;
        VALUE = value;

        // Entity kills and blocks mined and etc. need to be handled separately
        if (STATISTIC.getType() != Statistic.Type.UNTYPED)
            return;

        // Send http request for player statistic update
        new BukkitRunnable() {
            public void run() {
                // Sending request
                JsonObject request = new JsonObject();
                request.addProperty("name", STATISTIC.name().trim());
                request.addProperty("value", Integer.toString(VALUE));
                request.addProperty("playerName", PLAYER.getName());
                try {
                    WebApiResponse response = WebApi.createRequest(
                            SimpleMcRecordsPlugin.getBackendUrl() + "/api/Scores",
                            RequestMethod.POST,
                            request.toString());
                    if (response.getStatusCode() == 409) {
                        request.remove("playerName");
                        WebApi.createRequest(
                            SimpleMcRecordsPlugin.getBackendUrl() + "/api/Scores/" + STATISTIC.name().toLowerCase() + "/Players/" + PLAYER.getName(),
                            RequestMethod.PUT,
                            request.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(SimpleMcRecordsPlugin.getInstance());
    }
}
