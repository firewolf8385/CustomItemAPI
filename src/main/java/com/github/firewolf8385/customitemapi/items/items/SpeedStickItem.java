package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemAtrribute;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpeedStickItem extends CustomItem {

    public SpeedStickItem() {
        super("speed_stick");

        ItemStack item = new ItemBuilder(Material.STICK)
                .setDisplayName("Speed Stick")
                .build();
        setItem(item);
        setRarity(ItemRarity.LEGENDARY);

        addItemAttribute(new ItemAtrribute(ItemAtrribute.Type.SPEED, 10, ItemAtrribute.Slot.MAIN_HAND));
    }

}