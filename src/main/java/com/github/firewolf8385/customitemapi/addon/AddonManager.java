package com.github.firewolf8385.customitemapi.addon;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages all addons.
 */
public class AddonManager {
    private final Map<String, Addon> addons = new HashMap<>();

    /**
     * Get an addon from its id.
     * @param id Id of the Addon.
     * @return Addon object.
     */
    public Addon getAddon(String id) {
        return addons.getOrDefault(id, null);
    }

    /**
     * Gets a map of all addons and their ids.
     * @return All addons with their ids.
     */
    public Map<String, Addon> getAddons() {
        return addons;
    }

    /**
     * Registers an addon with the API.
     * @param addon Addon to register.
     */
    public void registerAddon(Addon addon) {
        addons.put(addon.getId(), addon);
    }
}