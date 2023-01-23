package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import org.bukkit.enchantments.Enchantment;
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

        // Checks for Custom Enchantments
        for(Enchantment enchantment : item.getEnchantments().keySet()) {
            if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                ((CustomEnchantment) enchantment).onHold(event);
            }
        }

        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return;
        }

        if(!CustomItemAPI.isCustomItem(item)) {
            return;
        }

        CustomItem customItem = CustomItemAPI.fromItemStack(item);

        if(customItem == null) {
            return;
        }

        player.getInventory().setItemInMainHand(customItem.update(item));

        customItem.onHold(event);
    }
}