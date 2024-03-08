package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraftListener implements Listener {

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {

        String[] namespaces = new String[9];

        for(int i = 0; i < e.getInventory().getMatrix().length; i++) {
            ItemStack item = e.getInventory().getMatrix()[i];

            // Skip slot if the item is null.
            if(item == null) {
                continue;
            }

            if(CustomItemAPIPlugin.isCustomItem(item)) {
                CustomItem customItem = CustomItemAPIPlugin.fromItemStack(item);
                namespaces[i] = customItem.getID();
            }
            else {
                namespaces[i] = item.getType().getKey().toString();
            }
        }

        for(String namespace : namespaces) {
            //Bukkit.broadcastMessage(namespace);
        }
    }

}