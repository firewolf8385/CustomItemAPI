package com.github.firewolf8385.customitemapi.commands;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import com.github.firewolf8385.customitemapi.gui.AddonBrowseGUI;
import com.github.firewolf8385.customitemapi.gui.ItemBrowseGUI;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.ItemRarity;
import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import com.github.firewolf8385.customitemapi.utils.items.EnchantmentUtils;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import com.github.firewolf8385.customitemapi.utils.items.ItemUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.util.StringUtil;

import java.util.*;

public class ItemCMD extends AbstractCommand {
    private final CustomItemAPI plugin;

    /**
     * Registers the command.
     */
    public ItemCMD(CustomItemAPI plugin) {
        super("items", "ci.item", true);
        this.plugin = plugin;
    }

    /**
     * Executes the command.
     * @param sender The Command Sender.
     * @param args Arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            help(sender);
            return;
        }

        switch (args[0]) {
            case "give" -> give(sender, args);

            case "debug" -> debug(sender);

            case "update" -> update(sender, args);

            case "set" -> set(sender, args);

            case "browse" -> {
                if(!(sender instanceof Player player)) {
                    return;
                }

                new ItemBrowseGUI(1).open(player);
            }

            case "addons" -> {
                if(!(sender instanceof Player player)) {
                    return;
                }

                new AddonBrowseGUI(1).open(player);
            }

            case "enchant" -> enchant(sender, args);

            case "info" -> {
                ChatUtils.chat(sender, "&8&m+-----------------------***-----------------------+");
                ChatUtils.centeredChat(sender, "&a&lCustomItemAPI");
                sender.sendMessage("");
                ChatUtils.chat(sender, "  &8» &aAuthor: &f" + plugin.getDescription().getAuthors().get(0));
                ChatUtils.chat(sender, "  &8» &aVersion: &f" + plugin.getDescription().getVersion());
                ChatUtils.chat(sender, "  &8» &aGitHub: &fhttps://github.com/firewolf8385/CustomItemsAPI");
                ChatUtils.chat(sender, "&8&m+-----------------------***-----------------------+");
            }

            case "rename" -> rename(sender, args);

            default -> help(sender);
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
            return Arrays.asList("give", "update", "set", "browse", "info", "rename", "enchant", "debug", "addons");
        }

        switch (args[0]) {
            case "give" -> {
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
            }

            case "update", "rename" -> {
                return Collections.emptyList();
            }

            case "set" -> {
                if(args.length == 2) {
                    return Arrays.asList("rarity", "upgraded");
                }

                if(args.length == 3) {
                    if(args[1].equalsIgnoreCase("rarity")) {
                        List<String> rarities = new ArrayList<>();
                        for(ItemRarity rarity : ItemRarity.values()) {
                            rarities.add(rarity.toString().toLowerCase(Locale.ROOT).replace(" ", "_"));
                        }

                        return rarities;
                    }

                    if(args[1].equalsIgnoreCase("upgraded")) {
                        return Arrays.asList("true", "false");
                    }

                    return Collections.emptyList();
                }
            }

            case  "enchant" -> {
                if(args.length == 2) {
                    List<String> enchantments = new ArrayList<>();
                    CustomItemAPI.getCustomEnchantments().forEach(enchantment -> {
                        String id = ((CustomEnchantment) enchantment).getId();
                        enchantments.add(id);
                    });

                    return enchantments;
                }

                return Collections.emptyList();
            }
        }

        return Collections.emptyList();
    }

    /**
     * Shows the plugin help menu.
     * @param sender Command sender.
     */
    private void help(CommandSender sender) {
        ChatUtils.chat(sender, "&8&m+-----------------------***-----------------------+");
        ChatUtils.centeredChat(sender, "&a&lCustomItemAPI");
        ChatUtils.chat(sender, "  &8» &a/items browse");
        ChatUtils.chat(sender, "  &8» &a/items give");
        ChatUtils.chat(sender, "  &8» &a/items info");
        ChatUtils.chat(sender, "  &8» &a/items update");
        ChatUtils.chat(sender, "  &8» &a/items set [attribute] [value]");
        ChatUtils.chat(sender, "&8&m+-----------------------***-----------------------+");
    }

    private void debug(CommandSender sender) {
        if(!sender.hasPermission("customitems.debug")) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command!");
            return;
        }

        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        ChatUtils.chat(sender, "&a&lEnchantments:");
        for(Enchantment enchantment : item.getEnchantments().keySet()) {
            ChatUtils.chat(sender, "  - " + EnchantmentUtils.enchantmentToString(enchantment) + " " + EnchantmentUtils.IntegerToRomanNumeral(item.getEnchantments().get(enchantment)));
        }

        if(item.getItemMeta() instanceof EnchantmentStorageMeta storage) {
            ChatUtils.chat(sender, "&a&lStored Enchantments");
            for(Enchantment enchantment : storage.getStoredEnchants().keySet()) {
                ChatUtils.chat(sender, "  - " + EnchantmentUtils.enchantmentToString(enchantment) + " " + EnchantmentUtils.IntegerToRomanNumeral(storage.getStoredEnchants().get(enchantment)));
            }
        }
    }

    private void enchant(CommandSender sender, String[] args) {
        if(!sender.hasPermission("customitems.enchant")) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command!");
            return;
        }

        if(args.length < 2) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cUsage: /item enchant [enchantment]");
            return;
        }

        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        if(!CustomItemAPI.isCustomItem(item)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cThat is not a custom item!");
            return;
        }

        Enchantment enchantment = CustomItemAPI.getEnchantment(args[1].toLowerCase());

        int level = 1;
        if(args.length == 3) {
            level = Integer.parseInt(args[2]);
        }

        if(item.getItemMeta() instanceof EnchantmentStorageMeta temp) {
            temp.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(temp);
        }

        ItemBuilder enchantedItem = new ItemBuilder(item);

        if(!(item.getItemMeta() instanceof EnchantmentStorageMeta)) {
            enchantedItem.addEnchantment(enchantment, level);
        }

        CustomItem customItem = CustomItemAPI.fromItemStack(item);
        p.getInventory().setItemInMainHand(customItem.update(enchantedItem.build()));
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
        Player player = (Player) sender;

        switch (args[1]) {
            case "rarity" -> {
                if(args.length != 3) {
                    ChatUtils.chat(sender, "&c&l(&7!&c&l) &cUsage: /item set rarity [rarity]");
                    return;
                }

                ItemStack item = player.getInventory().getItemInMainHand();

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
                player.getInventory().setItemInMainHand(customItem.toItemStack());
            }

            case "upgraded" -> {
                if(args.length != 3) {
                    ChatUtils.chat(sender, "&c&l(&7!&c&l) &cUsage: /item set upgraded [true/false]");
                    return;
                }

                ItemStack item = player.getInventory().getItemInMainHand();

                if(!CustomItemAPI.isCustomItem(item)) {
                    ChatUtils.chat(sender, "&cThat item is not a custom item!");
                    return;
                }

                if(args[2].equalsIgnoreCase("true")) {
                    ItemStack upgraded = new ItemBuilder(item).setPersistentData("ci-upgraded", "true").build();
                    CustomItem customItem = CustomItemAPI.fromItemStack(upgraded);
                    player.getInventory().setItemInMainHand(customItem.update(upgraded));
                }

                if(args[2].equalsIgnoreCase("false")) {
                    ItemStack upgraded = new ItemBuilder(item).setPersistentData("ci-upgraded", "false").build();
                    CustomItem customItem = CustomItemAPI.fromItemStack(upgraded);
                    player.getInventory().setItemInMainHand(customItem.update(upgraded));
                }
            }
        }
    }

    /**
     * Renames a custom item.
     * @param sender Command sender.
     * @param args Command arguments.
     */
    private void rename(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        if(!CustomItemAPI.isCustomItem(item)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cThat is not a custom item!");
            return;
        }

        if(args.length < 2) {
            ChatUtils.chat(sender, "&a&l(&7!&a&l) &aItem Display Name has been reset.");
            ItemBuilder builder = new ItemBuilder(item)
                    .setPersistentData("ci-display_name", "");

            p.getInventory().setItemInMainHand(CustomItemAPI.fromItemStack(builder.build()).update(builder.build()));
            return;
        }

        ItemBuilder builder = new ItemBuilder(item)
                .setPersistentData("ci-display_name", StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " "));

        p.getInventory().setItemInMainHand(CustomItemAPI.fromItemStack(builder.build()).update(builder.build()));

        ChatUtils.chat(sender, "&a&l(&7!&a&l) &aItem name has been set to " + CustomItemAPI.getRarity(item).getColor() + StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ") + "&a.");
    }
}