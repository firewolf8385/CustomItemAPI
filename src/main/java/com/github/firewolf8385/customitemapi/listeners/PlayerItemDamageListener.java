package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
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
    private final CustomItemAPIPlugin plugin;

    public PlayerItemDamageListener(CustomItemAPIPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        NamespacedKey maxDurability = new NamespacedKey(plugin, "ci-max_durability");
        NamespacedKey currentDurability = new NamespacedKey(plugin, "ci-current_durability");
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

            if(CustomItemAPIPlugin.isCustomItem(item)) {
                int slot = event.getPlayer().getInventory().first(item);
                if(slot != -1) {
                    event.getPlayer().getInventory().setItem(slot, CustomItemAPIPlugin.fromItemStack(item).update(item));
                }
            }
        }

        if(CustomItemAPIPlugin.isCustomItem(item)) {
            CustomItemAPIPlugin.fromItemStack(item).onDamage(event);
        }
    }
}