package com.github.firewolf8385.customitemapi.commands;

import com.github.firewolf8385.customitemapi.commands.subcommands.GiveCMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CustomItemsCMD implements CommandExecutor {
    private GiveCMD giveCMD;

    public CustomItemsCMD() {
        this.giveCMD = new GiveCMD();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            //TODO: Add GUI.
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                giveCMD.onCommand(sender, cmd, label, Arrays.copyOfRange(args, 1, args.length));
                break;
        }
        return true;
    }
}