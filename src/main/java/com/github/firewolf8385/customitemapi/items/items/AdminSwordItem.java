package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

public class AdminSwordItem extends CustomItem {

    public AdminSwordItem() {
        super("admin_sword");

        ItemStack item = new ItemBuilder(Material.GOLDEN_SWORD)
                .setDisplayName("Admin Sword")
                .addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", 100, AttributeModifier.Operation.ADD_NUMBER))
                .build();

        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.SWORD);
    }

}