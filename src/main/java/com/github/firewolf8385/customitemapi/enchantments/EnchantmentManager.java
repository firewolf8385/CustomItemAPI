package com.github.firewolf8385.customitemapi.enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentManager {
    private final List<Enchantment> customEnchantments =  new ArrayList<>();

    public Enchantment getEnchantment(String id) {
        for(Enchantment enchantment : customEnchantments) {
            if(((CustomEnchantment) enchantment).getId().equals(id)) {
                return enchantment;
            }
        }

        return null;
    }

    public List<Enchantment> getEnchantments() {
        return customEnchantments;
    }

    /**
     * Registers a custom enchantment with the server.
     * @param enchantment Custom Enchantment to register.
     */
    public void registerEnchantment(Enchantment enchantment) {
        customEnchantments.add(enchantment);

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if an enchantment is a custom enchantment.
     * Only works for enchantments done through the api.
     * @param enchantment Enchantment to check.
     * @return Whether it is a custom enchantment.
     */
    public boolean isCustomEnchantment(Enchantment enchantment) {
        if(customEnchantments.contains(enchantment)) {
            return true;
        }

        return false;
    }

    /**
     * Converts a custom enchantment to its name.
     * @param enchantment Enchantment to get the name of.
     * @return Name of the enchantment.
     */
    public String enchantmentToString(Enchantment enchantment) {

        // If it's a custom enchantment, use the name set by the plugin.
        if(isCustomEnchantment(enchantment)) {
            return ((CustomEnchantment) enchantment).getName();
        }

        // If it's not a custom enchantment, use the vanilla name.
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

    public String integerToRomanNumeral(int input) {
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