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
import com.github.firewolf8385.customitemapi.settings.SettingsManager;
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
    private SettingsManager settingsManager;

    /**
     * This is called when Paper first loads the plugin.
     */
    @Override
    public void onEnable() {
        // Makes sure the server is running paper.
        try {
            boolean isPaper = Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData") != null;
        }
        catch (ClassNotFoundException exception) {
            Bukkit.getLogger().warning("CustomItemAPI only works on Paper and its forks.");
            getServer().getPluginManager().disablePlugin(this);
        }

        settingsManager = new SettingsManager(this);

        // Registers all required listeners.
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

    /**
     * Get the Settings Manager, which gives us access to the plugin Configuration.
     * @return Settings Manager.
     */
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    /**
     * Get a CustomItem from an ItemStack.
     * @param item ItemStack to check.
     * @return CustomItem object.
     */
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
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return customItem;
    }

    /**
     * Get a map of all custom items.
     * @return All registered custom items.
     */
    public static Map<String, CustomItem> getCustomItems() {
        return  items;
    }

    /**
     * Get a custom item based off an Id.
     * @param id Id of the Custom Item.
     * @return Custom Item object,
     */
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

    /**
     * Registers an item without the use of addons.
     * @param item Item to register.
     */
    public static void registerItem(CustomItem item) {
        items.put(item.getID(), item);
    }

    /**
     * Get the AddonManager instance, which manages all Addons.
     * @return AddonManager.
     */
    public static AddonManager getAddonManager() {
        return addonManager;
    }
}