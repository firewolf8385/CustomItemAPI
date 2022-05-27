package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.SmithItemEvent;

public class SmithItemListener implements Listener {

    @EventHandler
    public void onSmoth(SmithItemEvent event) {

        // Exit if the item isn't a custom item.
        if(!CustomItemAPI.isCustomItem(event.getCurrentItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPI.fromItemStack(event.getCurrentItem()).onSmith(event);
    }
}
