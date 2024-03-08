package com.github.firewolf8385.customitemapi.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerAmorChangeListener implements Listener {

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {

        // Checks for Custom Enchantments
        for(Enchantment enchantment : event.getOldItem().getEnchantments().keySet()) {
            if(CustomItemAPIPlugin.isCustomEnchantment(enchantment)) {
                ((CustomEnchantment) enchantment).onUnequip(event);
            }
        }

        // Checks for Custom Enchantments
        for(Enchantment enchantment : event.getNewItem().getEnchantments().keySet()) {
            if(CustomItemAPIPlugin.isCustomEnchantment(enchantment)) {
                ((CustomEnchantment) enchantment).onEquip(event);
            }
        }
    }

}
