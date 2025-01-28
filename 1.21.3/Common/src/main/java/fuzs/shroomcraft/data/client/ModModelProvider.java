package fuzs.shroomcraft.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelTemplateHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModItems;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
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
    public void addBlockModels(BlockModelGenerators builder) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> builder.family(blockFamily.getBaseBlock()).generateFor(blockFamily));
        this.createHangingSign(builder, ModBlocks.STRIPPED_MUSHROOM_STEM.value(), ModBlockFamilies.SHROOMWOOD_FAMILY);
        this.createHangingSign(builder,
                ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(),
                ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY);
        this.createHangingSign(builder,
                ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(),
                ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY);
        this.createHangingSign(builder,
                ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(),
                ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY);
        builder.createPlant(ModBlocks.BLUE_MUSHROOM.value(),
                ModBlocks.POTTED_BLUE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.ORANGE_MUSHROOM.value(),
                ModBlocks.POTTED_ORANGE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.PURPLE_MUSHROOM.value(),
                ModBlocks.POTTED_PURPLE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_STEM.value());
        builder.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_STEM.value());
        builder.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_STEM.value());
        builder.woodProvider(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
        builder.createMultiface(ModBlocks.MYCELIAL_GROWTH.value());
        GLOW_LICHEN_PROVIDER.create(ModBlocks.MYCELIAL_GROWTH.value(), builder.modelOutput);
        builder.createCrossBlock(ModBlocks.MUSHROOM_SPROUTS.value(), BlockModelGenerators.TintState.NOT_TINTED);
        builder.createSimpleFlatItemModel(ModBlocks.MUSHROOM_SPROUTS.value());
        builder.createCrossBlock(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), BlockModelGenerators.TintState.NOT_TINTED);
        builder.createSimpleFlatItemModel(ModBlocks.BLUE_MUSHROOM_SPROUTS.value());
        builder.createCrossBlock(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), BlockModelGenerators.TintState.NOT_TINTED);
        builder.createSimpleFlatItemModel(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value());
        builder.createCrossBlock(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), BlockModelGenerators.TintState.NOT_TINTED);
        builder.createSimpleFlatItemModel(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value());
        builder.createCrossBlock(ModBlocks.TINY_BROWN_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        builder.createCrossBlock(ModBlocks.TINY_RED_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        builder.createCrossBlock(ModBlocks.TINY_BLUE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        builder.createCrossBlock(ModBlocks.TINY_ORANGE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
        builder.createCrossBlock(ModBlocks.TINY_PURPLE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED,
                BlockStateProperties.AGE_1,
                0,
                1);
    }

    public void createHangingSign(BlockModelGenerators builder, Block particleBlock, BlockFamilyRegistrar registrar) {
        if (registrar.hangingSignBlock() != null && registrar.wallHangingSignBlock() != null) {
            builder.createHangingSign(particleBlock,
                    registrar.hangingSignBlock().value(),
                    registrar.wallHangingSignBlock().value());
        }
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        builder.generateFlatItem(ModBlockFamilies.SHROOMWOOD_FAMILY.boatItem().value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.SHROOMWOOD_FAMILY.chestBoatItem().value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.boatItem().value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.chestBoatItem().value(),
                ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.boatItem().value(),
                ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.chestBoatItem().value(),
                ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.boatItem().value(),
                ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.chestBoatItem().value(),
                ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.SHROOMFIN.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.COOKED_SHROOMFIN.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.SHROOMFIN_BUCKET.value(), ModelTemplates.FLAT_ITEM);
        ModelTemplateHelper.generateFlatItem(ModItems.SHROOMFIN_SPAWN_EGG.value(), SPAWN_EGG, builder.output);
        builder.generateFlatItem(ModItems.CLUCKSHROOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.BLUE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.ORANGE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.PURPLE_SHROOMBOMB.value(), ModelTemplates.FLAT_ITEM);
    }
}
