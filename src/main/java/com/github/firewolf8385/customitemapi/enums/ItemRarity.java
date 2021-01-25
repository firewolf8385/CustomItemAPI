package com.github.firewolf8385.customitemapi.enums;

import net.md_5.bungee.api.ChatColor;

public enum ItemRarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHICAL(ChatColor.LIGHT_PURPLE);

    private ChatColor color;

    ItemRarity(ChatColor color) {
        this.color = color;
    }

    /**
     * Get the color of a rarity.
     * @return Color of the rarity.
     */
    public ChatColor getColor() {
        return color;
    }
}
