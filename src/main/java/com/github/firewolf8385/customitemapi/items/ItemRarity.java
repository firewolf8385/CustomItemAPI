package com.github.firewolf8385.customitemapi.items;

import net.md_5.bungee.api.ChatColor;

/**
 * Represents the rarity of an item.
 */
public enum ItemRarity {
    NONE(ChatColor.RESET, ""),
    COMMON(ChatColor.WHITE, "COMMON"),
    UNCOMMON(ChatColor.GREEN, "UNCOMMON"),
    RARE(ChatColor.BLUE, "RARE"),
    EPIC(ChatColor.DARK_PURPLE, "EPIC"),
    LEGENDARY(ChatColor.GOLD, "LEGENDARY"),
    MYTHIC(ChatColor.LIGHT_PURPLE, "MYTHIC"),
    SUPREME(ChatColor.DARK_RED, "SUPREME"),
    SPECIAL(ChatColor.RED, "SPECIAL"),
    VERY_SPECIAL(ChatColor.RED, "VERY SPECIAL"),
    UNFINISHED(ChatColor.GRAY, "UNFINISHED");

    private final ChatColor color;
    private final String toString;

    /**
     * Creates a rarity.
     * @param color The color of the rarity.
     * @param toString The rarity in string form.
     */
    ItemRarity(ChatColor color, String toString) {
        this.color = color;
        this.toString = toString;
    }

    /**
     * Get the color of the rarity.
     * @return The ChatColor of the rarity.
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     * Convert the rarity of a String.
     * @return String form of the rarity.
     */
    public String toString() {
        return toString;
    }
}