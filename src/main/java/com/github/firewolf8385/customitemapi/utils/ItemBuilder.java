package com.github.firewolf8385.customitemapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    /**
     * Create a new ItemStack with Material m
     * @param m Material for the ItemStack
     */
    public ItemBuilder(Material m) {
        this(m, 1);
    }

    /**
     * Create a new ItemStack of i items with material m
     * @param m Material for the ItemStack
     * @param i Number of items in the ItemStack
     */
    public ItemBuilder(Material m, int i) {
        this(new ItemStack(m, i));
    }

    /**
     * Start a builder with an existing ItemStack
     * @param item ItemStack
     */
    public ItemBuilder(ItemStack item) {
        this.item = item;
        meta = item.getItemMeta();
    }

    /**
     * Add lore to the item.
     * @param str String
     * @return ItemBuilder
     */
    public ItemBuilder addLore(String str) {
        List<String> lore = meta.getLore();

        if(lore == null) {
            lore = new ArrayList<>();
        }

        lore.add(ChatUtils.translate(str));
        meta.setLore(lore);

        return this;
    }

    /**
     * Add multiple lines of lore at once.
     * @param arr List of lore.
     * @return ItemBuilder.
     */
    public ItemBuilder addLore(List<String> arr) {
        List<String> lore = meta.getLore();

        if(lore == null) {
            lore = new ArrayList<>();
        }

        for(String str : arr) {
            lore.add(ChatUtils.translate(str));
        }
        meta.setLore(lore);

        return this;
    }

    /**
     * Get the ItemStack from the builder.
     * @return ItemStack
     */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Set the CustomModelData of the item.
     * @param data Data
     * @return ItemBuilder
     */
    public ItemBuilder setCustomModelData(int data) {
        meta.setCustomModelData(data);
        return this;
    }

    public ItemBuilder setCustomDurability(int durability) {
        NamespacedKey maxDurability = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "max-durability");
        NamespacedKey currentDurability = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "current-durability");

        meta.getPersistentDataContainer().set(maxDurability, PersistentDataType.INTEGER, durability);
        meta.getPersistentDataContainer().set(currentDurability, PersistentDataType.INTEGER, durability);

        return this;
    }

    /**
     * Set the display name of the item.
     * @param str Display name
     * @return ItemBuilder
     */
    public ItemBuilder setDisplayName(String str) {
        meta.setDisplayName(ChatUtils.translate(str));
        return this;
    }

    /**
     * Set the item stack
     * @param item item Stack
     */
    protected void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * Set the lore of an item.
     * @param lore
     * @return ItemBuilder
     */
    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Set if the item should be unbreakbale.
     * @param unbreakable Whether or not it should be unbreakable.
     * @return ItemBuilder.
     */
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(true);
        return this;
    }

}