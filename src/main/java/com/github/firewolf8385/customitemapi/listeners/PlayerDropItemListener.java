package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getItemDrop().getItemStack())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getItemDrop().getItemStack()).onDrop(event);
    }

}
