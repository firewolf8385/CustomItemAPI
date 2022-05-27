package com.github.firewolf8385.customitemapi.listeners;

import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Only works on Paper.
 */
public class PrepareResultListener implements Listener {

    @EventHandler
    public void onPrepare(PrepareResultEvent event) {
        ItemStack item = event.getInventory().getItem(2);

        if(item == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return;
        }

        if(!CustomItemAPI.isCustomItem(item)) {
            return;
        }

        CustomItem customItem = CustomItemAPI.fromItemStack(item);
        event.setResult(customItem.update(item));
    }
}
