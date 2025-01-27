package fuzs.shroomcraft.init;

import fuzs.shroomcraft.world.level.block.TinyMushroomCropBlock;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Holder.Reference<Block> BLUE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("blue_mushroom",
            (properties) -> new MushroomBlock(ModFeatures.HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 2));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("orange_mushroom",
            (properties) -> new MushroomBlock(ModFeatures.HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 3));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock("purple_mushroom",
            (properties) -> new MushroomBlock(ModFeatures.HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .lightLevel((BlockState blockState) -> 4));
    public static final Holder.Reference<Block> POTTED_BLUE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "potted_blue_mushroom",
            properties -> new FlowerPotBlock(BLUE_MUSHROOM.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> POTTED_ORANGE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "potted_orange_mushroom",
            properties -> new FlowerPotBlock(ORANGE_MUSHROOM.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> POTTED_PURPLE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "potted_purple_mushroom",
            properties -> new FlowerPotBlock(PURPLE_MUSHROOM.value(), properties),
            Blocks::flowerPotProperties);
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
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_BLUE));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "orange_mushroom_stem",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_ORANGE));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "purple_mushroom_stem",
            HugeMushroomBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_PURPLE));
    public static final Holder.Reference<Block> STRIPPED_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Holder.Reference<Block> STRIPPED_BLUE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_blue_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> STRIPPED_ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_orange_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> STRIPPED_PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlock(
            "stripped_purple_mushroom_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> STRIPPED_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlock(
            "stripped_mushroom_hyphae",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Holder.Reference<Block> STRIPPED_BLUE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlock(
            "stripped_blue_mushroom_hyphae",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> STRIPPED_ORANGE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlock(
            "stripped_orange_mushroom_hyphae",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> STRIPPED_PURPLE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlock(
            "stripped_purple_mushroom_hyphae",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Holder.Reference<Block> BLUE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "blue_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "orange_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock(
            "purple_shroomwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> MYCELIAL_GROWTH = ModRegistry.REGISTRIES.registerBlock("mycelial_growth",
            GlowLichenBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLOW_LICHEN).lightLevel((BlockState blockState) -> 15));
    public static final Holder.Reference<Block> MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlock(
            "mushroom_sprouts",
            RootsBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Holder.Reference<Block> BLUE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlock(
            "blue_mushroom_sprouts",
            RootsBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Holder.Reference<Block> ORANGE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlock(
            "orange_mushroom_sprouts",
            RootsBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Holder.Reference<Block> PURPLE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlock(
            "purple_mushroom_sprouts",
            RootsBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Holder.Reference<Block> TINY_BROWN_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "tiny_brown_mushroom",
            properties -> new TinyMushroomCropBlock(properties, Blocks.BROWN_MUSHROOM.builtInRegistryHolder()),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP).mapColor(MapColor.COLOR_BROWN));
    public static final Holder.Reference<Block> TINY_RED_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "tiny_red_mushroom",
            properties -> new TinyMushroomCropBlock(properties, Blocks.RED_MUSHROOM.builtInRegistryHolder()),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP).mapColor(MapColor.COLOR_RED));
    public static final Holder.Reference<Block> TINY_BLUE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "tiny_blue_mushroom",
            properties -> new TinyMushroomCropBlock(properties, BLUE_MUSHROOM),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP).mapColor(MapColor.COLOR_BLUE));
    public static final Holder.Reference<Block> TINY_ORANGE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "tiny_orange_mushroom",
            properties -> new TinyMushroomCropBlock(properties, ORANGE_MUSHROOM),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP).mapColor(MapColor.COLOR_ORANGE));
    public static final Holder.Reference<Block> TINY_PURPLE_MUSHROOM = ModRegistry.REGISTRIES.registerBlock(
            "tiny_purple_mushroom",
            properties -> new TinyMushroomCropBlock(properties, PURPLE_MUSHROOM),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP).mapColor(MapColor.COLOR_PURPLE));

    public static void bootstrap() {
        // NO-OP
    }
}
