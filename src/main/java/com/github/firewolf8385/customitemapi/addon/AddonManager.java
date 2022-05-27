package com.github.firewolf8385.customitemapi.addon;

import java.util.HashMap;
import java.util.Map;

public class AddonManager {
    private final Map<String, Addon> addons = new HashMap<>();

    public AddonManager() {

    }

    public Addon getAddon(String id) {
        return addons.getOrDefault(id, null);
    }

    public Map<String, Addon> getAddons() {
        return addons;
    }

    public void registerAddon(Addon addon) {
        addons.put(addon.getId(), addon);
    }
}