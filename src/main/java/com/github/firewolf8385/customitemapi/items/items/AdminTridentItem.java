package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemAtrribute;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.ItemType;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminTridentItem extends CustomItem {

    public AdminTridentItem() {
        super("admin_trident");

        ItemStack item = new ItemBuilder(Material.TRIDENT)
                .setDisplayName("Admin Trident")
                .build();

        addItemAttribute(new ItemAtrribute(ItemAtrribute.Type.DAMAGE, 100, ItemAtrribute.Slot.MAIN_HAND));
        addItemAttribute(new ItemAtrribute(ItemAtrribute.Type.ATTACK_SPEED, 100, ItemAtrribute.Slot.MAIN_HAND));
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);
        setType(ItemType.TRIDENT);

        setMaxDurability(512);
    }

}