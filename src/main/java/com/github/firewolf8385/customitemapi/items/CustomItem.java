package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.utils.items.EnchantmentUtils;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Custom Item, not available in vanilla.
 */
public class CustomItem implements Cloneable {
    private final String id;
    private String name;
    private ItemRarity rarity;
    private ItemType type;
    private Material material;
    private ItemStack item;

    private Map<Enchantment, Integer> enchantments = new HashMap<>();

    /**
     * Creates a Custom Item.
     * @param id ID of the custom item.
     */
    public CustomItem(String id) {
        this.id = id;
        this.item = new ItemBuilder(Material.STICK).build();

        name = "Custom Item";
        rarity = ItemRarity.COMMON;
        type = ItemType.NONE;
        material = Material.STICK;
    }

    /**
     * Clone the object. Used for updating items.
     * @return The cloned object.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    // return shallow copy
    }

    /**
     * Get the id of the custom item.
     * @return ID of the custom item.
     */
    public String getID() {
        return id;
    }

    /**
     * Set the enchantments of the item.
     * This is used for converting an ItemStack to a CustomItem.
     * @param enchantments Enchantment map.
     * @return The Custom Item.
     */
    public CustomItem setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return  this;
    }

    public CustomItem setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    /**
     * Set the material of the item.
     * @param material The material.
     * @return The Custom Item.
     */
    public CustomItem setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Set the rarity of the item.
     * @param rarity The item rarity.
     * @return The Custom Item.
     */
    public CustomItem setRarity(ItemRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    /**
     * Set the type of the item.
     * @param type The item type.
     * @return The Custom Item.
     */
    public CustomItem setType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemStack toItemStack() {
        ItemStack clone = item.clone();
        clone.setItemMeta(item.getItemMeta());

        ItemBuilder builder = new ItemBuilder(clone);
        ItemMeta meta = item.getItemMeta();

        builder.setDisplayName(rarity.getColor() + meta.getDisplayName());

        if(meta.getAttributeModifiers() != null) {
            int attackDamage = 0;
            int attackSpeed = 0;
            int movementSpeed = 0;

            boolean flag = false;
            for(AttributeModifier modifier : meta.getAttributeModifiers().values()) {
                if(modifier.getOperation() != AttributeModifier.Operation.ADD_NUMBER) {
                    System.out.println("Only Operation Add Number is supported for Attributes!");
                    continue;
                }

                switch (modifier.getName()) {
                    case "generic.attackDamage" -> {
                        attackDamage += modifier.getAmount();
                        flag = true;
                    }
                    case "generic.attackSpeed" -> {
                        attackSpeed += modifier.getAmount();
                        flag = true;
                    }
                    case "generic.movementSpeed" -> {
                        movementSpeed += modifier.getAmount();
                        flag = true;
                    }
                }

                if(attackDamage != 0) builder.addLore("&7Damage: &a" + attackDamage);
                if(attackSpeed != 0) builder.addLore("&7Attack Speed: &a" + attackSpeed);
                if(movementSpeed != 0) builder.addLore("&7Speed: &a" + movementSpeed);

                if(flag) builder.addLore("");
            }
        }

        // Add appropriate space after any added lore.
        if(meta.getLore() != null && meta.getLore().size() != 0) {
            builder.addLore(" ");
        }

        if(!meta.getEnchants().isEmpty()) {
            for(Enchantment enchantment : meta.getEnchants().keySet()) {
                String name = EnchantmentUtils.enchantmentToString(enchantment);
                String level = EnchantmentUtils.IntegerToRomanNumeral(meta.getEnchants().get(enchantment));
                builder.addLore("&7" + name + level);
                builder.addEnchantment(enchantment, meta.getEnchants().get(enchantment));
            }
            builder.addLore("");
        }

        // Add rarity lore
        builder.addLore(rarity.getColor() + "&l" + rarity.toString() + type.toString());

        // Add custom data.
        builder.setPersistentData("ci-id", id)
                .setPersistentData("ci-rarity", rarity.toString());

        // Add item flags.
        builder.addFlag(ItemFlag.HIDE_ENCHANTS)
                .addFlag(ItemFlag.HIDE_ATTRIBUTES);

        return builder.build();
    }

    public ItemStack update(ItemStack item) {
        ItemStack clone = this.item.clone();
        clone.setItemMeta(this.item.getItemMeta());

        ItemBuilder builder = new ItemBuilder(clone);
        ItemMeta meta = this.item.getItemMeta();

        if(meta.getAttributeModifiers() != null) {
            int attackDamage = 0;
            int attackSpeed = 0;
            int movementSpeed = 0;

            boolean flag = false;
            for(AttributeModifier modifier : meta.getAttributeModifiers().values()) {
                if(modifier.getOperation() != AttributeModifier.Operation.ADD_NUMBER) {
                    System.out.println("Only Operation Add Number is supported for Attributes!");
                    continue;
                }

                switch (modifier.getName()) {
                    case "generic.attackDamage" -> {
                        attackDamage += modifier.getAmount();
                        flag = true;
                    }
                    case "generic.attackSpeed" -> {
                        attackSpeed += modifier.getAmount();
                        flag = true;
                    }
                    case "generic.movementSpeed" -> {
                        movementSpeed += modifier.getAmount();
                        flag = true;
                    }
                }

                if(attackDamage != 0) builder.addLore("&7Damage: &a" + attackDamage);
                if(attackSpeed != 0) builder.addLore("&7Attack Speed: &a" + attackSpeed);
                if(movementSpeed != 0) builder.addLore("&7Speed: &a" + movementSpeed);

                if(flag) builder.addLore("");
            }
        }

        builder.setDisplayName(rarity.getColor() + meta.getDisplayName());

        // Add appropriate space after any added lore.
        if(meta.getLore() != null && meta.getLore().size() != 0) {
            builder.addLore(" ");
        }

        if(!item.getItemMeta().getEnchants().isEmpty()) {
            for(Enchantment enchantment : item.getItemMeta().getEnchants().keySet()) {
                String name = EnchantmentUtils.enchantmentToString(enchantment);

                if(!EnchantmentUtils.hasLevel(enchantment)) {
                    builder.addLore("&7" + name);
                }
                else {
                    String level = EnchantmentUtils.IntegerToRomanNumeral(item.getItemMeta().getEnchants().get(enchantment));
                    builder.addLore("&7" + name + " " + level);
                }
                builder.addEnchantment(enchantment, item.getItemMeta().getEnchants().get(enchantment));
            }
            builder.addLore("");
        }

        // Add rarity lore
        builder.addLore(rarity.getColor() + "&l" + rarity.toString() + type.toString());

        // Add custom data.
        builder.setPersistentData("ci-id", id)
                .setPersistentData("ci-rarity", rarity.toString());

        // Add item flags.
        builder.addFlag(ItemFlag.HIDE_ENCHANTS)
                .addFlag(ItemFlag.HIDE_ATTRIBUTES);

        return builder.build();
    }
}