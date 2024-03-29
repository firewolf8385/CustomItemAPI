package com.github.firewolf8385.customitemapi.addon;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
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
    private Plugin plugin;
    private String id;
    private ItemStack icon;
    private final List<CustomItem> items = new ArrayList<>();

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
     * @return
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

    /**
     * Register an item to the addon.
     * @param item Item to add to the Addon.
     */
    public void registerItem(CustomItem item) {
        items.add(item);
        CustomItemAPI.registerItem(item);
    }
}