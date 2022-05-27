package com.github.firewolf8385.customitemapi.commands;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    private final String permission;
    private final boolean canConsoleUse;
    private static CustomItemAPI plugin;

    /**
     * Creates a new AbstractCommand.
     * @param commandName Name of the command.
     * @param permission Permission required to use the command.
     * @param canConsoleUse Whether or not console can use the command.
     */
    public AbstractCommand(final String commandName, final String permission, final boolean canConsoleUse) {
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        plugin.getCommand(commandName).setExecutor(this);
        plugin.getCommand(commandName).setTabCompleter(this);
    }

    /**
     * Registers all commands in the plugin.
     * @param pl Plugin.
     */
    public static void registerCommands(CustomItemAPI pl) {
        plugin = pl;

        new ItemCMD(pl);
    }

    /**
     * Executes the command.
     * @param sender The Command Sender.
     * @param args Arguments of the command.
     */
    public abstract void execute(CommandSender sender, String[] args);

    /**
     * Processes early execution of the plugin.
     * @param sender Command Sender.
     * @param cmd The Command.
     * @param label Command Label.
     * @param args Command Arugments.
     * @return Successful Completion.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!permission.equals("") && !sender.hasPermission(permission)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command.");
            return true;
        }
        if(!canConsoleUse && !(sender instanceof Player)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cOnly players can use that command.");
            return true;
        }
        execute(sender, args);
        return true;
    }

    /**
     * Sets the tab completion of the plugin.
     * @param args Arugments of the command.
     * @return Tab completion.
     */
    public abstract List<String> tabComplete(String[] args);

    /**
     * Processes command tab completion.
     * @param sender Command sender.
     * @param cmd Command.
     * @param label Command label.
     * @param args Arguments of the command.
     * @return Tab completion.
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return tabComplete(args);
    }
}