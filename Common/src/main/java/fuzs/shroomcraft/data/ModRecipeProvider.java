package fuzs.shroomcraft.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.recipes.TransformingRecipeOutput;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.generateForBlockFamilies(ModBlockFamilies.getAllFamilies());
        this.planksFromLog(ModItems.SHROOMWOOD_PLANKS.value(), ModRegistry.SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.BLUE_SHROOMWOOD_PLANKS.value(), ModRegistry.BLUE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.ORANGE_SHROOMWOOD_PLANKS.value(), ModRegistry.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.PURPLE_SHROOMWOOD_PLANKS.value(), ModRegistry.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.woodFromLogs(ModItems.STRIPPED_MUSHROOM_HYPHAE.value(), ModItems.STRIPPED_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE.value(), ModItems.STRIPPED_BLUE_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value(),
                ModItems.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value(),
                ModItems.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            this.woodenBoat(registrar.boatItem().value(), registrar.getBaseBlock().value());
            this.chestBoat(registrar.chestBoatItem().value(), registrar.boatItem().value());
        });
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.SHROOMFIN.value()),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_SHROOMFIN.value(),
                        0.35F,
                        200)
                .unlockedBy(getHasName(ModItems.SHROOMFIN.value()), this.has(ModItems.SHROOMFIN.value()))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.items(), RecipeCategory.FOOD, Items.MUSHROOM_STEW)
                .requires(ModRegistry.MUSHROOMS_ITEM_TAG)
                .requires(ModRegistry.MUSHROOMS_ITEM_TAG)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.MUSHROOM_STEW), this.has(Items.MUSHROOM_STEW))
                .unlockedBy(getHasName(Items.BOWL), this.has(Items.BOWL))
                .unlockedBy(getHasName(ModRegistry.MUSHROOMS_ITEM_TAG), this.has(ModRegistry.MUSHROOMS_ITEM_TAG))
                .save(new TransformingRecipeOutput(this.output,
                        (Recipe<?> recipe) -> new DistinctShapelessRecipe((ShapelessRecipe) recipe)));
    }
}
