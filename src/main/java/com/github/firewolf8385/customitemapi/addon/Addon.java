package com.github.firewolf8385.customitemapi.addon;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a plugin that is using the api. It stores various information used, along with all items added.
 */
public class Addon {
    private final Plugin plugin;
    private final String id;
    private final ItemStack icon;
    private final List<CustomItem> items = new ArrayList<>();
    private final List<CustomEnchantment> enchantments = new ArrayList<>();
    private final List<ItemAttribute> attributes = new ArrayList<>();

    /**
     * Creates an Addon.
     * @param plugin Plugin Instance.
     * @param id Id of the addon.
     * @param icon ItemStack used in the Addon's icon.
     */
    public Addon(Plugin plugin, String id, ItemStack icon) {
        this.plugin = plugin;
        this.id = id;

        ItemBuilder builder = new ItemBuilder(icon);
        builder.setDisplayName("&a" + plugin.getName());
        builder.addLore("&7Version: " + plugin.getDescription().getVersion());
        builder.addFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.icon = builder.build();
    }

    /**
     * Creates an addon.
     * @param plugin Plugin Instance
     * @param id Id of the addon.
     * @param icon Material used in the Addon's icon.
     */
    public Addon(Plugin plugin, String id, Material icon) {
        this.plugin = plugin;
        this.id = id;

        ItemBuilder builder = new ItemBuilder(icon);
        builder.setDisplayName("&a" + plugin.getName());
        builder.addLore("&7Version: " + plugin.getDescription().getVersion());
        builder.addFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.icon = builder.build();
    }

    /**
     * Get a list of all attributes registered to the addon.
     * @return All registered attributes.
     */
    public List<ItemAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Get a list of all registered enchantments to the addon.
     * @return All registered addons.
     */
    public List<CustomEnchantment> getEnchantments() {
        return enchantments;
    }

    /**
     * Gets the Addon icon.
     * This is displayed in /items browse.
     * @return Addon Icon.
     */
    public ItemStack getIcon() {
        return icon;
    }

    /**
     * Get the id of the Addon.
     * @return The Addon's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Get all Custom Items registered to the addon.
     * @return All registered items.
     */
    public List<CustomItem> getItems() {
        return items;
    }

    /**
     * Get the Plugin Instance of the Addon.
     * @return Plugin Instance.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    public Addon registerAttribute(ItemAttribute attribute) {
        attributes.add(attribute);
        CustomItemAPI.registerAttribute(this, attribute);
        return this;
    }

    /**
     * Registers a custom enchantment to the addon.
     * @param customEnchantment Custom Enchantment to register.
     * @return Instance of the addon.
     */
    public Addon registerEnchantment(CustomEnchantment customEnchantment) {
        enchantments.add(customEnchantment);
        CustomItemAPI.registerEnchantment(this, customEnchantment);
        return this;
    }

    /**
     * Register an item to the addon.
     * @param item Item to add to the Addon.
     * @return Instance of the addon.
     */
    public Addon registerItem(CustomItem item) {
        items.add(item);
        CustomItemAPI.registerItem(this, item);
        return this;
    }
}