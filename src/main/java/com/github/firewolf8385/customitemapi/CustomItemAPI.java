package com.github.firewolf8385.customitemapi;

import com.github.firewolf8385.customitemapi.addon.Addon;
import com.github.firewolf8385.customitemapi.addon.AddonManager;
import com.github.firewolf8385.customitemapi.commands.AbstractCommand;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.items.AdminSwordItem;
import com.github.firewolf8385.customitemapi.items.items.TestStickItem;
import com.github.firewolf8385.customitemapi.listeners.PlayerItemHeldListener;
import com.github.firewolf8385.customitemapi.listeners.PrepareItemCraftListener;
import com.github.firewolf8385.customitemapi.listeners.PrepareResultListener;
import com.github.firewolf8385.customitemapi.utils.gui.GUIListeners;
import com.github.firewolf8385.customitemapi.utils.items.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.TreeMap;

/**
 * This plugin allows easy creation and management of custom items.
 */
public final class CustomItemAPI extends JavaPlugin {
    private static final Map<String, CustomItem> items = new TreeMap<>();
    private static final AddonManager addonManager = new AddonManager();

    /**
     * This is called when Paper first loads the plugin.
     */
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PrepareResultListener(), this);
        Bukkit.getPluginManager().registerEvents(new PrepareItemCraftListener(), this);
        Bukkit.getPluginManager().registerEvents(new GUIListeners(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemHeldListener(), this);

        // Plugin startup logic
        AbstractCommand.registerCommands(this);

        // Metrics
        Metrics metrics = new Metrics(this, 10115);

        Addon addon = new Addon(this, "customitemapi", Material.GOLDEN_SWORD);
        addon.registerItem(new TestStickItem());
        addon.registerItem(new AdminSwordItem());
        addonManager.registerAddon(addon);
    }

    public static CustomItem fromItemStack(ItemStack item) {
        // Makes sure the item is a custom item.
        if(!isCustomItem(item)) {
            return null;
        }

        CustomItem customItem = null;

        String id = ItemUtils.getStringData(item, "ci-id");

        try {
            customItem = (CustomItem) getItem(id).clone();

            ItemMeta meta = item.getItemMeta();
            customItem.setEnchantments(meta.getEnchants());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return customItem;
    }

    public static Map<String, CustomItem> getCustomItems() {
        return  items;
    }

    public static CustomItem getItem(String id) {
        return items.get(id);
    }

    /**
     * Check if an item is a Custom Item.
     * @param item Item to check.
     * @return Whether it is a Custom Item.
     */
    public static boolean isCustomItem(ItemStack item) {
        return ItemUtils.getStringData(item, "ci-id") != null;
    }

    public static void registerItem(CustomItem item) {
        items.put(item.getID(), item);
    }

    public static AddonManager getAddonManager() {
        return addonManager;
    }
}