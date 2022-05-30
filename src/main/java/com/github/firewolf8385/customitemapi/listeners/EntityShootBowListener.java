package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemAtrribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class EntityShootBowListener implements Listener {
    private final CustomItemAPI plugin;

    public EntityShootBowListener(CustomItemAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if(event.getBow() == null) {
            return;
        }

        if(!CustomItemAPI.isCustomItem(event.getBow())) {
            return;
        }

        CustomItem customItem = CustomItemAPI.fromItemStack(event.getBow());


        for(ItemAtrribute itemAtrribute : customItem.getItemAtrributes()) {
            if(itemAtrribute.getType() == ItemAtrribute.Type.DAMAGE) {
                // Gets the intended damage.
                float damage = itemAtrribute.getAmount();

                // Adds in "force" to the damage.
                damage = damage * event.getForce();

                // Applies "Power" damage if enchanted.
                if(event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) != 0) {
                    damage += (damage * 0.25 * event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) + 1);
                }

                // Applies that damage to the arrow.
                event.getProjectile().setMetadata("damage", new FixedMetadataValue(plugin, damage));
            }
        }
    }

}
