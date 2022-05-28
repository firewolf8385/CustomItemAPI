package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the rarity of an item.
 */
public enum ItemRarity {
    NONE("&r", "", 0),
    COMMON("&f", "COMMON", 1),
    UNCOMMON("&a", "UNCOMMON", 2),
    RARE("&9", "RARE", 3),
    EPIC("&5", "EPIC", 4),
    LEGENDARY("&6", "LEGENDARY", 5),
    MYTHIC("&d", "MYTHIC", 6),
    SUPREME("&4", "SUPREME", 7),
    SPECIAL("&c", "SPECIAL", 8),
    VERY_SPECIAL("&c", "VERY SPECIAL", 9),
    UNFINISHED("&7", "UNFINISHED", 10);

    private final String defaultColor;
    private final String defaultName;
    private final int weight;

    /**
     * Creates a rarity.
     * @param defaultColor The color of the rarity.
     * @param defaultName The rarity in string form.
     * @param weight How important a rarity is. Higher is better.
     */
    ItemRarity(String defaultColor, String defaultName, int weight) {
        this.defaultColor = defaultColor;
        this.defaultName = defaultName;
        this.weight = weight;
    }

    /**
     * Get an ItemRarity by it's weight.
     * @param weight Weight to get.
     * @return ItemRarity.
     */
    public static ItemRarity getByWeight(int weight) {
        for(ItemRarity rarity : values()) {
            if(rarity.getWeight() == weight) {
                return rarity;
            }
        }

        return NONE;
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

    /**
     * Get the weight of the rarity.
     * Higher weights take more priority.
     * @return Weight of the rarity.
     */
    public int getWeight() {
        return weight;
    }
}