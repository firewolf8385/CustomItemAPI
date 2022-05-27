package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class PlayerItemBreakListener implements Listener {

    @EventHandler
    public void onBreak(PlayerItemBreakEvent event) {
        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getBrokenItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getBrokenItem()).onBreak(event);
    }
}
