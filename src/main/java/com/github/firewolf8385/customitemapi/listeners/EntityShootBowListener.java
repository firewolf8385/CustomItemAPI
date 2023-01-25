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

import java.util.Random;

public class EntityShootBowListener implements Listener {
    private final CustomItemAPI plugin;

    public EntityShootBowListener(CustomItemAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        // Makes sure the bow used isn't null.
        if(event.getBow() == null) {
            return;
        }

        // Gets the custom item of the used bow.
        CustomItem customItem = CustomItemAPI.fromItemStack(event.getBow());

        // Makes sure the item is a custom item.
        if(customItem == null) {
            return;
        }

        // Sets the default damage to 0.
        float damage = 0;

        // Adds the damage attribute to the damage.
        damage += customItem.getAttributeValue("");

        // Calculates critical arrows and adds crit damage.
        if(customItem.getAttributeValue("crit_chance") > 0) {
            Random random = new Random();
            int chosen = random.nextInt(100);

            if(customItem.getAttributeValue("crit_chance") >= chosen) {
                damage += customItem.getAttributeValue("crit_damage");
            }
        }

        // Applies "Power" damage if enchanted.
        if(event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) != 0) {
            damage += (damage * 0.25 * event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) + 1);
        }

        // Applies that damage to the arrow.
        event.getProjectile().setMetadata("damage", new FixedMetadataValue(plugin, damage));
    }

}
