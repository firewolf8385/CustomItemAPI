package com.github.firewolf8385.customitemapi.items;

/**
 * Represents the type of item a Custom Item is.
 */
public enum ItemType {
    NONE(""),
    SWORD(" SWORD"),
    BOW(" BOW"),
    CROSSBOW(" CROSSBOW"),
    TRIDENT(" TRIDENT"),
    PICKAXE(" PICKAXE"),
    AXE(" AXE"),
    SHOVEL(" SHOVEL"),
    HOE(" HOE"),
    ROD(" ROD"),
    HELMET(" HELMET"),
    CHESTPLATE(" CHESTPLATE"),
    LEGGINGS(" LEGGINGS"),
    BOOTS(" BOOTS"),
    WAND(" WAND"),
    COMPASS(" COMPASS"),
    WINGS(" WINGS"),
    TOTEM(" TOTEM"),
    DYE(" DYE"),
    PROJECTILE(" PROJECTILE");

    private final String toString;

    /**
     * Create the ItemType.
     * @param toString The string form of the ItemType.
     */
    ItemType(String toString) {
        this.toString = toString;
    }

    /**
     * Convert the ItemType to a String.
     * @return String of the ItemType.
     */
    public String toString() {
        return toString;
    }
}