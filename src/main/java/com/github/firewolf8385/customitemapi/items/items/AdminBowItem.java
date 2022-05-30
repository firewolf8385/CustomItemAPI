package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemAtrribute;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminBowItem extends CustomItem {

    public AdminBowItem() {
        super("admin_bow");

        ItemStack item = new ItemBuilder(Material.BOW)
                .setDisplayName("Admin Sword")
                .setCustomDurability(512)
                .build();

        addItemAttribute(new ItemAtrribute(ItemAtrribute.Type.DAMAGE, 100, ItemAtrribute.Slot.MAIN_HAND));
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.BOW);
    }

}