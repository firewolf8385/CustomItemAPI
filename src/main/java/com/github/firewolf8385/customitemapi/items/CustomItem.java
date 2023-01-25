package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.utils.items.EnchantmentUtils;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import com.github.firewolf8385.customitemapi.utils.items.ItemUtils;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.*;

/**
 * Represents a Custom Item, not available in the base game.
 */
public class CustomItem {
    // Required values
    private final String id;
    private final List<String> description = new ArrayList<>();
    private final Map<ItemAttribute, Double> itemAttributes = new HashMap<>();

    // Optional values
    private ItemStack item;
    private int maxDurability;
    private ItemRarity rarity;
    private ItemType type;

    /**
     * Creates a Custom Item.
     * @param id ID of the custom item.
     */
    public CustomItem(String id) {
        this.id = id;

        this.item = new ItemBuilder(Material.STICK).setDisplayName("Custom Item").build();
        this.maxDurability = 0;
        this.rarity = ItemRarity.NONE;
        this.type = ItemType.NONE;
    }

    public void addDescription(List<String> description) {
        this.description.addAll(description);
    }

    public void addDescription(String description) {
        this.description.add(description);
    }

    /**
     * Adds an item attribute to the custom item.
     * @param atrribute ItemAttribute to add.
     * @param value Value of the attribute to add.
     */
    public void addItemAttribute(ItemAttribute atrribute, double value) {
        if(value == 0) {
            return;
        }

        if(itemAttributes.containsKey(atrribute)) {
            itemAttributes.put(atrribute ,itemAttributes.get(atrribute) + value);
            return;
        }

        itemAttributes.put(atrribute, value);
    }

    /**
     * Gets the value of an attribute on a custom item.
     * Returns 0 if the item does not have that attribute.
     * @param attributeId Id of the attribute to get the value of.
     * @return Value of the attribute.
     */
    public double getAttributeValue(String attributeId) {
        for(ItemAttribute attribute : itemAttributes.keySet()) {
            if(attribute.getId().equalsIgnoreCase(attributeId)) {
                return itemAttributes.get(attribute);
            }
        }

        return 0;
    }

    /**
     * Get the id of the custom item.
     * @return id of the custom item.
     */
    public String getID() {
        return id;
    }

    /**
     * Get the item attributes of the custom item.
     * @return Item Attributes.
     */
    public Map<ItemAttribute, Double> getItemAttributes() {
        return itemAttributes;
    }

    /**
     * Get the max durability of the custom item.
     * @return Max durability of the item.
     */
    public int getMaxDurability() {
        return maxDurability;
    }

    /**
     * Get the rarity of the Custom Item.
     * @return Rarity of the custom item.
     */
    public ItemRarity getRarity() {
        return rarity;
    }

    /**
     * Get the ItemType of the Custom Item.
     * @return ItemType.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Check if the custom item has a certain attribute.
     * @param id Id of the attribute.
     * @return Whether the item has an attribute.
     */
    public boolean hasAttribute(String id) {
        for(ItemAttribute attribute : itemAttributes.keySet()) {
            if(attribute.getId().equalsIgnoreCase(id)) {
               return true;
            }
        }

        return false;
    }

    /**
     * Set the base ItemStack of the custom item.
     * @param item New base ItemStack.
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * Changes the max durability of the custom item.
     * @param maxDurability New max durability.
     */
    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    /**
     * Changes the rarity of the custom item.
     * @param rarity New rarity.
     */
    public void setRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }

    /**
     * Changes the type of item the custom item is.
     * @param type New Item Type.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Gets the ItemStack that the Custom Item represents.
     * @return Represented ItemStack.
     */
    public ItemStack toItemStack() {
        return update(this.item);
    }

    /**
     * Updates an item stack in case of any changes to the custom item.
     * @param item ItemStack to update.
     * @return Updated item stack.
     */
    public ItemStack update(ItemStack item) {
        // Creates a new item builder with default item meta.
        ItemBuilder clone = new ItemBuilder(this.item.clone());
        clone.setItemMeta(this.item.getItemMeta());


        // Sets the custom item id.
        clone.setPersistentData("ci-id", id);

        // Sets the item as upgraded if it is upgraded.
        if(CustomItemAPI.isUpgraded(item)) {
            clone.setPersistentData("ci-upgraded", "true");
        }

        // Sets up custom durability if that applies.
        if(maxDurability > 0) {
            clone.setPersistentData("ci-max_durability", maxDurability);

            if(ItemUtils.getIntData(item, "ci-current_durability") == 0) {
                clone.setPersistentData("ci-current_durability", maxDurability);
            }
            else {
                clone.setPersistentData("ci-current_durability", ItemUtils.getIntData(item, "ci-current_durability"));
            }

            // Sets the item's durability if it applies.
            if(item.getItemMeta() instanceof Damageable) {
                clone.setDurability(item.getDurability());
            }
        }



        // Sets the item's display name.
        ItemRarity rarity;
        if(!CustomItemAPI.isCustomItem(item)) {
            rarity = this.rarity;
        }
        else {
            rarity = CustomItemAPI.getRarity(item);
        }

        // Sets the name of the item.
        if(ItemUtils.getStringData(item, "ci-display_name") != null && !ItemUtils.getStringData(item, "ci-display_name").equals("")) {
            clone.setDisplayName(rarity.getColor() + ItemUtils.getStringData(item, "ci-display_name"));
            clone.setPersistentData("ci-display_name", ItemUtils.getStringData(item, "ci-display_name"));
        }
        else {
            clone.setDisplayName(rarity.getColor() + this.item.getItemMeta().getDisplayName());
        }

        // Adds item attributes if there are any.
        boolean hasAttributes = false;

        for(ItemAttribute attribute : itemAttributes.keySet()) {
            double value = itemAttributes.get(attribute);

            if(value == 0) {
                continue;
            }

            hasAttributes = true;

            attribute.addedToItem(this, clone, value);

            if(Math.floor(value) == value) {
                if(value > 0) {
                    clone.addLore("&7" + attribute.getName() + ": &a+" + ((int) value));
                }
                else {
                    clone.addLore("&7" + attribute.getName() + ": &c-" + ((int) value));
                }
            }
            else {
                if(value > 0) {
                    clone.addLore("&7" + attribute.getName() + ": &a+" + value);
                }
                else {
                    clone.addLore("&7" + attribute.getName() + ": &c-" + value);
                }
            }
        }

        // Fixes weapon damage.
        if(!itemAttributes.containsKey(CustomItemAPI.getAttribute("damage")) && type != ItemType.NONE) {
            AttributeModifier damageFix = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
            clone.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageFix);
        }

        // Extra whitespace to separate item attributes from other things.
        if(hasAttributes) {
            clone.addLore("");
        }

        // Re-adds all old enchantments
        if(!item.getEnchantments().isEmpty()) {
            boolean hasEnchantments = false;

            // Loops through all enchantments on the old item.
            for(Enchantment enchantment : item.getEnchantments().keySet()) {
                // Skip if the enchantment level is 0.
                if(item.getEnchantments().get(enchantment) == 0) {
                    continue;
                }

                String name = EnchantmentUtils.enchantmentToString(enchantment);
                String level = EnchantmentUtils.IntegerToRomanNumeral(item.getEnchantments().get(enchantment));
                clone.addEnchantment(enchantment, item.getEnchantments().get(enchantment));

                if(enchantment.getMaxLevel() > 1) {
                    clone.addLore("&7" + name + " " + level);
                }
                else {
                    clone.addLore("&7" + name);
                }

                hasEnchantments = true;
            }

            // Adds extra whitespace only if the item has visible enchantments.
            // Used to give items a "glow" effect without displaying the enchantment used.
            if(hasEnchantments) {
                clone.addLore("");
            }
        }

        // Re-adds stored enchantments, used in enchanted books.
        if(item.getItemMeta() instanceof EnchantmentStorageMeta temp) {
            boolean hasEnchantments = false;
            for(Enchantment enchantment : temp.getStoredEnchants().keySet()) {
                clone.addStoredEnchant(enchantment, temp.getStoredEnchantLevel(enchantment), true);

                String name = EnchantmentUtils.enchantmentToString(enchantment);
                String level = EnchantmentUtils.IntegerToRomanNumeral(temp.getStoredEnchantLevel(enchantment));

                if(enchantment.getMaxLevel() > 1) {
                    clone.addLore("&7" + name + " " + level);
                }
                else {
                    clone.addLore("&7" + name);
                }

                hasEnchantments = true;
            }

            // Adds extra whitespace only if the item has visible enchantments.
            // Used to give items a "glow" effect without displaying the enchantment used.
            if(hasEnchantments) {
                clone.addLore("");
                clone.addFlag(ItemFlag.HIDE_POTION_EFFECTS);
            }
        }

        // Adds the item's description if it has one.
        if(description.size() > 0) {
            for(String line : description) {
                clone.addLore(line);
            }
            clone.addLore("");
        }

        // Adds the durability counter.
        if(maxDurability > 0) {
            int currentDurability;

            if(ItemUtils.getIntData(item, "ci-current_durability") == 0) {
                currentDurability = maxDurability;
            }
            else {
                currentDurability = ItemUtils.getIntData(item, "ci-current_durability");
            }

            clone.addLore("&7Durability: &a" + currentDurability + "&7/&a" + maxDurability);
        }

        // Add rarity lore
        if(rarity != ItemRarity.NONE) {
            // Checks if the item is upgraded.
            if(!CustomItemAPI.isUpgraded(item)) {
                // If not, add normal rarity lore.
                clone.addLore(rarity.getColor() + "&l" + rarity.getName() + type.toString());
            }
            else {
                // If so, use upgraded rarity lore.
                clone.addLore(rarity.getColor() + "&k&l#&r " + rarity.getColor() + "&l" + rarity.getName() + type.toString() + " &k#");
            }
        }

        // Copies loaded crossbow projectiles.
        if(item.getItemMeta() instanceof CrossbowMeta temp) {
            clone.setChargedProjectiles(temp.getChargedProjectiles());
        }

        // Copies potion meta.
        if(item.getItemMeta() instanceof PotionMeta temp) {
            clone.setBasePotionData(temp.getBasePotionData());
        }

        // Copies block state meta.
        if(item.getItemMeta() instanceof BlockStateMeta temp) {
            clone.setBlockState(temp.getBlockState());
        }

        // Copies firework meta.
        if(item.getItemMeta() instanceof FireworkMeta temp) {
            clone.setPower(temp.getPower());

            for(FireworkEffect effect : temp.getEffects()) {
                clone.addEffect(effect);
            }
        }

        // Add item flags.
        clone.addFlag(ItemFlag.HIDE_ENCHANTS);
        clone.addFlag(ItemFlag.HIDE_ATTRIBUTES);
        clone.addFlag(ItemFlag.HIDE_UNBREAKABLE);

        // Fixes the amount of items in the stack.
        clone.setAmount(item.getAmount());

        // Builds the updated item.
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