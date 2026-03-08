package fuzs.shroomcraft.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), Shroomcraft.MOD_NAME);
        translationBuilder.add(ModBlocks.SHROOMWOOD_PLANKS.value(), "Shroomwood Planks");
        translationBuilder.add(ModBlocks.BLUE_SHROOMWOOD_PLANKS.value(), "Blue Shroomwood Planks");
        translationBuilder.add(ModBlocks.ORANGE_SHROOMWOOD_PLANKS.value(), "Orange Shroomwood Planks");
        translationBuilder.add(ModBlocks.PURPLE_SHROOMWOOD_PLANKS.value(), "Purple Shroomwood Planks");
        this.generateFor(translationBuilder, ModBlockFamilies.SHROOMWOOD_FAMILY, "Shroomwood");
        this.generateFor(translationBuilder, ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY, "Blue Shroomwood");
        this.generateFor(translationBuilder, ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY, "Orange Shroomwood");
        this.generateFor(translationBuilder, ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY, "Purple Shroomwood");
        translationBuilder.add(ModBlocks.BLUE_MUSHROOM.value(), "Blue Mushroom");
        translationBuilder.add(ModBlocks.ORANGE_MUSHROOM.value(), "Orange Mushroom");
        translationBuilder.add(ModBlocks.PURPLE_MUSHROOM.value(), "Purple Mushroom");
        translationBuilder.add(ModBlocks.POTTED_BLUE_MUSHROOM.value(), "Potted Blue Mushroom");
        translationBuilder.add(ModBlocks.POTTED_ORANGE_MUSHROOM.value(), "Potted Orange Mushroom");
        translationBuilder.add(ModBlocks.POTTED_PURPLE_MUSHROOM.value(), "Potted Purple Mushroom");
        translationBuilder.add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(), "Blue Mushroom Block");
        translationBuilder.add(ModBlocks.ORANGE_MUSHROOM_BLOCK.value(), "Orange Mushroom Block");
        translationBuilder.add(ModBlocks.PURPLE_MUSHROOM_BLOCK.value(), "Purple Mushroom Block");
        translationBuilder.add(ModBlocks.BLUE_MUSHROOM_STEM.value(), "Blue Mushroom Stem");
        translationBuilder.add(ModBlocks.ORANGE_MUSHROOM_STEM.value(), "Orange Mushroom Stem");
        translationBuilder.add(ModBlocks.PURPLE_MUSHROOM_STEM.value(), "Purple Mushroom Stem");
        translationBuilder.add(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), "Stripped Mushroom Stem");
        translationBuilder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), "Stripped Blue Mushroom Stem");
        translationBuilder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(), "Stripped Orange Mushroom Stem");
        translationBuilder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(), "Stripped Purple Mushroom Stem");
        translationBuilder.add(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value(), "Stripped Mushroom Hyphae");
        translationBuilder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value(), "Stripped Blue Mushroom Hyphae");
        translationBuilder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value(), "Stripped Orange Mushroom Hyphae");
        translationBuilder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value(), "Stripped Purple Mushroom Hyphae");
        translationBuilder.add(ModBlocks.MYCELIAL_GROWTH.value(), "Mycelial Growth");
        translationBuilder.add(ModBlocks.MUSHROOM_SPROUTS.value(), "Mushroom Sprouts");
        translationBuilder.add(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), "Blue Mushroom Sprouts");
        translationBuilder.add(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), "Orange Mushroom Sprouts");
        translationBuilder.add(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), "Purple Mushroom Sprouts");
        translationBuilder.add(ModBlocks.POTTED_MUSHROOM_SPROUTS.value(), "Potted Mushroom Sprouts");
        translationBuilder.add(ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value(), "Potted Blue Mushroom Sprouts");
        translationBuilder.add(ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value(), "Potted Orange Mushroom Sprouts");
        translationBuilder.add(ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value(), "Potted Purple Mushroom Sprouts");
        translationBuilder.add(ModBlocks.TINY_BROWN_MUSHROOM.value(), "Tiny Brown Mushroom");
        translationBuilder.add(ModBlocks.TINY_RED_MUSHROOM.value(), "Tiny Red Mushroom");
        translationBuilder.add(ModBlocks.TINY_BLUE_MUSHROOM.value(), "Tiny Blue Mushroom");
        translationBuilder.add(ModBlocks.TINY_ORANGE_MUSHROOM.value(), "Tiny Orange Mushroom");
        translationBuilder.add(ModBlocks.TINY_PURPLE_MUSHROOM.value(), "Tiny Purple Mushroom");
        translationBuilder.add(ModItems.BROWN_SHROOMSPORES.value(), "Brown Shroomspores");
        translationBuilder.add(ModItems.RED_SHROOMSPORES.value(), "Red Shroomspores");
        translationBuilder.add(ModItems.BLUE_SHROOMSPORES.value(), "Blue Shroomspores");
        translationBuilder.add(ModItems.ORANGE_SHROOMSPORES.value(), "Orange Shroomspores");
        translationBuilder.add(ModItems.PURPLE_SHROOMSPORES.value(), "Purple Shroomspores");
        translationBuilder.add(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(), "Mooshroom");
        translationBuilder.add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(), "Shroomfin");
        translationBuilder.add(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(), "Cluckshroom");
        translationBuilder.add(ModItems.SHROOMFIN.value(), "Shroomfin");
        translationBuilder.add(ModItems.COOKED_SHROOMFIN.value(), "Cooked Shroomfin");
        translationBuilder.add(ModItems.SHROOMFIN_BUCKET.value(), "Bucket of Shroomfin");
        translationBuilder.addSpawnEgg(ModItems.SHROOMFIN_SPAWN_EGG.value(), "Shroomfin");
        translationBuilder.addSpawnEgg(ModItems.CLUCKSHROOM_SPAWN_EGG.value(), "Cluckshroom");
        translationBuilder.add(ModItems.BLUE_SHROOMBOMB.value(), "Blue Shroombomb");
        translationBuilder.add(ModItems.ORANGE_SHROOMBOMB.value(), "Orange Shroombomb");
        translationBuilder.add(ModItems.PURPLE_SHROOMBOMB.value(), "Purple Shroombomb");
        translationBuilder.add(ModItems.BLUE_SHROOMBOMB.value(), "effect.empty", "Blue Shroombomb");
        translationBuilder.add(ModItems.ORANGE_SHROOMBOMB.value(), "effect.empty", "Orange Shroombomb");
        translationBuilder.add(ModItems.PURPLE_SHROOMBOMB.value(), "effect.empty", "Purple Shroombomb");
    }
}
