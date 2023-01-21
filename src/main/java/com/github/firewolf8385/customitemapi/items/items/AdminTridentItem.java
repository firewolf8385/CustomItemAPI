package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.AttackSpeedAttribute;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.DamageAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminTridentItem extends CustomItem {

    public AdminTridentItem() {
        super("admin_trident");

        ItemStack item = new ItemBuilder(Material.TRIDENT)
                .setDisplayName("Admin Trident")
                .build();

        addItemAttribute(new DamageAttribute(), 100);
        addItemAttribute(new AttackSpeedAttribute(), 100);
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.TRIDENT);

        setMaxDurability(512);
    }

}