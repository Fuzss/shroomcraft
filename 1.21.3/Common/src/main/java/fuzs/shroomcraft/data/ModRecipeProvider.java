package fuzs.shroomcraft.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

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
        ShapelessRecipeBuilder.shapeless(this.items(), RecipeCategory.FOOD, Items.MUSHROOM_STEW)
                .requires(Blocks.BROWN_MUSHROOM)
                .requires(Blocks.RED_MUSHROOM)
                .requires(Items.BOWL)
                .unlockedBy("has_mushroom_stew", this.has(Items.MUSHROOM_STEW))
                .unlockedBy("has_bowl", this.has(Items.BOWL))
                .unlockedBy("has_brown_mushroom", this.has(Blocks.BROWN_MUSHROOM))
                .unlockedBy("has_red_mushroom", this.has(Blocks.RED_MUSHROOM))
                .save(recipeOutput);
    }
}
