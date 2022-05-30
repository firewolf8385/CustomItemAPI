package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.utils.items.EnchantmentUtils;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import com.github.firewolf8385.customitemapi.utils.items.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

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
    private final List<ItemAtrribute> itemAtrributes = new ArrayList<>();

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

    public void addItemAttribute(ItemAtrribute itemAtrribute) {
        itemAtrributes.add(itemAtrribute);
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

    public List<ItemAtrribute> getItemAtrributes() {
        return itemAtrributes;
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

        // Transfer the durability
        builder.setDurability(item.getDurability());

        // Also Custom Durability
        if(ItemUtils.getIntData(item, "max-durability") != 0) {
            builder.setCustomDurability(ItemUtils.getIntData(item, "max-durability"));
            builder.setPersistentData("current-durability", ItemUtils.getIntData(item, "current-durability"));
        }

        if(!CustomItemAPI.isUpgraded(item)) {
            builder.setDisplayName(rarity.getColor() + meta.getDisplayName());
        }
        else {
            ItemRarity newRarity = ItemRarity.getByWeight(rarity.getWeight() + 1);
            builder.setDisplayName(newRarity.getColor() + ChatColor.stripColor(meta.getDisplayName()));
        }

        boolean hasAttributes = false;

        if(!itemAtrributes.isEmpty()) {
            AttributeModifier damageFix = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
            builder.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageFix);
        }

        for(ItemAtrribute itemAtrribute : itemAtrributes) {
            hasAttributes = true;

            builder.addAttributeModifier(itemAtrribute.getType().getAttribute(), itemAtrribute.toAttributeModifier());
            builder.addLore(itemAtrribute.toString());
        }

        if(hasAttributes) {
            builder.addLore("");
        }

        // Add appropriate space after any added lore.
        if(meta.getLore() != null && meta.getLore().size() != 0) {
            builder.addLore(" ");
        }

        if(!meta.getEnchants().isEmpty()) {
            for(Enchantment enchantment : meta.getEnchants().keySet()) {
                // Skip if the enchantment level is 0.
                if(meta.getEnchants().get(enchantment) == 0) {
                    continue;
                }

                String name = EnchantmentUtils.enchantmentToString(enchantment);
                String level = EnchantmentUtils.IntegerToRomanNumeral(meta.getEnchants().get(enchantment));
                builder.addLore("&7" + name + level);
                builder.addEnchantment(enchantment, meta.getEnchants().get(enchantment));
            }
            builder.addLore("");
        }

        if(ItemUtils.getIntData(item, "max-durability") != 0) {
            int maxDurability = ItemUtils.getIntData(item, "max-durability");
            int currentDurability = ItemUtils.getIntData(item, "current-durability");

            builder.addLore("&7Durability: &a" + currentDurability + "&7/&a" + maxDurability);
        }

        // Add rarity lore
        if(rarity != ItemRarity.NONE) {
            if(!CustomItemAPI.isUpgraded(item)) {
                builder.addLore(rarity.getColor() + "&l" + rarity.getName() + type.toString());
            }
            else {
                ItemRarity newRarity = ItemRarity.getByWeight(rarity.getWeight() + 1);
                builder.addLore(newRarity.getColor() + "&k&l#&r " + newRarity.getColor() + "&l" + newRarity.getName() + type.toString() + " &k#");
            }
        }

        // Add custom data.
        builder.setPersistentData("ci-id", id)
                .setPersistentData("ci-rarity", rarity.toString());

        // Add item flags.
        builder.addFlag(ItemFlag.HIDE_ENCHANTS)
                .addFlag(ItemFlag.HIDE_ATTRIBUTES);

        return builder.build();
    }

    public ItemStack update(ItemStack item) {
        // Creates a new item builder with default item meta.
        ItemBuilder clone = new ItemBuilder(this.item.clone());
        clone.setItemMeta(this.item.getItemMeta());

        // Also Custom Durability
        if(ItemUtils.getIntData(item, "max-durability") != 0) {
            clone.setCustomDurability(ItemUtils.getIntData(item, "max-durability"));
            clone.setPersistentData("current-durability", ItemUtils.getIntData(item, "current-durability"));
        }

        // Transfer the durability
        clone.setDurability(item.getDurability());

        Map<Enchantment, Integer> enchantments = item.getEnchantments();

        if(CustomItemAPI.isUpgraded(item)) {
            clone.setPersistentData("ci-upgraded", "true");
        }

        // Sets the display name.
        if(rarity != ItemRarity.NONE) {
            if(!CustomItemAPI.isUpgraded(item)) {
                clone.setDisplayName(rarity.getColor() + this.item.getItemMeta().getDisplayName());
            }
            else {
                ItemRarity newRarity = ItemRarity.getByWeight(rarity.getWeight() + 1);
                clone.setDisplayName(newRarity.getColor() + this.item.getItemMeta().getDisplayName());
            }
        }

        // Adds item attributes if there are any.
        boolean hasAttributes = false;

        if(!itemAtrributes.isEmpty()) {
            AttributeModifier damageFix = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
            clone.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageFix);
        }

        for(ItemAtrribute itemAtrribute : itemAtrributes) {
            hasAttributes = true;

            clone.addAttributeModifier(itemAtrribute.getType().getAttribute(), itemAtrribute.toAttributeModifier());
            clone.addLore(itemAtrribute.toString());
        }

        // Extra whitespace to separate item attributes from other things.
        if(hasAttributes) {
            clone.addLore("");
        }

        // Re-adds all old enchantments
        if(!enchantments.isEmpty()) {
            for(Enchantment enchantment : enchantments.keySet()) {
                String name = EnchantmentUtils.enchantmentToString(enchantment);

                if(!EnchantmentUtils.hasLevel(enchantment)) {
                    clone.addLore("&7" + name);
                }
                else {
                    String level = EnchantmentUtils.IntegerToRomanNumeral(item.getItemMeta().getEnchants().get(enchantment));
                    clone.addLore("&7" + name + " " + level);
                }
                clone.addEnchantment(enchantment, item.getItemMeta().getEnchants().get(enchantment));
            }

            if(rarity != ItemRarity.NONE) {
                clone.addLore("");
            }
        }

        if(ItemUtils.getIntData(item, "max-durability") != 0) {
            int maxDurability = ItemUtils.getIntData(item, "max-durability");
            int currentDurability = ItemUtils.getIntData(item, "current-durability");

            clone.addLore("&7Durability: &a" + currentDurability + "&7/&a" + maxDurability);
        }

        // Add rarity lore
        if(rarity != ItemRarity.NONE) {
            if(!CustomItemAPI.isUpgraded(item)) {
                clone.addLore(rarity.getColor() + "&l" + rarity.getName() + type.toString());
            }
            else {
                ItemRarity newRarity = ItemRarity.getByWeight(rarity.getWeight() + 1);
                clone.addLore(newRarity.getColor() + "&k&l#&r " + newRarity.getColor() + "&l" + newRarity.getName() + type.toString() + " &k#");
            }
        }

        // Add custom data.
        clone.setPersistentData("ci-id", id)
                .setPersistentData("ci-rarity", rarity.toString());

        // Add item flags.
        clone.addFlag(ItemFlag.HIDE_ENCHANTS)
                .addFlag(ItemFlag.HIDE_ATTRIBUTES);

        return clone.build();
    }

    public void onBreak(PlayerItemBreakEvent event) {}

    public void onClick(InventoryClickEvent event) {}

    public void onConsume(PlayerItemConsumeEvent event) {}

    public void onCraft(CraftItemEvent event) {}

    public void onDamage(PlayerItemDamageEvent event) {}

    public void onDrag(InventoryDragEvent event) {}

    public void onDrop(PlayerDropItemEvent event) {}

    public void onEnchant(EnchantItemEvent event) {}

    public void onHold(PlayerItemHeldEvent event) {}

    public void onInteract(PlayerInteractEvent event) {}

    public void onMend(PlayerItemMendEvent event) {}

    public void onSmelt(FurnaceSmeltEvent event) {}

    public void onSmith(SmithItemEvent event) {}
}