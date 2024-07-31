/*
 * This file is part of CustomItemAPI, licensed under the MIT License.
 *
 *  Copyright (c) firewolf8385
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package com.github.firewolf8385.customitemapi.utils.items;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

public final class FireworkBuilder extends ItemBuilder {
    private int power;
    private final Collection<FireworkEffect> effects = new HashSet<>();

    /**
     * Creates a new Firework ItemStack with a stack size of 1.
     */
    public FireworkBuilder() {
        super(Material.FIREWORK_ROCKET);
    }

    /**
     * Creates a new Firework ItemStack with a set stack size.
     * @param amount Amount of fireworks in the stack.
     */
    public FireworkBuilder(final int amount) {
        super(Material.FIREWORK_ROCKET, amount);
    }

    /**
     * Creates a Firework from a given ItemStack.
     * @param itemStack ItemStack to build as a firework.
     */
    public FireworkBuilder(@NotNull final ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Adds an effect to the firework.
     * @param effect Effect to add to the firework.
     * @return Firework Builder.
     */
    public FireworkBuilder addEffect(final FireworkEffect effect) {
        effects.add(effect);
        return this;
    }

    /**
     * Sets the power of the firework.
     * @param power Power level of the firework.
     * @return Firework Builder.
     */
    public FireworkBuilder setPower(final int power) {
        this.power = power;
        return this;
    }

    /**
     * Converts the builder to an ItemStack.
     * @return ItemStack made by the builder.
     */
    @Override
    public ItemStack build() {
        final ItemStack item = super.build();
        final FireworkMeta meta = (FireworkMeta) item.getItemMeta();

        meta.setPower(power);
        meta.addEffects(effects);
        item.setItemMeta(meta);
        return item;
    }
}