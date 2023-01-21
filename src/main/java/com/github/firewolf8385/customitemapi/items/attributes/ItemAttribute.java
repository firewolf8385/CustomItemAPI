package com.github.firewolf8385.customitemapi.items.attributes;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;

/**
 * Creates a modifier that can be added to custom items to change their properties.
 */
public abstract class ItemAttribute {
    private final String id;
    private final String name;

    /**
     * Creates the attribute object,
     * @param id Id of the attribute.
     * @param name Display name of the attribute.
     */
    public ItemAttribute(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Runs when the attribute is added to an item.
     * Allows you to add data to the item.
     * @param customItem The custom item the attribute is being added to.
     * @param item ItemBuilder of the item the attribute is being added to.
     * @param value The value of the attribute being added.
     */
    public void addedToItem(CustomItem customItem, ItemBuilder item, int value) {}

    /**
     * Get the id of the attribute.
     * This is used to differentiate one attribute from another.
     * @return Id of the attribute.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the display name of the attribute.
     * This is displayed on the item.
     * @return Display name of the attribute.
     */
    public String getName() {
        return name;
    }
}