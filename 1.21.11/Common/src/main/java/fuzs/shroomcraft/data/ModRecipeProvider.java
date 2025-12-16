package fuzs.shroomcraft.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.recipes.TransformingRecipeOutput;
import fuzs.shroomcraft.init.*;
import fuzs.shroomcraft.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.generateForBlockFamilies(ModBlockFamilies.getAllFamilies());
        this.planksFromLog(ModItems.SHROOMWOOD_PLANKS.value(), ModTags.SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.BLUE_SHROOMWOOD_PLANKS.value(), ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.ORANGE_SHROOMWOOD_PLANKS.value(), ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.planksFromLog(ModItems.PURPLE_SHROOMWOOD_PLANKS.value(), ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG, 4);
        this.woodFromLogs(ModItems.STRIPPED_MUSHROOM_HYPHAE.value(), ModItems.STRIPPED_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE.value(), ModItems.STRIPPED_BLUE_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value(),
                ModItems.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        this.woodFromLogs(ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value(),
                ModItems.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        this.hangingSign(ModBlockFamilies.SHROOMWOOD_FAMILY.hangingSignItem().value(),
                ModBlocks.STRIPPED_MUSHROOM_STEM.value());
        this.hangingSign(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.hangingSignItem().value(),
                ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value());
        this.hangingSign(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.hangingSignItem().value(),
                ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        this.hangingSign(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.hangingSignItem().value(),
                ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        this.shelf(ModBlockFamilies.SHROOMWOOD_FAMILY.shelfItem().value(), ModBlocks.STRIPPED_MUSHROOM_STEM.value());
        this.shelf(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.shelfItem().value(),
                ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value());
        this.shelf(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.shelfItem().value(),
                ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        this.shelf(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.shelfItem().value(),
                ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            if (registrar.boatItem() != null) {
                this.woodenBoat(registrar.boatItem().value(), registrar.getBaseBlock().value());
                if (registrar.chestBoatItem() != null) {
                    this.chestBoat(registrar.chestBoatItem().value(), registrar.boatItem().value());
                }
            }
        });
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
        this.oneToOneConversionRecipe(Items.BROWN_DYE, ModItems.MUSHROOM_SPROUTS.value(), getItemName(Items.BROWN_DYE));
        this.oneToOneConversionRecipe(Items.BLUE_DYE,
                ModItems.BLUE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.BLUE_DYE));
        this.oneToOneConversionRecipe(Items.ORANGE_DYE,
                ModItems.ORANGE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.ORANGE_DYE));
        this.oneToOneConversionRecipe(Items.PURPLE_DYE,
                ModItems.PURPLE_MUSHROOM_SPROUTS.value(),
                getItemName(Items.PURPLE_DYE));
        this.oneToOneConversionRecipe(ModItems.BROWN_SHROOMSPORES.value(), Items.BROWN_MUSHROOM, null, 2);
        this.oneToOneConversionRecipe(ModItems.RED_SHROOMSPORES.value(), Items.RED_MUSHROOM, null, 2);
        this.oneToOneConversionRecipe(ModItems.BLUE_SHROOMSPORES.value(), ModItems.BLUE_MUSHROOM.value(), null, 2);
        this.oneToOneConversionRecipe(ModItems.ORANGE_SHROOMSPORES.value(), ModItems.ORANGE_MUSHROOM.value(), null, 2);
        this.oneToOneConversionRecipe(ModItems.PURPLE_SHROOMSPORES.value(), ModItems.PURPLE_MUSHROOM.value(), null, 2);
    }

    public void shroombomb(ItemLike result, ItemLike ingredient) {
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
