package com.github.firewolf8385.customitemapi.items;

import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;

/**
 * This class is a wrapper for AttributeModifiers, allowing us to easily create and track attributes.
 */
public class ItemAtrribute {
    private final Type type;
    private final Operation operation;
    private final int amount;
    private final Slot slot;

    public ItemAtrribute(Type type, Operation operation, int amount, Slot slot) {
        this.type = type;
        this.operation = operation;
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
     * Gets the operation of the attribute.
     * Returns either add or subtract.
     * @return Attribute Opperation.
     */
    public Operation getOperation() {
        return operation;
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
        ATTACK_SPEED("Attack Speed", Attribute.GENERIC_ATTACK_SPEED, "generic.attackSpeed"),
        DAMAGE("Damage", Attribute.GENERIC_ATTACK_DAMAGE, "generic.attackDamage"),
        DEFENSE("Defense", Attribute.GENERIC_ARMOR, "generic.armor"),
        HEALTH("Health", Attribute.GENERIC_MAX_HEALTH, "generic.maxHealth"),
        SPEED("Speed", Attribute.GENERIC_MOVEMENT_SPEED, "generic.movementSpeed"),
        TOUGHNESS("Toughness", Attribute.GENERIC_ARMOR_TOUGHNESS, "generic.armorToughness");

        private final String name;
        private final Attribute attribute;
        private final String attributeName;

        Type(String name, Attribute attribute, String attributeName) {
            this.name = name;
            this.attribute = attribute;
            this.attributeName = attributeName;
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
    }

    /**
     * Represents the
     */
    public enum Operation {
        ADD,
        SUBTRACT
    }
}