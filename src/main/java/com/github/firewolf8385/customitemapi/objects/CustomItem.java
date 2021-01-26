package com.github.firewolf8385.customitemapi.objects;

import com.github.firewolf8385.customitemapi.enums.ItemRarity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an item not in in Vanilla Minecraft.
 */
public abstract class CustomItem {
    private final String id;
    private ItemRarity rarity;
    private static Map<String, CustomItem> allItems= new HashMap<>();

    /**
     * Creates a CustomItem.
     * @param id
     */
    public CustomItem(String id) {
        this.id = id;
        rarity = ItemRarity.NONE;
        allItems.put(id, this);
    }

    /**
     * Gets a map of all Custom Items.
     * @return Custom Items.
     */
    public static Map<String, CustomItem> getAllItems() {
        return allItems;
    }

    /**
     * Get the id of the custom item.
     * @return Id of the custom item.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the rarity of the item.
     * @return Rarity of the Item
     */
    public ItemRarity getRarity() {
        return rarity;
    }

    /**
     * Get the ItemStack of the custom item.
     * @return ItemStack of custom item.
     */
    public abstract ItemStack getItemStack();

    /**
     * Set the rarity of the item.
     * @param rarity Rarity of the item.
     */
    public void setRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }
}