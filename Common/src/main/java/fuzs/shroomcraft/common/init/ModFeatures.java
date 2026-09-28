package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugeBlueMushroomFeature;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugeOrangeMushroomFeature;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugePurpleMushroomFeature;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.MultifaceSpreadeableBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModFeatures {
    public static final ResourceKey<Feature> HUGE_BLUE_MUSHROOM = register("huge_blue_mushroom");
    public static final ResourceKey<Feature> HUGE_ORANGE_MUSHROOM = register("huge_orange_mushroom");
    public static final ResourceKey<Feature> HUGE_PURPLE_MUSHROOM = register("huge_purple_mushroom");
    public static final ResourceKey<Feature> MUSHROOM_ISLAND_VEGETATION = register("mushroom_island_vegetation");
    public static final ResourceKey<Feature> BLUE_MUSHROOM = register("blue_mushroom");
    public static final ResourceKey<Feature> ORANGE_MUSHROOM = register("orange_mushroom");
    public static final ResourceKey<Feature> PURPLE_MUSHROOM = register("purple_mushroom");
    public static final ResourceKey<Feature> MYCELIAL_GROWTH = register("mycelial_growth");
    public static final ResourceKey<Feature> MUSHROOM_SPROUTS = register("mushroom_sprouts");
    public static final ResourceKey<Feature> BLUE_MUSHROOM_SPROUTS = register("blue_mushroom_sprouts");
    public static final ResourceKey<Feature> ORANGE_MUSHROOM_SPROUTS = register("orange_mushroom_sprouts");
    public static final ResourceKey<Feature> PURPLE_MUSHROOM_SPROUTS = register("purple_mushroom_sprouts");

    private static ResourceKey<Feature> register(String name) {
        return ResourceKey.create(Registries.FEATURE, Shroomcraft.id(name));
    }

    public static void bootstrap(BootstrapContext<Feature> context) {
        context.register(HUGE_BLUE_MUSHROOM,
                new HugeBlueMushroomFeature(BlockStateProvider.holderOf(ModBlocks.BLUE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.holderOf(ModBlocks.BLUE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3,
                        BlockPredicate.matchesTag(ModTags.Blocks.HUGE_BLUE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)));
        context.register(HUGE_ORANGE_MUSHROOM,
                new HugeOrangeMushroomFeature(BlockStateProvider.holderOf(ModBlocks.ORANGE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.holderOf(ModBlocks.ORANGE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3,
                        BlockPredicate.matchesTag(ModTags.Blocks.HUGE_ORANGE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)));
        context.register(HUGE_PURPLE_MUSHROOM,
                new HugePurpleMushroomFeature(BlockStateProvider.holderOf(ModBlocks.PURPLE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.holderOf(ModBlocks.PURPLE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3,
                        BlockPredicate.matchesTag(ModTags.Blocks.HUGE_PURPLE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)));
        HolderGetter<Feature> featureLookup = context.lookup(Registries.FEATURE);
        context.register(MUSHROOM_ISLAND_VEGETATION,
                new SimpleRandomSelectorFeature(HolderSet.direct(PlacementUtils.inlinePlaced(featureLookup.getOrThrow(
                                TreeFeatures.HUGE_BROWN_MUSHROOM)),
                        PlacementUtils.inlinePlaced(featureLookup.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM)),
                        PlacementUtils.inlinePlaced(featureLookup.getOrThrow(HUGE_BLUE_MUSHROOM)),
                        PlacementUtils.inlinePlaced(featureLookup.getOrThrow(HUGE_ORANGE_MUSHROOM)),
                        PlacementUtils.inlinePlaced(featureLookup.getOrThrow(HUGE_PURPLE_MUSHROOM)))));
        context.register(BLUE_MUSHROOM, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.BLUE_MUSHROOM.value())));
        context.register(ORANGE_MUSHROOM,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.ORANGE_MUSHROOM.value())));
        context.register(PURPLE_MUSHROOM,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.PURPLE_MUSHROOM.value())));
        context.register(MYCELIAL_GROWTH,
                new MultifaceGrowthFeature((MultifaceSpreadeableBlock) ModBlocks.MYCELIAL_GROWTH.value(),
                        20,
                        true,
                        true,
                        true,
                        1.0F,
                        HolderSet.direct(Block::builtInRegistryHolder,
                                Blocks.STONE,
                                Blocks.ANDESITE,
                                Blocks.DIORITE,
                                Blocks.GRANITE,
                                Blocks.DRIPSTONE_BLOCK,
                                Blocks.CALCITE,
                                Blocks.TUFF,
                                Blocks.DEEPSLATE)));
        context.register(MUSHROOM_SPROUTS,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.MUSHROOM_SPROUTS.value())));
        context.register(BLUE_MUSHROOM_SPROUTS,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.BLUE_MUSHROOM_SPROUTS.value())));
        context.register(ORANGE_MUSHROOM_SPROUTS,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value())));
        context.register(PURPLE_MUSHROOM_SPROUTS,
                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value())));
    }
}
