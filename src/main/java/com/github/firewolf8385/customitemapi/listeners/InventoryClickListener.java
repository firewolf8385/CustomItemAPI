package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        // Exit if the item is null.
        if(event.getCurrentItem() == null) {
            return;
        }

        // Exit if the item isn't a custom item.
        if(!CustomItemAPIPlugin.isCustomItem(event.getCurrentItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPIPlugin.fromItemStack(event.getCurrentItem()).onClick(event);
    }
}
