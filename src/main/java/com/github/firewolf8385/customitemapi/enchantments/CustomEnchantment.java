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
    private String id;
    private String name;
    private int maxLevel;
    private EnchantmentTarget target;
    private EnchantmentRarity rarity;

    public CustomEnchantment(String id, String name) {
        super(new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), id));

        this.id = id;
        this.name = name;

        target = EnchantmentTarget.ALL;
        maxLevel = 5;
        rarity = EnchantmentRarity.COMMON;
    }

    public String getId() {
        return id;
    }

    public CustomEnchantment setTarget(EnchantmentTarget target) {
        this.target = target;
        return this;
    }

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