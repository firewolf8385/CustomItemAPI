package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

public class PlayerItemMendListener implements Listener {

    @EventHandler
    public void onMend(PlayerItemMendEvent event) {

        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getItem()).onMend(event);
    }
}