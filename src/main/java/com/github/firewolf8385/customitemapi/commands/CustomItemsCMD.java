package com.github.firewolf8385.customitemapi.commands;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.commands.subcommands.GiveCMD;
import com.github.firewolf8385.customitemapi.commands.subcommands.InfoCMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomItemsCMD implements CommandExecutor, TabCompleter {
    private final GiveCMD giveCMD;
    private final InfoCMD infoCMD;

    public CustomItemsCMD() {
        this.giveCMD = new GiveCMD();
        this.infoCMD = new InfoCMD();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            infoCMD.onCommand(sender, cmd, label, Arrays.copyOfRange(args, 0, args.length));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                giveCMD.onCommand(sender, cmd, label, Arrays.copyOfRange(args, 1, args.length));
                break;
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>(CustomItemAPI.getCustomItems().keySet());
        return (args.length == 3) ? StringUtil.copyPartialMatches(args[2], completions, new ArrayList<>()) : null;
    }
}