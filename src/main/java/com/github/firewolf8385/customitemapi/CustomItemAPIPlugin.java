package com.github.firewolf8385.customitemapi;

import com.github.firewolf8385.customitemapi.addon.Addon;
import com.github.firewolf8385.customitemapi.addon.AddonManager;
import com.github.firewolf8385.customitemapi.commands.AbstractCommand;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.items.attributes.AttributeManager;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.items.attributes.attributes.*;
import com.github.firewolf8385.customitemapi.items.items.*;
import com.github.firewolf8385.customitemapi.listeners.*;
import com.github.firewolf8385.customitemapi.listeners.SmithItemListener;
import com.github.firewolf8385.customitemapi.settings.SettingsManager;
import com.github.firewolf8385.customitemapi.utils.gui.GUIListeners;
import com.github.firewolf8385.customitemapi.utils.items.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * This plugin allows easy creation and management of custom items.
 */
public final class CustomItemAPIPlugin extends JavaPlugin {
    private static final Map<String, CustomItem> items = new TreeMap<>();
    private static final AddonManager addonManager = new AddonManager();
    private SettingsManager settingsManager;
    private static final AttributeManager attributeManager = new AttributeManager();

    /**
     * This is called when Paper first loads the plugin.
     */
    @Override
    public void onEnable() {
        // Makes sure the server is running paper.
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
        }
        catch (ClassNotFoundException exception) {
            Bukkit.getLogger().severe("CustomItemAPI only works on Paper and its forks.");
            getServer().getPluginManager().disablePlugin(this);
        }

        settingsManager = new SettingsManager(this);

        // Registers all required listeners.
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnchantItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityShootBowListener(this), this);
        Bukkit.getPluginManager().registerEvents(new FurnaceSmeltListener(), this);
        Bukkit.getPluginManager().registerEvents(new GUIListeners(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDragListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerAmorChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemConsumeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemMendListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PrepareAnvilListener(), this);
        Bukkit.getPluginManager().registerEvents(new PrepareItemCraftListener(), this);
        Bukkit.getPluginManager().registerEvents(new PrepareResultListener(), this);
        Bukkit.getPluginManager().registerEvents(new SmithItemListener(), this);

        // Plugin startup logic
        AbstractCommand.registerCommands(this);

        // Metrics
        new Metrics(this, 10115);

        // Registers the default addon.
        Addon addon = new Addon(this, "customitemapi", Material.GOLDEN_SWORD)
                .registerItem(new TestStickItem())
                .registerItem(new AdminSwordItem())
                .registerItem(new SpeedStickItem())
                .registerItem(new AdminBowItem())
                .registerItem(new AdminCrossbowItem())
                .registerItem(new AdminTridentItem())
                .registerAttribute(new AttackSpeedAttribute())
                .registerAttribute(new DefenseAttribute())
                .registerAttribute(new HealthAttribute())
                .registerAttribute(new LuckAttribute())
                .registerAttribute(new SpeedAttribute())
                .registerAttribute(new ToughnessAttribute())
                .registerAttribute(new DamageAttribute())
                .registerAttribute(new CritChanceAttribute())
                .registerAttribute(new CritDamageAttribute());
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

        String id = ItemUtils.getStringData(item, "ci-id");
        return getItem(id);
    }

    /**
     * Get an addon based on its id.
     * @param id Id of the addon.
     * @return Addon corresponding to that id.
     */
    public static Addon getAddon(String id) {
        return addonManager.getAddon(id);
    }

    /**
     * Get a collection of all registered addons.
     * @return All registered addons.
     */
    public static Collection<Addon> getAddons() {
        return addonManager.getAddons().values();
    }

    /**
     * Get an ItemAttribute based on its id.
     * @param attributeID Id of the attribute.
     * @return Associated item attribute.
     */
    public static ItemAttribute getAttribute(String attributeID) {
        return attributeManager.getAttribute(attributeID);
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
     * Get the rarity of a Custom Item.
     * returns NONE if the item is not a custom item or if it has no rarity.
     * @param item ItemStack to get rarity of.
     * @return Rarity of the item.
     */
    public static ItemRarity getRarity(ItemStack item) {
        // Exit if the item isn't a custom item.
        if(!isCustomItem(item)) {
            return ItemRarity.NONE;
        }

        // Gets the ItemRarity of item.
        ItemRarity rarity = CustomItemAPIPlugin.fromItemStack(item).getRarity();

        // Checks if the item is upgraded.
        if(isUpgraded(item)) {
            // If so, returns the rarity above the existing rarity.
            return ItemRarity.getByWeight(rarity.getWeight() + 1);
        }

        // if not, returns the rarity.
        return rarity;
    }

    /**
     * Check if an item is a Custom Item.
     * @param item Item to check.
     * @return Whether it is a Custom Item.
     */
    public static boolean isCustomItem(ItemStack item) {
        // Null items can't be custom items.
        if(item == null) {
            return false;
        }

        // No ItemMeta means no custom item
        if(item.getItemMeta() == null) {
            return false;
        }

        // Custom Items have an id in their nbt
        if(ItemUtils.getStringData(item, "ci-id") == null) {
            return false;
        }

        // The custom id must be valid.
        CustomItem customItem = getItem(ItemUtils.getStringData(item, "ci-id"));
        return customItem != null;
    }

    /**
     * Check if an item has been set as upgraded.
     * @param item Item to check if it has been upgraded.
     * @return Whether is has been upgraded.
     */
    public static boolean isUpgraded(ItemStack item) {
        // Non-custom items can't be upgraded.
        if(!isCustomItem(item)) {
            return false;
        }

        // Item can only be upgraded if it that the ci-upgraded namespace.
        if(ItemUtils.getStringData(item, "ci-upgraded") != null) {
            return ItemUtils.getStringData(item, "ci-upgraded").equals("true");
        }

        // all other items are not upgraded.
        return false;
    }

    /**
     * Register an addon with CustomItemAPI.
     * @param addon Addon to register.
     */
    public static void registerAddon(Addon addon) {
        addonManager.registerAddon(addon);
    }

    /**
     * Register an attribute to a specific addon.
     * @param addon Addon to register attribute to.
     * @param attribute Attribute to register to it.
     */
    public static void registerAttribute(Addon addon, ItemAttribute attribute) {
        attributeManager.registerAttribute(attribute);
    }

    /**
     * Registers an item without the use of addons.
     * @param item Item to register.
     */
    public static void registerItem(Addon addon, CustomItem item) {
        items.put(item.getID(), item);
    }

    /**
     * Get the AddonManager instance, which manages all Addons.
     * Use methods in CustomItemAPI.java instead.
     * @return AddonManager.
     */
    @Deprecated
    public static AddonManager getAddonManager() {
        return addonManager;
    }

    /**
     * Gets an instance of the Attribute manager, which manages custom item attributes.
     * Use methods in CustomItemAPI.java instead.
     * @return AttributeManager.
     */
    @Deprecated
    public static AttributeManager getAttributeManager() {
        return attributeManager;
    }
}