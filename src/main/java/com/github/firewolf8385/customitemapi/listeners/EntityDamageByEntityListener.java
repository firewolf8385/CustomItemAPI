package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        Player player;
        if(event.getDamager() instanceof Player) {
            player = (Player) event.getDamager();
        }
        else if(event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
            player = (Player) ((Projectile) event.getDamager()).getShooter();
        }
        else {
            player = null;
        }

        if(player != null) {
            ItemStack helmet = player.getInventory().getHelmet();
            ItemStack chestplate = player.getInventory().getChestplate();
            ItemStack leggings = player.getInventory().getLeggings();
            ItemStack boots = player.getInventory().getBoots();
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            if(helmet != null) {
                // Checks for Custom Enchantments
                for(Enchantment enchantment : helmet.getEnchantments().keySet()) {
                    if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                        ((CustomEnchantment) enchantment).onEntityDamage(event);
                    }
                }
            }

            if(chestplate != null) {
                if(helmet != null) {
                    // Checks for Custom Enchantments
                    for(Enchantment enchantment : chestplate.getEnchantments().keySet()) {
                        if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                            ((CustomEnchantment) enchantment).onEntityDamage(event);
                        }
                    }
                }
            }

            if(leggings != null) {
                if(helmet != null) {
                    // Checks for Custom Enchantments
                    for(Enchantment enchantment : leggings.getEnchantments().keySet()) {
                        if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                            ((CustomEnchantment) enchantment).onEntityDamage(event);
                        }
                    }
                }
            }

            if(boots != null) {
                if(helmet != null) {
                    // Checks for Custom Enchantments
                    for(Enchantment enchantment : boots.getEnchantments().keySet()) {
                        if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                            ((CustomEnchantment) enchantment).onEntityDamage(event);
                        }
                    }
                }
            }

            if(heldItem != null) {
                if(helmet != null) {
                    // Checks for Custom Enchantments
                    for(Enchantment enchantment : heldItem.getEnchantments().keySet()) {
                        if(CustomItemAPI.getEnchantmentManager().isCustomEnchantment(enchantment)) {
                            ((CustomEnchantment) enchantment).onEntityDamage(event);
                        }
                    }
                }
            }
        }

        if(!(event.getDamager() instanceof Projectile)) {
            return;
        }

        for(MetadataValue metadataValue : event.getDamager().getMetadata("damage")) {
            event.setDamage(metadataValue.asInt());
        }
    }
}
