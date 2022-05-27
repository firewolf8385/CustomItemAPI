package com.github.firewolf8385.customitemapi.settings;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Allows easy access to plugin configuration
 * files. Stores spawn and arena locations.
 */
public class SettingsManager {
    private final CustomItemAPI plugin;

    private FileConfiguration config;
    private File configFile;
    private FileConfiguration messages;
    private File messagesFile;

    public SettingsManager(CustomItemAPI plugin) {
        this.plugin = plugin;

        config = plugin.getConfig();
        config.options().copyDefaults(true);
        configFile = new File(plugin.getDataFolder(), "config.yml");
        plugin.saveConfig();

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        messages = YamlConfiguration.loadConfiguration(messagesFile);

        if(!messagesFile.exists())
            plugin.saveResource("messages.yml", false);
    }

    /**
     * Get the main configuration file.
     * @return Main configuration file.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Get the maps configuration file.
     * @return Maps configuration file.
     */
    public FileConfiguration getMessages() {
        return messages;
    }
}