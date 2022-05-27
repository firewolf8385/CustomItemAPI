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

public class Addon {
    private Plugin plugin;
    private String id;
    private ItemStack icon;
    private final List<CustomItem> items = new ArrayList<>();

    public Addon(Plugin plugin, String id, ItemStack icon) {
        this.plugin = plugin;
        this.id = id;

        ItemBuilder builder = new ItemBuilder(icon);
        builder.setDisplayName("&a" + plugin.getName());
        builder.addLore("&7Version: " + plugin.getDescription().getVersion());
        builder.addFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.icon = builder.build();
    }

    public Addon(Plugin plugin, String id, Material icon) {
        this.plugin = plugin;
        this.id = id;

        ItemBuilder builder = new ItemBuilder(icon);
        builder.setDisplayName("&a" + plugin.getName());
        builder.addLore("&7Version: " + plugin.getDescription().getVersion());
        builder.addFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.icon = builder.build();
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public List<CustomItem> getItems() {
        return items;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void registerItem(CustomItem item) {
        items.add(item);
        CustomItemAPI.registerItem(item);
    }
}