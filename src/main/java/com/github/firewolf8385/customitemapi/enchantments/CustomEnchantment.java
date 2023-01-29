package com.github.firewolf8385.customitemapi.enchantments;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CustomEnchantment extends Enchantment {
    private final String id;
    private final String name;
    private int maxLevel;
    private EnchantmentTarget target;
    private EnchantmentRarity rarity;
    private String description;

    /**
     * Creates a Custom Enchantment
     * @param id Id of the Custom Enchantment
     * @param name Name of the Custom Enchantment.
     */
    public CustomEnchantment(String id, String name) {
        super(new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), id));

        this.id = id;
        this.name = name;

        target = EnchantmentTarget.ALL;
        maxLevel = 5;
        rarity = EnchantmentRarity.COMMON;

        description = "";
    }


    /**
     * Get the description of the enchantment.
     * Returns empty if there is no description.
     * @return Enchantment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the id of the enchantment.
     * @return Enchantment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Change the description of the enchantment.
     * @param description Enchantment description.
     * @return Instance of the Custom Enchantment.
     */
    public CustomEnchantment setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the enchantment target of the enchantment.
     * @param target Enchantment Target
     * @return Custom Enchantment.
     */
    public CustomEnchantment setTarget(EnchantmentTarget target) {
        this.target = target;
        return this;
    }

    /**
     * Sets the maximum level of the enchantment.
     * @param maxLevel Maximum level.
     * @return CustomEnchantment.
     */
    public CustomEnchantment setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public CustomEnchantment setRarity(EnchantmentRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    /**
     * Gets the name of the enchantment.
     * @return Enchantment name.
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    public boolean isCursed() {
        return false;
    }

    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack arg0) {
        return false;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text(name + " Component");
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return rarity;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return target;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public @NotNull String translationKey() {
        return null;
    }

    public void onEntityDamage(EntityDamageByEntityEvent event) {}

    public void onInteract(PlayerInteractEvent event) {}

    public void onItemDamage(PlayerItemDamageEvent event) {}

    public void onItemMend(PlayerItemMendEvent event) {}

    public void onHold(PlayerItemHeldEvent event) {}

    public void onMove(PlayerMoveEvent event) {}

    public void onEquip(PlayerArmorChangeEvent event) {}

    public void onUnequip(PlayerArmorChangeEvent event) {}
}