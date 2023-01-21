package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.DamageAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminBowItem extends CustomItem {

    public AdminBowItem() {
        super("admin_bow");

        ItemStack item = new ItemBuilder(Material.BOW)
                .setDisplayName("Admin Bow")
                .build();

        addItemAttribute(new DamageAttribute(), 100);
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.BOW);

        setMaxDurability(512);
    }

}