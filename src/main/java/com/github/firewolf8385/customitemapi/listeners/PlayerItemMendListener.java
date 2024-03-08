package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

public class PlayerItemMendListener implements Listener {

    @EventHandler
    public void onMend(PlayerItemMendEvent event) {

        // Checks for Custom Enchantments
        for(Enchantment enchantment : event.getItem().getEnchantments().keySet()) {
            if(CustomItemAPIPlugin.isCustomEnchantment(enchantment)) {
                ((CustomEnchantment) enchantment).onItemMend(event);
            }
        }

        // Exit if the item isn't a custom item.
        if(!CustomItemAPIPlugin.isCustomItem(event.getItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPIPlugin.fromItemStack(event.getItem()).onMend(event);
    }
}