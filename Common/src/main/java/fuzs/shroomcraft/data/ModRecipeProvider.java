package fuzs.shroomcraft.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.MUSHROOM_STEW)
                .requires(Blocks.BROWN_MUSHROOM)
                .requires(Blocks.RED_MUSHROOM)
                .requires(Items.BOWL)
                .unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_brown_mushroom", has(Blocks.BROWN_MUSHROOM))
                .unlockedBy("has_red_mushroom", has(Blocks.RED_MUSHROOM))
                .save(recipeOutput);
    }
}
