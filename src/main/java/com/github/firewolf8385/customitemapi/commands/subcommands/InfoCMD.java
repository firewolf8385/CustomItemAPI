package com.github.firewolf8385.customitemapi.commands.subcommands;

import com.github.firewolf8385.customitemapi.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ChatUtils.centeredChat(sender, "&b&lCustomItemAPI");
        ChatUtils.chat(sender, "  &8» &bAuthor: &ffirewolf8385");
        ChatUtils.chat(sender, "  &8» &bVersion: &f1.0");
        return true;
    }
}
