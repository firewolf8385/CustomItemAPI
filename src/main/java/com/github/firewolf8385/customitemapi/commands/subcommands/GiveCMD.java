package com.github.firewolf8385.customitemapi.commands.subcommands;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.objects.CustomItem;
import com.github.firewolf8385.customitemapi.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("customitems.give")) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command!");
            return true;
        }

        if(args.length < 2) {
            ChatUtils.chat(sender, "&c&l(&7&l!&c&l) &cUsage: /ci give [player] [item] <amount>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            ChatUtils.chat(sender, "&c&l(&7&l!&c&l) &cThat player is not online!");
            return true;
        }

        CustomItem item = CustomItemAPI.getCustomItems().get(args[1]);

        if(item == null) {
            ChatUtils.chat(sender, "&c&l(&7&l!&c&l) &cThat item does not exist.");
            return true;
        }

        int amount = 1;
        if(args.length == 3) {
            amount = Integer.parseInt(args[2]);
        }

        for(int i = 0; i < amount; i++) {
            target.getInventory().addItem(item.getItemStack());
        }

        ChatUtils.chat(target, "&a&l(&7&l!&a&l) &aItem has been given.");

        return true;
    }
}
