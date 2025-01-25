package fuzs.shroomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Holder.Reference<Block> BLUE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("blue_mushroom",
            (properties) -> new MushroomBlock(null, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 2));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("orange_mushroom",
            (properties) -> new MushroomBlock(null, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 3));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("purple_mushroom",
            (properties) -> new MushroomBlock(null, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 4));
    public static final Holder.Reference<Block> BLUE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "blue_mushroom_block",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "orange_mushroom_block",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "purple_mushroom_block",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)
                    .mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> BLUE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "blue_mushroom_stem",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.COLOR_BLUE));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "orange_mushroom_stem",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.COLOR_ORANGE));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "purple_mushroom_stem",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.COLOR_PURPLE));
    public static final Holder.Reference<Block> STRIPPED_BLUE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_blue_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> STRIPPED_ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_orange_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> STRIPPED_PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_purple_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)
                    .mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> BLUE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "blue_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "orange_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "purple_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> BLUE_SHROOMWOOD_STAIRS = ModRegistry.REGISTRIES.registerBlock(
            "blue_shroomwood_stairs",
            (BlockBehaviour.Properties properties) -> new StairBlock(BLUE_SHROOMWOOD_PLANKS.value().defaultBlockState(),
                    properties),
            () -> BlockBehaviour.Properties.ofLegacyCopy(BLUE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_SHROOMWOOD_STAIRS = ModRegistry.REGISTRIES.registerBlock(
            "orange_shroomwood_stairs",
            (BlockBehaviour.Properties properties) -> new StairBlock(BLUE_SHROOMWOOD_PLANKS.value().defaultBlockState(),
                    properties),
            () -> BlockBehaviour.Properties.ofLegacyCopy(ORANGE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_SHROOMWOOD_STAIRS = ModRegistry.REGISTRIES.registerBlock(
            "purple_shroomwood_stairs",
            (BlockBehaviour.Properties properties) -> new StairBlock(BLUE_SHROOMWOOD_PLANKS.value().defaultBlockState(),
                    properties),
            () -> BlockBehaviour.Properties.ofLegacyCopy(PURPLE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> BLUE_SHROOMWOOD_SLAB = ModRegistry.REGISTRIES.registerBlock(
            "blue_shroomwood_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(BLUE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_SHROOMWOOD_SLAB = ModRegistry.REGISTRIES.registerBlock(
            "orange_shroomwood_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(ORANGE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_SHROOMWOOD_SLAB = ModRegistry.REGISTRIES.registerBlock(
            "purple_shroomwood_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(PURPLE_SHROOMWOOD_PLANKS.value())
                    .mapColor(MapColor.TERRACOTTA_PURPLE));

    public static void bootstrap() {
        // NO-OP
    }
}
