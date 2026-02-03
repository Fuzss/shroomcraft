package fuzs.shroomcraft.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.family.BlockSetVariant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModModelProvider extends AbstractModelProvider {
    public static final TextureSlot GLOW_LICHEN_TEXTURE_SLOT = TextureSlot.create("glow_lichen");
    public static final ModelTemplate GLOW_LICHEN_MODEL_TEMPLATE = ModelTemplates.create("glow_lichen",
            TextureSlot.PARTICLE,
            GLOW_LICHEN_TEXTURE_SLOT);
    public static final TexturedModel.Provider GLOW_LICHEN_PROVIDER = TexturedModel.createDefault(ModModelProvider::glowLichen,
            GLOW_LICHEN_MODEL_TEMPLATE);

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    public static TextureMapping glowLichen(Block block) {
        return new TextureMapping().put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block))
                .put(GLOW_LICHEN_TEXTURE_SLOT, TextureMapping.getBlockTexture(block));
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> blockModelGenerators.family(blockFamily.getBaseBlock())
                        .generateFor(blockFamily));
        this.createHangingSign(ModBlocks.STRIPPED_MUSHROOM_STEM.value(),
                ModBlockFamilies.SHROOMWOOD_FAMILY,
                blockModelGenerators);
        this.createHangingSign(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(),
                ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY,
                blockModelGenerators);
        this.createHangingSign(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(),
                ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY,
                blockModelGenerators);
        this.createHangingSign(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(),
                ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY,
                blockModelGenerators);
        blockModelGenerators.createShelf(ModBlockFamilies.SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF).value(),
                ModBlocks.STRIPPED_MUSHROOM_STEM.value());
        blockModelGenerators.createShelf(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF)
                .value(), ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value());
        blockModelGenerators.createShelf(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF)
                .value(), ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        blockModelGenerators.createShelf(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getBlock(BlockSetVariant.SHELF)
                .value(), ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.BLUE_MUSHROOM.value(),
                ModBlocks.POTTED_BLUE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.ORANGE_MUSHROOM.value(),
                ModBlocks.POTTED_ORANGE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.PURPLE_MUSHROOM.value(),
                ModBlocks.POTTED_PURPLE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_BLOCK.value());
        blockModelGenerators.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_BLOCK.value());
        blockModelGenerators.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_BLOCK.value());
        blockModelGenerators.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_STEM.value());
        blockModelGenerators.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_STEM.value());
        blockModelGenerators.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_STEM.value());
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
        blockModelGenerators.createMultiface(ModBlocks.MYCELIAL_GROWTH.value());
        GLOW_LICHEN_PROVIDER.create(ModBlocks.MYCELIAL_GROWTH.value(), blockModelGenerators.modelOutput);
        blockModelGenerators.createNetherRoots(ModBlocks.MUSHROOM_SPROUTS.value(),
                ModBlocks.POTTED_MUSHROOM_SPROUTS.value());
        blockModelGenerators.createNetherRoots(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(),
                ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value());
        blockModelGenerators.createNetherRoots(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(),
                ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value());
        blockModelGenerators.createNetherRoots(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(),
                ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value());
        blockModelGenerators.createCrossBlock(ModBlocks.TINY_BROWN_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        blockModelGenerators.createCrossBlock(ModBlocks.TINY_RED_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        blockModelGenerators.createCrossBlock(ModBlocks.TINY_BLUE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        blockModelGenerators.createCrossBlock(ModBlocks.TINY_ORANGE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        blockModelGenerators.createCrossBlock(ModBlocks.TINY_PURPLE_MUSHROOM.value(),
                BlockModelGenerators.PlantType.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
    }

    public final void createHangingSign(Block particleBlock, BlockSetFamily registrar, BlockModelGenerators blockModelGenerators) {
        if (registrar.getBlock(BlockSetVariant.HANGING_SIGN) != null
                && registrar.getBlock(BlockSetVariant.WALL_HANGING_SIGN) != null) {
            blockModelGenerators.createHangingSign(particleBlock,
                    registrar.getBlock(BlockSetVariant.HANGING_SIGN).value(),
                    registrar.getBlock(BlockSetVariant.WALL_HANGING_SIGN).value());
        }
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModBlockFamilies.SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT).value(),
                ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getItem(BlockSetVariant.CHEST_BOAT)
                .value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SHROOMFIN.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COOKED_SHROOMFIN.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SHROOMFIN_BUCKET.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SHROOMFIN_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CLUCKSHROOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BLUE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ORANGE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPLE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
    }
}
