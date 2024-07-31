package com.github.firewolf8385.customitemapi.items.attributes.attributes;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ReachAttribute extends ItemAttribute {
    private final Plugin plugin;

    public ReachAttribute(@NotNull final Plugin plugin) {
        super("reach", "Reach");
        this.plugin = plugin;
    }

    @Override
    public void addedToItem(CustomItem customItem, ItemBuilder item, double value) {
        Attribute attribute1 = Attribute.PLAYER_ENTITY_INTERACTION_RANGE;
        Attribute attribute2 = Attribute.PLAYER_BLOCK_BREAK_SPEED;

        switch (customItem.getType()) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> {
                final AttributeModifier modifier1 = new AttributeModifier(new NamespacedKey(plugin, "player.entity_interaction_range"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
                final AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(plugin, "player.block_interaction_range"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
                item.addAttributeModifier(attribute1, modifier1);
                item.addAttributeModifier(attribute2, modifier2);
            }

            default -> {
                AttributeModifier modifier1 = new AttributeModifier(new NamespacedKey(plugin, "player.entity_interaction_range"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
                AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(plugin, "player.block_interaction_range"), value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
                item.addAttributeModifier(attribute1, modifier1);
                item.addAttributeModifier(attribute2, modifier2);
            }
        }
    }
}