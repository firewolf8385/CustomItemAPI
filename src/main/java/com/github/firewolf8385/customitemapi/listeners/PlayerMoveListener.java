package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if(helmet != null) {
            // Checks for Custom Enchantments
            for(Enchantment enchantment : helmet.getEnchantments().keySet()) {
                if(CustomItemAPI.isCustomEnchantment(enchantment)) {
                    ((CustomEnchantment) enchantment).onMove(event);
                }
            }
        }

        if(chestplate != null) {
            // Checks for Custom Enchantments
            for(Enchantment enchantment : chestplate.getEnchantments().keySet()) {
                if(CustomItemAPI.isCustomEnchantment(enchantment)) {
                    ((CustomEnchantment) enchantment).onMove(event);
                }
            }
        }

        if(leggings != null) {
            // Checks for Custom Enchantments
            for(Enchantment enchantment : leggings.getEnchantments().keySet()) {
                if(CustomItemAPI.isCustomEnchantment(enchantment)) {
                    ((CustomEnchantment) enchantment).onMove(event);
                }
            }
        }

        if(boots != null) {
            // Checks for Custom Enchantments
            for(Enchantment enchantment : boots.getEnchantments().keySet()) {
                if(CustomItemAPI.isCustomEnchantment(enchantment)) {
                    ((CustomEnchantment) enchantment).onMove(event);
                }
            }
        }

        if(heldItem != null) {
            // Checks for Custom Enchantments
            for(Enchantment enchantment : heldItem.getEnchantments().keySet()) {
                if(CustomItemAPI.isCustomEnchantment(enchantment)) {
                    ((CustomEnchantment) enchantment).onMove(event);
                }
            }
        }
    }
}
