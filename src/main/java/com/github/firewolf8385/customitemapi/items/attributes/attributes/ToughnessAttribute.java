package com.github.firewolf8385.customitemapi.items.attributes.attributes;

import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.UUID;

public class ToughnessAttribute extends ItemAttribute {

    public ToughnessAttribute() {
        super("toughness", "Toughness");
    }

    @Override
    public void addedToItem(CustomItem customItem, ItemBuilder item, double value) {
        Attribute attribute = Attribute.GENERIC_ARMOR_TOUGHNESS;

        switch (customItem.getType()) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> {
                Arrays.asList(EquipmentSlot.HAND, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET).forEach(slot -> {
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", value, AttributeModifier.Operation.ADD_NUMBER, slot);
                    item.addAttributeModifier(attribute, modifier);
                });
            }

            default -> {
                AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                item.addAttributeModifier(attribute, modifier);
            }
        }
    }

}
