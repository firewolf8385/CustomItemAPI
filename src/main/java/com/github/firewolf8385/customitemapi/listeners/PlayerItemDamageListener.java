package com.github.firewolf8385.customitemapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerItemDamageListener implements Listener {
    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();

        NamespacedKey maxDurability = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "max-durability");
        NamespacedKey currentDurability = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "current-durability");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if(container.has(maxDurability, PersistentDataType.INTEGER)) {
            int max = container.get(maxDurability, PersistentDataType.INTEGER);
            int current = container.get(currentDurability, PersistentDataType.INTEGER);
            int normal = item.getType().getMaxDurability();
            int damage = e.getDamage();
            current = current - damage;
            float ratio = (float) current / max;

            container.set(currentDurability, PersistentDataType.INTEGER, current);
            item.setItemMeta(meta);
            e.setCancelled(true);

            item.setDurability((short) (normal - (int) (normal * ratio)));

            if(current <= 0) {
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                e.getPlayer().getInventory().remove(item);
            }
        }
    }
}