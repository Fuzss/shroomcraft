package fuzs.shroomcraft.common.data.recipes;

import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v3.recipes.TransformingRecipeOutput;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModItems;
import fuzs.shroomcraft.common.init.ModTags;
import fuzs.shroomcraft.common.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.Collections;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        this.generateFor(ModBlockFamilies.SHROOMWOOD);
        this.generateFor(ModBlockFamilies.ORANGE_SHROOMWOOD);
        this.generateFor(ModBlockFamilies.BLUE_SHROOMWOOD);
        this.generateFor(ModBlockFamilies.PURPLE_SHROOMWOOD);
        this.planksFromLog(ModItems.SHROOMWOOD_PLANKS.value(), ModTags.Items.SHROOMWOOD_LOGS, 4);
        this.planksFromLog(ModItems.BLUE_SHROOMWOOD_PLANKS.value(), ModTags.Items.BLUE_SHROOMWOOD_LOGS, 4);
        this.planksFromLog(ModItems.ORANGE_SHROOMWOOD_PLANKS.value(), ModTags.Items.ORANGE_SHROOMWOOD_LOGS, 4);
        this.planksFromLog(ModItems.PURPLE_SHROOMWOOD_PLANKS.value(), ModTags.Items.PURPLE_SHROOMWOOD_LOGS, 4);
        this.foodCooking(ModItems.COOKED_SHROOMFIN.value(), ModItems.SHROOMFIN.value());
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.FOOD, Items.MUSHROOM_STEW)
                .requires(ModTags.Items.MUSHROOMS)
                .requires(ModTags.Items.MUSHROOMS)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.MUSHROOM_STEW), this.has(Items.MUSHROOM_STEW))
                .unlockedBy(getHasName(Items.BOWL), this.has(Items.BOWL))
                .unlockedBy(getHasName(ModTags.Items.MUSHROOMS), this.has(ModTags.Items.MUSHROOMS))
                .save(TransformingRecipeOutput.transformed(this.output, (Recipe<?> recipe) -> {
                    return new DistinctShapelessRecipe((ShapelessRecipe) recipe);
                }));
        this.shroombomb(ModItems.BLUE_SHROOMBOMB.value(), ModItems.BLUE_SHROOMSPORES.value());
        this.shroombomb(ModItems.ORANGE_SHROOMBOMB.value(), ModItems.ORANGE_SHROOMSPORES.value());
        this.shroombomb(ModItems.PURPLE_SHROOMBOMB.value(), ModItems.PURPLE_SHROOMSPORES.value());
        this.shapeless(RecipeCategory.FOOD, ModItems.RED_SHROOMSPORES.value());
        this.oneToOneConversionRecipe(Items.DYE.brown(),
                ModItems.MUSHROOM_SPROUTS.value(),
                getItemName(Items.DYE.brown()));
        this.oneToOneConversionRecipe(Items.DYE.blue(),
                ModItems.BLUE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.DYE.blue()));
        this.oneToOneConversionRecipe(Items.DYE.orange(),
                ModItems.ORANGE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.DYE.orange()));
        this.oneToOneConversionRecipe(Items.DYE.purple(),
                ModItems.PURPLE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.DYE.purple()));
        this.oneToOneConversionRecipe(ModItems.BROWN_SHROOMSPORES.value(), Items.BROWN_MUSHROOM, null, 2);
        this.oneToOneConversionRecipe(ModItems.RED_SHROOMSPORES.value(), Items.RED_MUSHROOM, null, 2);
        this.oneToOneConversionRecipe(ModItems.BLUE_SHROOMSPORES.value(), ModItems.BLUE_MUSHROOM.value(), null, 2);
        this.oneToOneConversionRecipe(ModItems.ORANGE_SHROOMSPORES.value(), ModItems.ORANGE_MUSHROOM.value(), null, 2);
        this.oneToOneConversionRecipe(ModItems.PURPLE_SHROOMSPORES.value(), ModItems.PURPLE_MUSHROOM.value(), null, 2);
    }

    @Override
    public final void generateFor(BlockSetFamily blockSetFamily) {
        this.generateFor(blockSetFamily, createVariantWoodProviders(blockSetFamily), Collections.emptyMap());
    }

    public final void shroombomb(ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, result)
                .define('#', Items.PAPER)
                .define('X', Items.GUNPOWDER)
                .define('@', ingredient)
                .pattern(" X ")
                .pattern("#@#")
                .pattern(" # ")
                .unlockedBy(getHasName(ingredient), this.has(ingredient))
                .save(this.output);
    }
}
