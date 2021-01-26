package com.github.firewolf8385.customitemapi;

import com.github.firewolf8385.customitemapi.commands.CustomItemsCMD;
import com.github.firewolf8385.customitemapi.listeners.PlayerItemDamageListener;
import com.github.firewolf8385.customitemapi.objects.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class CustomItemAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Enables stat tracking through bStats.
        new MetricsLite(this, 10115);

        // Registers the main plugin command.
        getCommand("customitemsapi").setExecutor(new CustomItemsCMD());

        // Registers events
        Bukkit.getPluginManager().registerEvents(new PlayerItemDamageListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Map<String, CustomItem> getCustomItems() {
        return CustomItem.getAllItems();
    }
}