package com.github.firewolf8385.customitemapi.items.attributes.attributes;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

public class AttackSpeedAttribute extends ItemAttribute {
    private final Plugin plugin;

    public AttackSpeedAttribute(@NotNull final Plugin plugin) {
        super("attack_speed", "Attack Speed");
        this.plugin = plugin;
    }

    @Override
    public void addedToItem(CustomItem customItem, ItemBuilder item, double value) {
        Attribute attribute = Attribute.GENERIC_ATTACK_SPEED;

        switch (customItem.getType()) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> {
                final AttributeModifier modifier = new AttributeModifier(new NamespacedKey(plugin, "generic.attackSpeed"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
                item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
            }

            default -> {
                AttributeModifier modifier = new AttributeModifier(new NamespacedKey(plugin, "generic.attackSpeed"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
                item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
            }
        }
    }
}