package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

public class FurnaceSmeltListener implements Listener {

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {

        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getSource())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getSource()).onSmelt(event);
    }
}
