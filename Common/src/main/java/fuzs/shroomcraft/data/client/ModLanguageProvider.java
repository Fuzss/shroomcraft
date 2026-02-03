package fuzs.shroomcraft.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.init.family.BlockSetFamilyRegistrar;
import fuzs.shroomcraft.init.family.BlockSetVariant;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), Shroomcraft.MOD_NAME);
        builder.blockFamily("Shroomwood", "Shroomwood Planks")
                .generateFor(ModBlockFamilies.SHROOMWOOD_FAMILY.getWoodenVanillaFamily());
        builder.blockFamily("Blue Shroomwood", "Blue Shroomwood Planks")
                .generateFor(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getWoodenVanillaFamily());
        builder.blockFamily("Orange Shroomwood", "Orange Shroomwood Planks")
                .generateFor(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getWoodenVanillaFamily());
        builder.blockFamily("Purple Shroomwood", "Purple Shroomwood Planks")
                .generateFor(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getWoodenVanillaFamily());
        builder.addBlock(ModBlockFamilies.SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.HANGING_SIGN),
                "Shroomwood Hanging Sign");
        builder.addBlock(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.HANGING_SIGN),
                "Blue Shroomwood Hanging Sign");
        builder.addBlock(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.HANGING_SIGN),
                "Orange Shroomwood Hanging Sign");
        builder.addBlock(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.HANGING_SIGN),
                "Purple Shroomwood Hanging Sign");
        builder.addBlock(ModBlockFamilies.SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF), "Shroomwood Shelf");
        builder.addBlock(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF),
                "Blue Shroomwood Shelf");
        builder.addBlock(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF),
                "Orange Shroomwood Shelf");
        builder.addBlock(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF),
                "Purple Shroomwood Shelf");
        builder.add(ModBlocks.BLUE_MUSHROOM.value(), "Blue Mushroom");
        builder.add(ModBlocks.ORANGE_MUSHROOM.value(), "Orange Mushroom");
        builder.add(ModBlocks.PURPLE_MUSHROOM.value(), "Purple Mushroom");
        builder.add(ModBlocks.POTTED_BLUE_MUSHROOM.value(), "Potted Blue Mushroom");
        builder.add(ModBlocks.POTTED_ORANGE_MUSHROOM.value(), "Potted Orange Mushroom");
        builder.add(ModBlocks.POTTED_PURPLE_MUSHROOM.value(), "Potted Purple Mushroom");
        builder.add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(), "Blue Mushroom Block");
        builder.add(ModBlocks.ORANGE_MUSHROOM_BLOCK.value(), "Orange Mushroom Block");
        builder.add(ModBlocks.PURPLE_MUSHROOM_BLOCK.value(), "Purple Mushroom Block");
        builder.add(ModBlocks.BLUE_MUSHROOM_STEM.value(), "Blue Mushroom Stem");
        builder.add(ModBlocks.ORANGE_MUSHROOM_STEM.value(), "Orange Mushroom Stem");
        builder.add(ModBlocks.PURPLE_MUSHROOM_STEM.value(), "Purple Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), "Stripped Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), "Stripped Blue Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(), "Stripped Orange Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(), "Stripped Purple Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value(), "Stripped Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value(), "Stripped Blue Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value(), "Stripped Orange Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value(), "Stripped Purple Mushroom Hyphae");
        builder.add(ModBlocks.MYCELIAL_GROWTH.value(), "Mycelial Growth");
        builder.add(ModBlocks.MUSHROOM_SPROUTS.value(), "Mushroom Sprouts");
        builder.add(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), "Blue Mushroom Sprouts");
        builder.add(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), "Orange Mushroom Sprouts");
        builder.add(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), "Purple Mushroom Sprouts");
        builder.add(ModBlocks.POTTED_MUSHROOM_SPROUTS.value(), "Potted Mushroom Sprouts");
        builder.add(ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value(), "Potted Blue Mushroom Sprouts");
        builder.add(ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value(), "Potted Orange Mushroom Sprouts");
        builder.add(ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value(), "Potted Purple Mushroom Sprouts");
        builder.add(ModBlocks.TINY_BROWN_MUSHROOM.value(), "Tiny Brown Mushroom");
        builder.add(ModBlocks.TINY_RED_MUSHROOM.value(), "Tiny Red Mushroom");
        builder.add(ModBlocks.TINY_BLUE_MUSHROOM.value(), "Tiny Blue Mushroom");
        builder.add(ModBlocks.TINY_ORANGE_MUSHROOM.value(), "Tiny Orange Mushroom");
        builder.add(ModBlocks.TINY_PURPLE_MUSHROOM.value(), "Tiny Purple Mushroom");
        builder.add(ModItems.BROWN_SHROOMSPORES.value(), "Brown Shroomspores");
        builder.add(ModItems.RED_SHROOMSPORES.value(), "Red Shroomspores");
        builder.add(ModItems.BLUE_SHROOMSPORES.value(), "Blue Shroomspores");
        builder.add(ModItems.ORANGE_SHROOMSPORES.value(), "Orange Shroomspores");
        builder.add(ModItems.PURPLE_SHROOMSPORES.value(), "Purple Shroomspores");
        builder.add(ModBlockFamilies.SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT).value(), "Shroomwood Boat");
        builder.add(ModBlockFamilies.SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT).value(),
                "Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.BOAT).value(), "Shroomwood Boat");
        builder.add(ModBlockFamilies.SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.CHEST_BOAT).value(),
                "Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT).value(),
                "Blue Shroomwood Boat");
        builder.add(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT).value(),
                "Blue Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.BOAT).value(),
                "Blue Shroomwood Boat");
        builder.add(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.CHEST_BOAT).value(),
                "Blue Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT).value(),
                "Orange Shroomwood Boat");
        builder.add(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT).value(),
                "Orange Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.BOAT).value(),
                "Orange Shroomwood Boat");
        builder.add(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.CHEST_BOAT).value(),
                "Orange Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT).value(),
                "Purple Shroomwood Boat");
        builder.add(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT).value(),
                "Purple Shroomwood Chest Boat");
        builder.add(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.BOAT).value(),
                "Purple Shroomwood Boat");
        builder.add(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getEntityType(BlockSetVariant.CHEST_BOAT).value(),
                "Purple Shroomwood Chest Boat");
        builder.add(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(), "Mooshroom");
        builder.add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(), "Shroomfin");
        builder.add(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(), "Cluckshroom");
        builder.add(ModItems.SHROOMFIN.value(), "Shroomfin");
        builder.add(ModItems.COOKED_SHROOMFIN.value(), "Cooked Shroomfin");
        builder.add(ModItems.SHROOMFIN_BUCKET.value(), "Bucket of Shroomfin");
        builder.addSpawnEgg(ModItems.SHROOMFIN_SPAWN_EGG.value(), "Shroomfin");
        builder.addSpawnEgg(ModItems.CLUCKSHROOM_SPAWN_EGG.value(), "Cluckshroom");
        builder.add(ModItems.BLUE_SHROOMBOMB.value(), "Blue Shroombomb");
        builder.add(ModItems.ORANGE_SHROOMBOMB.value(), "Orange Shroombomb");
        builder.add(ModItems.PURPLE_SHROOMBOMB.value(), "Purple Shroombomb");
        builder.add(ModItems.BLUE_SHROOMBOMB.value(), "effect.empty", "Blue Shroombomb");
        builder.add(ModItems.ORANGE_SHROOMBOMB.value(), "effect.empty", "Orange Shroombomb");
        builder.add(ModItems.PURPLE_SHROOMBOMB.value(), "effect.empty", "Purple Shroombomb");
    }
}
