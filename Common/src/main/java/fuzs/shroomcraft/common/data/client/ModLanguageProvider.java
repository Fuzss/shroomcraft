package fuzs.shroomcraft.common.data.client;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.client.data.v3.language.TranslationBuilder;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.init.*;

import java.util.Map;
import java.util.function.UnaryOperator;

public class ModLanguageProvider extends AbstractLanguageProvider {
    public static final Map<BlockSetVariant, UnaryOperator<String>> VARIANT_BLOCK_NAMES = ImmutableMap.<BlockSetVariant, UnaryOperator<String>>builder()
            .putAll(AbstractLanguageProvider.VARIANT_BLOCK_NAMES)
            .put(BlockSetVariant.LOG, (String baseName) -> baseName + " Stem")
            .put(BlockSetVariant.WOOD, (String baseName) -> baseName + " Hyphae")
            .put(BlockSetVariant.STRIPPED_LOG, (String baseName) -> "Stripped " + baseName + " Stem")
            .put(BlockSetVariant.STRIPPED_WOOD, (String baseName) -> "Stripped " + baseName + " Hyphae")
            .buildKeepingLast();

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), Shroomcraft.MOD_NAME);
        this.add(ModBlocks.SHROOMWOOD_PLANKS.value(), "Shroomwood Planks");
        this.add(ModBlocks.BLUE_SHROOMWOOD_PLANKS.value(), "Blue Shroomwood Planks");
        this.add(ModBlocks.ORANGE_SHROOMWOOD_PLANKS.value(), "Orange Shroomwood Planks");
        this.add(ModBlocks.PURPLE_SHROOMWOOD_PLANKS.value(), "Purple Shroomwood Planks");
        this.generateFor(this, ModBlockFamilies.SHROOMWOOD, "Shroomwood");
        this.generateFor(this, ModBlockFamilies.BLUE_SHROOMWOOD, "Blue Shroomwood");
        this.generateFor(this, ModBlockFamilies.ORANGE_SHROOMWOOD, "Orange Shroomwood");
        this.generateFor(this, ModBlockFamilies.PURPLE_SHROOMWOOD, "Purple Shroomwood");
        this.add(ModBlocks.BLUE_MUSHROOM.value(), "Blue Mushroom");
        this.add(ModBlocks.ORANGE_MUSHROOM.value(), "Orange Mushroom");
        this.add(ModBlocks.PURPLE_MUSHROOM.value(), "Purple Mushroom");
        this.add(ModBlocks.POTTED_BLUE_MUSHROOM.value(), "Potted Blue Mushroom");
        this.add(ModBlocks.POTTED_ORANGE_MUSHROOM.value(), "Potted Orange Mushroom");
        this.add(ModBlocks.POTTED_PURPLE_MUSHROOM.value(), "Potted Purple Mushroom");
        this.add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(), "Blue Mushroom Block");
        this.add(ModBlocks.ORANGE_MUSHROOM_BLOCK.value(), "Orange Mushroom Block");
        this.add(ModBlocks.PURPLE_MUSHROOM_BLOCK.value(), "Purple Mushroom Block");
        this.add(ModBlocks.MYCELIAL_GROWTH.value(), "Mycelial Growth");
        this.add(ModBlocks.MUSHROOM_SPROUTS.value(), "Mushroom Sprouts");
        this.add(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), "Blue Mushroom Sprouts");
        this.add(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), "Orange Mushroom Sprouts");
        this.add(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), "Purple Mushroom Sprouts");
        this.add(ModBlocks.POTTED_MUSHROOM_SPROUTS.value(), "Potted Mushroom Sprouts");
        this.add(ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value(), "Potted Blue Mushroom Sprouts");
        this.add(ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value(), "Potted Orange Mushroom Sprouts");
        this.add(ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value(), "Potted Purple Mushroom Sprouts");
        this.add(ModBlocks.TINY_BROWN_MUSHROOM.value(), "Tiny Brown Mushroom");
        this.add(ModBlocks.TINY_RED_MUSHROOM.value(), "Tiny Red Mushroom");
        this.add(ModBlocks.TINY_BLUE_MUSHROOM.value(), "Tiny Blue Mushroom");
        this.add(ModBlocks.TINY_ORANGE_MUSHROOM.value(), "Tiny Orange Mushroom");
        this.add(ModBlocks.TINY_PURPLE_MUSHROOM.value(), "Tiny Purple Mushroom");
        this.add(ModItems.BROWN_SHROOMSPORES.value(), "Brown Shroomspores");
        this.add(ModItems.RED_SHROOMSPORES.value(), "Red Shroomspores");
        this.add(ModItems.BLUE_SHROOMSPORES.value(), "Blue Shroomspores");
        this.add(ModItems.ORANGE_SHROOMSPORES.value(), "Orange Shroomspores");
        this.add(ModItems.PURPLE_SHROOMSPORES.value(), "Purple Shroomspores");
        this.add(ModEntityTypes.MOOSHROOM.value(), "Mooshroom");
        this.add(ModEntityTypes.SHROOMFIN.value(), "Shroomfin");
        this.add(ModEntityTypes.CLUCKSHROOM.value(), "Cluckshroom");
        this.add(ModItems.SHROOMFIN.value(), "Shroomfin");
        this.add(ModItems.COOKED_SHROOMFIN.value(), "Cooked Shroomfin");
        this.add(ModItems.SHROOMFIN_BUCKET.value(), "Bucket of Shroomfin");
        this.addSpawnEgg(ModItems.SHROOMFIN_SPAWN_EGG.value(), "Shroomfin");
        this.addSpawnEgg(ModItems.CLUCKSHROOM_SPAWN_EGG.value(), "Cluckshroom");
        this.add(ModItems.BLUE_SHROOMBOMB.value(), "Blue Shroombomb");
        this.add(ModItems.ORANGE_SHROOMBOMB.value(), "Orange Shroombomb");
        this.add(ModItems.PURPLE_SHROOMBOMB.value(), "Purple Shroombomb");
        this.add(ModItems.BLUE_SHROOMBOMB.value(), "effect.empty", "Blue Shroombomb");
        this.add(ModItems.ORANGE_SHROOMBOMB.value(), "effect.empty", "Orange Shroombomb");
        this.add(ModItems.PURPLE_SHROOMBOMB.value(), "effect.empty", "Purple Shroombomb");
    }

    @Override
    public void generateFor(TranslationBuilder translationBuilder, BlockSetFamily blockSetFamily, String baseName) {
        this.generateFor(translationBuilder::add, blockSetFamily.getBlockVariants(), VARIANT_BLOCK_NAMES, baseName);
        this.generateFor(translationBuilder::add, blockSetFamily.getItemVariants(), VARIANT_ITEM_NAMES, baseName);
        this.generateFor(translationBuilder::add, blockSetFamily.getEntityVariants(), VARIANT_ENTITY_NAMES, baseName);
    }
}
