package com.archobyte.SimpleMcRecords.Handlers;

import org.bukkit.advancement.Advancement;
import org.bukkit.scheduler.BukkitRunnable;

import com.archobyte.SimpleMcRecords.SimpleMcRecordsPlugin;
import com.archobyte.SimpleMcRecords.Web.RequestMethod;
import com.archobyte.SimpleMcRecords.Web.WebApi;
import com.google.gson.JsonObject;

public class NewAdvancementHandler {
    private final String NAME;
    private final String CATEGORY;

    public NewAdvancementHandler(Advancement advancement) {
        String[] names = advancement.getKey().getKey().split("/");
        NAME = names[names.length - 1];
        String category = names[0];

        if (category.trim().equals("recipes")) {
            CATEGORY = "invalid";
            return;
        }

        for (int i = 1; i < names.length - 1; i++)
            category = category + "/" + names[i];
        CATEGORY = category;

        // Send http request for player advancements update
        new BukkitRunnable() {
            public void run() {
                // Constructing request body
                JsonObject request = new JsonObject();
                request.addProperty("name", NAME);
                request.addProperty("categoryName", CATEGORY);
                // Sending request
                try {
                    WebApi.createRequest(
                            SimpleMcRecordsPlugin.getBackendUrl() + "/api/Advancements",
                            RequestMethod.POST,
                            request.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(SimpleMcRecordsPlugin.getInstance());
    }
}
