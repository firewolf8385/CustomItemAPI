package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        // Exit if the item is null.
        if(event.getItem() == null) {
            return;
        }

        // Checks for Custom Enchantments
        for(Enchantment enchantment : event.getItem().getEnchantments().keySet()) {
            if(CustomItemAPIPlugin.isCustomEnchantment(enchantment)) {
                ((CustomEnchantment) enchantment).onInteract(event);
            }
        }

        // Exit if the item isn't a custom item.
        if(!CustomItemAPIPlugin.isCustomItem(event.getItem())) {
            return;
        }

        // Run the onInteract() method of the CustomItem.
        CustomItemAPIPlugin.fromItemStack(event.getItem()).onInteract(event);
    }
}