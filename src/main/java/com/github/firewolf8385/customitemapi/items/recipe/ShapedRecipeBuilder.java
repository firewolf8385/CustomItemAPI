package com.github.firewolf8385.customitemapi.items.recipe;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a recipe using either vanilla materials of CustomItems.
 */
public class ShapedRecipeBuilder {
    private final ItemStack result;

    private String shape1 = "ABC";
    private String shape2 = "DEF";
    private String shape3 = "GHI";

    private final Map<Character, String> materials = new HashMap<>();

    /**
     * Creates the Recipe Builder with a resulting Item Stack.
     * @param result ItemStack created through material.
     */
    public ShapedRecipeBuilder(ItemStack result) {
        this.result = result;
    }

    /**
     * Adds a material to the recipe using a string.
     * Can be either from Material enum or a CustomItem.
     * @param character Character representing the material.
     * @param material Material to add.
     * @return ShapedRecipeBuilder.
     */
    public ShapedRecipeBuilder addMaterial(char character, String material) {
        materials.put(character, material);
        return this;
    }

    /**
     * Adds a material to the recipe using the Material enum.
     * @param character Character representing the material.
     * @param material Material to add.
     * @return ShapedRecipeBuilder.
     */
    public ShapedRecipeBuilder addMaterial(char character, Material material) {
        materials.put(character, material.toString());
        return this;
    }

    /**
     * Adds a material to the recipe using the CustomItem.
     * @param character Character represeting the material.
     * @param customItem Material to add.
     * @return ShapedRecipeBuilder.
     */
    public ShapedRecipeBuilder addMaterial(char character, CustomItem customItem) {
        materials.put(character, customItem.getID());
        return this;
    }

    /**
     * Builds the recipe and registers it with Bukkit.
     * @param plugin Plugin creating the recipe.
     * @param id id of the recipe.
     */
    public void build(Plugin plugin, String id) {
        NamespacedKey key = new NamespacedKey(plugin, id);
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(shape1, shape2, shape3);

        for(Character character : materials.keySet()) {
            String material = materials.get(character);

            if(CustomItemAPIPlugin.getItem(material) != null) {
                recipe.setIngredient(character, CustomItemAPIPlugin.getItem(material).toItemStack());
            }
            else {
                recipe.setIngredient(character, Material.valueOf(material));
            }
        }

        Bukkit.addRecipe(recipe);
    }

    /**
     * Set the shape of the recipe.
     * @param shape1 First row of the crafting table.
     * @param shape2 Second row of the crafting table.
     * @param shape3 Third row of the crafting table.
     * @return ShapedRecipeBuilder.
     */
    public ShapedRecipeBuilder setShape(String shape1, String shape2, String shape3) {
        this.shape1 = shape1;
        this.shape2 = shape2;
        this.shape3 = shape3;
        return this;
    }
}
