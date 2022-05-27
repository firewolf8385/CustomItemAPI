package com.github.firewolf8385.customitemapi.commands;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.gui.ItemBrowseGUI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import java.util.*;

public class ItemCMD extends AbstractCommand {

    /**
     * Registers the command.
     */
    public ItemCMD() {
        super("items", "ci.item", true);
    }

    /**
     * Executes the command.
     * @param sender The Command Sender.
     * @param args Arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            return;
        }

        switch (args[0]) {
            case "give":
                give(sender, args);
                break;
            case "update":
                update(sender, args);
                break;
            case "set":
                set(sender, args);
                break;
            case "browse":
                if(!(sender instanceof Player)) {
                    return;
                }

                Player player = (Player) sender;
                new ItemBrowseGUI(1).open(player);
                break;
            default:
                sender.sendMessage("");
                ChatUtils.centeredChat(sender, "&a&lCustomItemAPI");
                sender.sendMessage("");
                ChatUtils.chat(sender, "  &8» &aAuthor: &ffirewolf8385");
                ChatUtils.chat(sender, "  &8» &aVersion: &f1.0");
                ChatUtils.chat(sender, "  &8» &aGitHub: &fhttps://github.com/firewolf8385/CustomItemsAPI");
                sender.sendMessage("");
                break;
        }
    }

    /**
     * Update tab completions.
     * @param args Arugments of the command.
     * @return Tab completion.
     */
    @Override
    public List<String> tabComplete(String[] args) {

        if(args.length == 1) {
            return Arrays.asList("give", "update", "set", "browse");
        }

        switch (args[0]) {
            case "give":
                if(args.length == 2) {
                    List<String> online = new ArrayList<>();

                    for(Player p : Bukkit.getOnlinePlayers()) {
                        online.add(p.getName());
                    }

                    return online;
                }

                if(args.length == 3) {
                    List<String> completions = new ArrayList<>(CustomItemAPI.getCustomItems().keySet());
                    return StringUtil.copyPartialMatches(args[2], completions, new ArrayList<>());
                }

                if(args.length == 4) {
                    return Collections.singletonList("1");
                }

                return Collections.emptyList();
            case "update":
                return Collections.emptyList();
            case "set":
                if(args.length == 2) {
                    return Arrays.asList("rarity");
                }

                if(args.length == 3) {
                    List<String> rarities = new ArrayList<>();
                    for(ItemRarity rarity : ItemRarity.values()) {
                        rarities.add(rarity.toString().toLowerCase(Locale.ROOT).replace(" ", "_"));
                    }

                    return rarities;
                }
                break;
        }

        return Collections.emptyList();
    }

    /**
     * Give an item.
     * @param sender Command sender.
     * @param args Command arguments.
     */
    private void give(CommandSender sender, String[] args) {
        if(!sender.hasPermission("customitems.give")) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command!");
            return;
        }

        if(args.length < 3) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cUsage: /item give [player] [item] <amount>");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if(target == null) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cThat player is not online!");
            return;
        }

        CustomItem item = CustomItemAPI.getCustomItems().get(args[2]);

        if(item == null) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cThat item does not exist.");
            return;
        }

        int amount = 1;
        if(args.length == 4) {
            amount = Integer.parseInt(args[3]);
        }

        for(int i = 0; i < amount; i++) {
            target.getInventory().addItem(item.toItemStack());
        }

        ChatUtils.chat(target, "&a&l(&7!&a&l) &aItem has been given.");
    }

    /**
     * Update an item.
     * @param sender Command sender.
     * @param args Command arguments.
     */
    private void update(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        if(!CustomItemAPI.isCustomItem(item)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cThat is not a custom item!");
            return;
        }

        p.getInventory().setItemInMainHand(CustomItemAPI.fromItemStack(item).toItemStack());

        ChatUtils.chat(sender, "&a&l(&7!&a&l) &aItem has been updated.");
    }

    /**
     * Change something about the item.
     * @param sender Command Sender
     * @param args Command arguments.
     */
    private void set(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        switch (args[1]) {
            case "rarity":
                if(args.length != 3) {
                    ChatUtils.chat(sender, "&c&l(&7!&c&l) &cUsage: /item set rarity [rarity]");
                    return;
                }

                ItemStack item = p.getInventory().getItemInMainHand();

                if(!CustomItemAPI.isCustomItem(item)) {
                    ChatUtils.chat(sender, "&cThat item is not a custom item!");
                    return;
                }

                ItemRarity rarity = ItemRarity.valueOf(args[2].toUpperCase());

                if(rarity == null) {
                    return;
                }

                CustomItem customItem = CustomItemAPI.fromItemStack(item);
                customItem.setRarity(rarity);
                p.getInventory().setItemInMainHand(customItem.toItemStack());

                break;
        }
    }
}