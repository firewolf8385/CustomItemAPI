package com.github.firewolf8385.customitemapi.items.attributes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages all attribute objects.
 */
public class AttributeManager {
    private final Map<String, ItemAttribute> attributes = new HashMap<>();

    /**
     * Registers an attribute.
     * @param attribute Attribute to register.
     */
    public void registerAttribute(ItemAttribute attribute) {
        attributes.put(attribute.getId(), attribute);
    }

    /**
     * Get an attribute based off its id.
     * @param id Id of the attribute you are trying to get.
     * @return The item attribute associated with the given id. null if invalid.
     */
    public ItemAttribute getAttribute(String id) {
        if(attributes.containsKey(id)) {
            return attributes.get(id);
        }

        return null;
    }

    /**
     * Gets a collection of all registered attributes.
     * @return All registered attributes.
     */
    public Collection<ItemAttribute> getAttributes() {
        return attributes.values();
    }
}