package fuzs.shroomcraft.common.data;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.recipes.TransformingRecipeOutput;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModItems;
import fuzs.shroomcraft.common.init.ModTags;
import fuzs.shroomcraft.common.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    /**
     * TODO use from Puzzles Lib
     */
    @Deprecated
    public static Map<BlockSetVariant, FamilyRecipeProvider> createVariantWoodProviders(BlockSetFamily blockSetFamily) {
        return ImmutableMap.<BlockSetVariant, FamilyRecipeProvider>builder()
                .put(BlockSetVariant.WOOD,
                        (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                            recipeProvider.woodFromLogs(result, blockSetFamily.getItem(BlockSetVariant.LOG).value());
                        })
                .put(BlockSetVariant.STRIPPED_WOOD,
                        (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                            recipeProvider.woodFromLogs(result,
                                    blockSetFamily.getItem(BlockSetVariant.STRIPPED_LOG).value());
                        })
                .put(BlockSetVariant.SHELF,
                        (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                            recipeProvider.shelf(result, blockSetFamily.getItem(BlockSetVariant.STRIPPED_LOG).value());
                        })
                .put(BlockSetVariant.BOAT,
                        (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                            recipeProvider.woodenBoat(result, input);
                        })
                .put(BlockSetVariant.CHEST_BOAT,
                        (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                            recipeProvider.chestBoat(result, blockSetFamily.getItem(BlockSetVariant.BOAT).value());
                        })
                .build();
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.generateFor(ModBlockFamilies.SHROOMWOOD_FAMILY);
        this.generateFor(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY);
        this.generateFor(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY);
        this.generateFor(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY);
        this.planksFromLog(ModItems.SHROOMWOOD_PLANKS.value(), ModTags.SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.BLUE_SHROOMWOOD_PLANKS.value(), ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.ORANGE_SHROOMWOOD_PLANKS.value(), ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.PURPLE_SHROOMWOOD_PLANKS.value(), ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.foodCooking(ModItems.COOKED_SHROOMFIN.value(), ModItems.SHROOMFIN.value());
        ShapelessRecipeBuilder.shapeless(this.items(), RecipeCategory.FOOD, Items.MUSHROOM_STEW)
                .requires(ModTags.MUSHROOMS_ITEM_TAG)
                .requires(ModTags.MUSHROOMS_ITEM_TAG)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.MUSHROOM_STEW), this.has(Items.MUSHROOM_STEW))
                .unlockedBy(getHasName(Items.BOWL), this.has(Items.BOWL))
                .unlockedBy(getHasName(ModTags.MUSHROOMS_ITEM_TAG), this.has(ModTags.MUSHROOMS_ITEM_TAG))
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
        ShapedRecipeBuilder.shaped(this.items(), RecipeCategory.MISC, result)
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
