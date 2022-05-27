package com.github.firewolf8385.customitemapi.items.items;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestStickItem extends CustomItem {

    public TestStickItem() {
        super("test_stick");

        ItemStack item = new ItemBuilder(Material.STICK)
                .setDisplayName("Test Stick")
                .build();
        setItem(item);
        setRarity(ItemRarity.SUPREME);
    }

}