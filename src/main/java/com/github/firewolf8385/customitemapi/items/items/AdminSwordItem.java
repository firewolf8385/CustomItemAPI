package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.AttackSpeedAttribute;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.DamageAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminSwordItem extends CustomItem {

    public AdminSwordItem() {
        super("admin_sword");

        ItemStack item = new ItemBuilder(Material.GOLDEN_SWORD)
                .setDisplayName("Admin Sword")
                .build();

        addItemAttribute(new DamageAttribute(), 100);
        addItemAttribute(new AttackSpeedAttribute(), 100);
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.SWORD);

        setMaxDurability(2);
    }

}