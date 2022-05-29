package com.github.firewolf8385.customitemapi.items;

import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

/**
 * This class is a wrapper for AttributeModifiers, allowing us to easily create and track attributes.
 */
public class ItemAtrribute {
    private final Type type;
    private final int amount;
    private final Slot slot;

    public ItemAtrribute(Type type, int amount, Slot slot) {
        this.type = type;
        this.amount = amount;
        this.slot = slot;
    }

    /**
     * Get the amount added or removed from the attribute.
     * @return Amount changed.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets the Slot the attribute applies to.
     * @return Slot the attributes are active in.
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * Gets the type of ItemAttribute.
     * @return type of ItemAttribute.
     */
    public Type getType() {
        return type;
    }

    public AttributeModifier toAttributeModifier() {
        double amount = this.amount;

        if(type.getOperation() == AttributeModifier.Operation.ADD_SCALAR) {
            amount = amount / 100.0;
        }

        return new AttributeModifier(UUID.randomUUID(), type.getAttributeName(), amount, type.getOperation(), slot.getSlot());
    }

    public String toString() {
        if(amount > 0) {
            return ChatUtils.translate("&7" + type.getName() + ": &a+" + amount);
        }

        return ChatUtils.translate("&7" + type.getName() + ": &c-" + amount);
    }

    /**
     * Represents the various slots attributes could apply in.
     */
    public enum Slot {
        HELMET(EquipmentSlot.HEAD),
        CHESTPLATE(EquipmentSlot.CHEST),
        LEGGINGS(EquipmentSlot.LEGS),
        BOOTS(EquipmentSlot.FEET),
        MAIN_HAND(EquipmentSlot.HAND),
        OFF_HAND(EquipmentSlot.OFF_HAND);

        private final EquipmentSlot slot;

        Slot(EquipmentSlot slot) {
            this.slot = slot;
        }

        public EquipmentSlot getSlot() {
            return slot;
        }
    }

    public enum Type {
        ATTACK_SPEED("Attack Speed", Attribute.GENERIC_ATTACK_SPEED, "generic.attackSpeed", AttributeModifier.Operation.ADD_NUMBER),
        DAMAGE("Damage", Attribute.GENERIC_ATTACK_DAMAGE, "generic.attackDamage", AttributeModifier.Operation.ADD_NUMBER),
        DEFENSE("Defense", Attribute.GENERIC_ARMOR, "generic.armor", AttributeModifier.Operation.ADD_NUMBER),
        HEALTH("Health", Attribute.GENERIC_MAX_HEALTH, "generic.maxHealth", AttributeModifier.Operation.ADD_NUMBER),
        LUCK("Luck", Attribute.GENERIC_LUCK, "generic.luck", AttributeModifier.Operation.ADD_NUMBER),
        SPEED("Speed", Attribute.GENERIC_MOVEMENT_SPEED, "generic.movementSpeed", AttributeModifier.Operation.ADD_SCALAR),
        TOUGHNESS("Toughness", Attribute.GENERIC_ARMOR_TOUGHNESS, "generic.armorToughness", AttributeModifier.Operation.ADD_NUMBER);

        private final String name;
        private final Attribute attribute;
        private final String attributeName;
        private final AttributeModifier.Operation operation;

        Type(String name, Attribute attribute, String attributeName, AttributeModifier.Operation operation) {
            this.name = name;
            this.attribute = attribute;
            this.attributeName = attributeName;
            this.operation = operation;
        }

        public Attribute getAttribute() {
            return attribute;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public String getName() {
            return name;
        }

        public AttributeModifier.Operation getOperation() {
            return operation;
        }
    }
}