package com.github.firewolf8385.customitemapi.utils.items;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentUtils {

    public static Enchantment getEnchantment(String id) {
        for(Enchantment enchantment : CustomItemAPI.getCustomEnchantments()) {
            if(((CustomEnchantment) enchantment).getId().equals(id)) {
                return enchantment;
            }
        }

        return null;
    }

    public static boolean hasLevel(Enchantment enchantment) {

        if(CustomItemAPI.getCustomEnchantments().contains(enchantment)) {
            return enchantment.getMaxLevel() != 1;
        }

        switch (enchantment.getName()) {
            default -> {
                return true;
            }
            case "ARROW_FIRE",
                    "ARROW_INFINITE",
                    "BINDING_CURSE",
                    "CHANNELING",
                    "MENDING",
                    "MULTISHOT",
                    "SILK_TOUCH",
                    "VANISHING_CURSE",
                    "WATER_WORKER" -> {
                return false;
            }
        }
    }

    public static String enchantmentToString(Enchantment enchantment) {
        if(CustomItemAPI.getCustomEnchantments().contains(enchantment)) {
            return ((CustomEnchantment) enchantment).getName();
        }

        return switch (enchantment.getName()) {
            case "ARROW_DAMAGE" -> "Power";
            case "ARROW_FIRE" -> "Flame";
            case "ARROW_INFINITE" -> "Infinity";
            case "ARROW_KNOCKBACK" -> "Punch";
            case "BINDING_CURSE" -> "&cCurse of Binding";
            case "CHANNELING" -> "Channeling";
            case "DAMAGE_ALL" -> "Sharpness";
            case "DAMAGE_ARTHROPODS" -> "Bane of Arthropods";
            case "DAMAGE_UNDEAD" -> "Smite";
            case "DEPTH_STRIDER" -> "Depth Strider";
            case "DIG_SPEED" -> "Efficiency";
            case "DURABILITY" -> "Unbreaking";
            case "FIRE_ASPECT" -> "Fire Aspect";
            case "FROST_WALKER" -> "Frost Walker";
            case "IMPALING" -> "Impaling";
            case "KNOCKBACK" -> "Knockback";
            case "LOOT_BONUS_BLOCKS" -> "Fortune";
            case "LOOT_BONUS_MOBS" -> "Looting";
            case "LOYALTY" -> "Loyalty";
            case "LUCK" -> "Luck of the Sea";
            case "LURE" -> "Lure";
            case "MENDING" -> "Mending";
            case "MULTISHOT" -> "Multishot";
            case "OXYGEN" -> "Respiration";
            case "PIERCING" -> "Piercing";
            case "PROTECTION_ENVIRONMENTAL" -> "Protection";
            case "PROTECTION_EXPLOSIONS" -> "Blast Protection";
            case "PROTECTION_FALL" -> "Feather Falling";
            case "PROTECTION_FIRE" -> "Fire Protection";
            case "PROTECTION_PROJECTILE" -> "Projectile Protection";
            case "QUICK_CHARGE" -> "Quick Charge";
            case "RIPTIDE" -> "Riptide";
            case "SILK_TOUCH" -> "Silk Touch";
            case "SOUL_SPEED" -> "Soul Speed";
            case "SWEEPING_EDGE" -> "Sweeping Edge";
            case "SWIFT_SNEAK" -> "Swift Sneak";
            case "THORNS" -> "Thorns";
            case "VANISHING_CURSE" -> "&cCurse of Vanishing";
            case "WATER_WORKER" -> "Aqua Affinity";
            default -> " ";
        };
    }

    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}
