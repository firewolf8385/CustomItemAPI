package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.DamageAttribute;
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

        for(ItemAttribute itemAttribute : customItem.getItemAttributes().keySet()) {
            if(itemAttribute instanceof DamageAttribute) {
                // Gets the intended damage.
                float damage = customItem.getItemAttributes().get(itemAttribute);

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
