package com.github.firewolf8385.customitemapi.items.attributes.attributes;

import com.github.firewolf8385.customitemapi.items.attributes.ItemAttribute;

public class CritChanceAttribute extends ItemAttribute {

    public CritChanceAttribute() {
        super("crit_chance", "Crit Chance");
    }

    @Override
    /**
     * Get the item lore that should be displayed when the attribute has a value.
     * @param value Value the attribute has on the item.
     * @return Lore that should be displayed.
     */
    public String toLore(double value) {
        String lore;

        if(Math.floor(value) == value) {
            if(value > 0) {
                lore = "&7" + getName() + ": &a+" + ((int) value) + "%";
            }
            else {
                lore = "&7" + getName() + ": &c-" + ((int) value) + "%";
            }
        }
        else {
            if(value > 0) {
                lore = "&7" + getName() + ": &a+" + value + "%";
            }
            else {
                lore = "&7" + getName() + ": &c-" + value + "%";
            }
        }

        return lore;
    }

}
