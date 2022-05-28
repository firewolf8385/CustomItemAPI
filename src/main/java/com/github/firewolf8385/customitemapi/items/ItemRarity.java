package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the rarity of an item.
 */
public enum ItemRarity {
    NONE("&r", ""),
    COMMON("&f", "COMMON"),
    UNCOMMON("&a", "UNCOMMON"),
    RARE("&9", "RARE"),
    EPIC("&5", "EPIC"),
    LEGENDARY("&6", "LEGENDARY"),
    MYTHIC("&d", "MYTHIC"),
    SUPREME("&4", "SUPREME"),
    SPECIAL("&c", "SPECIAL"),
    VERY_SPECIAL("&c", "VERY SPECIAL"),
    UNFINISHED("&7", "UNFINISHED");

    private final String defaultColor;
    private final String defaultName;

    /**
     * Creates a rarity.
     * @param defaultColor The color of the rarity.
     * @param defaultName The rarity in string form.
     */
    ItemRarity(String defaultColor, String defaultName) {
        this.defaultColor = defaultColor;
        this.defaultName = defaultName;
    }

    /**
     * Get the configured color of the ItemRarity.
     * @return Configured color.
     */
    public String getColor() {
        CustomItemAPI plugin = ((CustomItemAPI) JavaPlugin.getProvidingPlugin(CustomItemAPI.class));
        String configColor = plugin.getSettingsManager().getConfig().getString("Rarities." + this + ".color");

        if(configColor != null) {
            return configColor;
        }
        else {
            return defaultColor;
        }
    }

    /**
     * Get the configured name of the ItemRarity.
     * @return Configured name.
     */
    public String getName() {
        CustomItemAPI plugin = ((CustomItemAPI) JavaPlugin.getProvidingPlugin(CustomItemAPI.class));
        String configName = plugin.getSettingsManager().getConfig().getString("Rarities." + this + ".name");

        if(configName != null) {
            return configName;
        }
        else {
            return defaultName;
        }
    }
}