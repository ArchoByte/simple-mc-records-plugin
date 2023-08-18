package com.archobyte.SimpleMcRecords.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

import com.archobyte.SimpleMcRecords.Handlers.StatisticIncrementHandler;

public class StatisticIncrementListner implements Listener {
    @EventHandler
    public void onStatisticChange(PlayerStatisticIncrementEvent event) {
        new StatisticIncrementHandler(
                event.getPlayer(),
                event.getStatistic(),
                event.getNewValue());
    }
}
