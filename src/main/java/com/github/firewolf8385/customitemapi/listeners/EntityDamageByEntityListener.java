package com.github.firewolf8385.customitemapi.listeners;

import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Projectile)) {
            return;
        }

        for(MetadataValue metadataValue : event.getDamager().getMetadata("damage")) {
            event.setDamage(metadataValue.asInt());
        }
    }

}
