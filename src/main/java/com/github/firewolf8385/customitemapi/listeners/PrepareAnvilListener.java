package com.github.firewolf8385.customitemapi.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class PrepareAnvilListener implements Listener {



    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        ItemStack firstItem = event.getInventory().getFirstItem();
        ItemStack secondItem = event.getInventory().getSecondItem();

        // Makes sure the items are not null.
        if(firstItem == null || secondItem == null) {
            if(result == null) {
                return;
            }

            ItemMeta meta = result.getItemMeta();
            CustomItem customItem = CustomItemAPIPlugin.fromItemStack(result);

            if(ChatColor.stripColor(meta.getDisplayName()) != ChatColor.stripColor(customItem.toItemStack().getItemMeta().getDisplayName())) {
                ItemBuilder builder = new ItemBuilder(result)
                        .setPersistentData("ci-display_name", ChatColor.stripColor(meta.getDisplayName()));
                event.setResult(customItem.update(builder.build()));
            }
            else {
                event.setResult(customItem.update(result));
            }
        }

        // Makes sure both items are custom items.
        if(!CustomItemAPIPlugin.isCustomItem(firstItem) || !CustomItemAPIPlugin.isCustomItem(secondItem)) {
            if(result == null) {
                return;
            }

            ItemMeta meta = result.getItemMeta();
            CustomItem customItem = CustomItemAPIPlugin.fromItemStack(result);

            if(customItem == null) {
                return;
            }

            if(ChatColor.stripColor(meta.getDisplayName()) != ChatColor.stripColor(customItem.toItemStack().getItemMeta().getDisplayName())) {
                ItemBuilder builder = new ItemBuilder(result)
                        .setPersistentData("ci-display_name", ChatColor.stripColor(meta.getDisplayName()));
                event.setResult(customItem.update(builder.build()));
            }
            else {
                event.setResult(customItem.update(result));
            }
        }

        CustomItem firstCustomItem = CustomItemAPIPlugin.fromItemStack(firstItem);
        CustomItem secondCustomItem = CustomItemAPIPlugin.fromItemStack(secondItem);

        // Makes sure the custom items are not null.
        if(firstCustomItem == null || secondCustomItem == null) {
            if(result == null) {
                return;
            }

            ItemMeta meta = result.getItemMeta();
            CustomItem customItem = CustomItemAPIPlugin.fromItemStack(result);

            if(ChatColor.stripColor(meta.getDisplayName()) != ChatColor.stripColor(customItem.toItemStack().getItemMeta().getDisplayName())) {
                ItemBuilder builder = new ItemBuilder(result)
                        .setPersistentData("ci-display_name", ChatColor.stripColor(meta.getDisplayName()));
                event.setResult(customItem.update(builder.build()));
            }
            else {
                event.setResult(customItem.update(result));
            }
        }

        if(firstCustomItem.getID().equals(secondCustomItem.getID()) || secondItem.getType() == Material.ENCHANTED_BOOK) {
            if(result == null) {
                result = new ItemStack(firstItem);
            }

            if(result.getItemMeta() instanceof EnchantmentStorageMeta storageMeta) {
                Map<Enchantment, Integer> currentEnchantments = new HashMap<>(storageMeta.getStoredEnchants());
                Map<Enchantment, Integer> newEnchantments = merge(firstItem, secondItem);

                for(Enchantment enchantment : currentEnchantments.keySet()) {
                    storageMeta.removeStoredEnchant(enchantment);
                }

                for(Enchantment enchantment : newEnchantments.keySet()) {
                    storageMeta.addStoredEnchant(enchantment, newEnchantments.get(enchantment), true);
                }

                result.setItemMeta(storageMeta);
            }
            else {
                Map<Enchantment, Integer> currentEnchantments = new HashMap<>(result.getEnchantments());
                Map<Enchantment, Integer> newEnchantments = merge(firstItem, secondItem);

                for(Enchantment enchantment : currentEnchantments.keySet()) {
                    result.removeEnchantment(enchantment);
                }

                for(Enchantment enchantment : newEnchantments.keySet()) {
                    result.addUnsafeEnchantment(enchantment, newEnchantments.get(enchantment));
                }
            }
        }

        /*
        if(firstCustomItem.getID().equals(secondCustomItem.getID())) {
            if(result == null) {
                result = new ItemStack(firstItem);
                event.getInventory().setRepairCost(5);
            }

            if(result.getItemMeta() instanceof EnchantmentStorageMeta storage) {

                if(secondItem.getItemMeta() instanceof  EnchantmentStorageMeta secondItemStorage) {
                    for(Enchantment enchantment : secondItemStorage.getStoredEnchants().keySet()) {
                        storage.addStoredEnchant(enchantment, secondItemStorage.getStoredEnchantLevel(enchantment), true);
                    }
                }
                else {
                    for(Enchantment enchantment : secondItem.getEnchantments().keySet()) {
                        storage.addStoredEnchant(enchantment, secondItem.getEnchantmentLevel(enchantment), true);
                    }
                }

                result.setItemMeta(storage);
            }
            else {
                if(secondItem.getItemMeta() instanceof  EnchantmentStorageMeta secondItemStorage) {
                    for(Enchantment enchantment : secondItemStorage.getStoredEnchants().keySet()) {
                        result.addUnsafeEnchantment(enchantment, secondItemStorage.getStoredEnchantLevel(enchantment));
                    }
                }
                else {
                    for(Enchantment enchantment : secondItem.getEnchantments().keySet()) {
                        result.addUnsafeEnchantment(enchantment, secondItem.getEnchantmentLevel(enchantment));
                    }
                }
            }
        }
        else {
            if(secondItem.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta storage = (EnchantmentStorageMeta) secondItem.getItemMeta();

                if(result == null) {
                    result = new ItemStack(firstItem);
                }

                for(Enchantment enchantment : storage.getStoredEnchants().keySet()) {
                    result.addUnsafeEnchantment(enchantment, storage.getStoredEnchantLevel(enchantment));
                }
            }
        }

         */

        if(result == null) {
            return;
        }

        ItemMeta meta = result.getItemMeta();
        CustomItem customItem = CustomItemAPIPlugin.fromItemStack(result);

        if(ChatColor.stripColor(meta.getDisplayName()) != ChatColor.stripColor(customItem.toItemStack().getItemMeta().getDisplayName())) {
            ItemBuilder builder = new ItemBuilder(result)
                    .setPersistentData("ci-display_name", ChatColor.stripColor(meta.getDisplayName()));
            event.setResult(customItem.update(builder.build()));
        }
        else {
            event.setResult(customItem.update(result));
        }
    }

    /**
     * Merges the enchantments of two ItemStacks.
     * @param first First ItemStack.
     * @param second Second ItemStack.
     * @return Map with the resulting enchantments and levels.
     */
    private Map<Enchantment, Integer> merge(ItemStack first, ItemStack second) {
        Map<Enchantment, Integer> finalEnchantments = new HashMap<>();
        Map<Enchantment, Integer> secondEnchantments = new HashMap<>();

        // Stores the first item's enchantments.
        // Works for both enchanted books and normal items.
        if(first.getItemMeta() instanceof EnchantmentStorageMeta storageMeta) {
            finalEnchantments.putAll(storageMeta.getStoredEnchants());
        }
        else {
            finalEnchantments.putAll(first.getEnchantments());
        }

        // Stores the second item's enchantments.
        // Works for both enchanted books and normal items.
        if(second.getItemMeta() instanceof EnchantmentStorageMeta storageMeta) {
            secondEnchantments.putAll(storageMeta.getStoredEnchants());
        }
        else {
            secondEnchantments.putAll(second.getEnchantments());
        }
        for(Enchantment enchantment : secondEnchantments.keySet()) {
            // Adds the enchantment if there is no duplicate.
            if(!finalEnchantments.containsKey(enchantment)) {
                finalEnchantments.put(enchantment, secondEnchantments.get(enchantment));
                continue;
            }

            // If there is a duplicate, skips if the original has a higher level already.
            if(finalEnchantments.get(enchantment) > secondEnchantments.get(enchantment)) {
               continue;
            }

            // If the two enchantments are the same level, combine them and increase the level by 1.
            if(finalEnchantments.get(enchantment).equals(secondEnchantments.get(enchantment))) {
                finalEnchantments.put(enchantment, finalEnchantments.get(enchantment) + 1);
            }

            // Replaces the original with the higher level duplicate.
            finalEnchantments.put(enchantment, secondEnchantments.get(enchantment));
        }

        return finalEnchantments;
    }
}