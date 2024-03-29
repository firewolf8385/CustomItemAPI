package com.github.firewolf8385.customitemapi.utils.items;

import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;

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
        this.item = item.clone();
        meta = this.item.getItemMeta();
    }

    public ItemBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    /**
     * Add an enchantment to the item.
     * @param e Enchantment to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder
     */
    public ItemBuilder addEnchantment(Enchantment e, int level) {
        addEnchantment(e, level, true);
        return this;
    }

    /**
     * Add an enchantment to the item.
     * @param e Enchantment to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder
     */
    public ItemBuilder addEnchantment(Enchantment e, int level, boolean ignore) {
        meta.addEnchant(e, level, ignore);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        meta.addItemFlags(flag);
        return this;
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

    public ItemBuilder addStoredEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ((EnchantmentStorageMeta) meta).addStoredEnchant(enchantment, level, ignoreLevelRestriction);
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

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setBasePotionData(PotionData data) {
        ((PotionMeta) meta).setBasePotionData(data);
        return this;
    }

    public ItemBuilder setBlockState(BlockState blockState) {
        ((BlockStateMeta) meta).setBlockState(blockState);
        return this;
    }

    public ItemBuilder setChargedProjectiles(List<ItemStack> projectiles) {
        ((CrossbowMeta) meta).setChargedProjectiles(projectiles);
        return this;
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

    /**
     * Set the custom durability of the item.
     * @param durability Durability to set
     * @return ItemBuilder
     */
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

    public ItemBuilder setDisplayName(Component name) {
        meta.displayName(name);
        return this;
    }

    public ItemBuilder setDurability(Short durability) {
        ((Damageable) meta).setDamage(durability);
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
     * Set the ItemMeta of the builder.
     * @param meta ItemMeta.
     */
    public void setItemMeta(ItemMeta meta) {
        this.meta = meta;
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
     * Save persistent data to the item.
     * @param key Key
     * @param value Value
     * @return ItemBuilder
     */
    public ItemBuilder setPersistentData(String key, String value) {
        NamespacedKey namepace = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), key);
        meta.getPersistentDataContainer().set(namepace, PersistentDataType.STRING, value);
        return this;
    }

    /**
     * Save persistent data to the item.
     * @param key Key
     * @param value value
     * @return ItemBuilder
     */
    public ItemBuilder setPersistentData(String key, int value) {
        NamespacedKey namepace = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), key);
        meta.getPersistentDataContainer().set(namepace, PersistentDataType.INTEGER, value);
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