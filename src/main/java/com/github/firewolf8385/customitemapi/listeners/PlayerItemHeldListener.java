package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerItemHeldListener implements Listener {

    @EventHandler
    public void onHold(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

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
        player.getInventory().setItemInMainHand(customItem.update(item));

        customItem.onHold(event);
    }
}