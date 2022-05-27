package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        // Exit if the item is null.
        if(event.getCursor() == null) {
            return;
        }

        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getCursor())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getCursor()).onDrag(event);
    }
}
