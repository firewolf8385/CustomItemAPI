package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
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
    private final CustomItemAPI plugin;

    public PlayerItemDamageListener(CustomItemAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        NamespacedKey maxDurability = new NamespacedKey(plugin, "max-durability");
        NamespacedKey currentDurability = new NamespacedKey(plugin, "current-durability");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if(container.has(maxDurability, PersistentDataType.INTEGER)) {
            int max = container.get(maxDurability, PersistentDataType.INTEGER);
            int current = container.get(currentDurability, PersistentDataType.INTEGER);
            int normal = item.getType().getMaxDurability();
            int damage = event.getDamage();
            current = current - damage;
            float ratio = (float) current / max;

            container.set(currentDurability, PersistentDataType.INTEGER, current);
            item.setItemMeta(meta);
            event.setCancelled(true);

            item.setDurability((short) (normal - (int) (normal * ratio)));

            if(current <= 0) {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                event.getPlayer().getInventory().remove(item);
            }
        }

        if(CustomItemAPI.isCustomItem(item)) {
            CustomItemAPI.fromItemStack(item).onDamage(event);
        }
    }
}