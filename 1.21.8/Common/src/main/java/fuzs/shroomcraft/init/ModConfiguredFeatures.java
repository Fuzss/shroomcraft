package fuzs.shroomcraft.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.MultifaceSpreadeableBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_blue_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_orange_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_purple_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "mushroom_island_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_blue_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_orange_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_purple_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIAL_GROWTH_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "mycelial_growth");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_MUSHROOM_SPROUTS_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_mushroom_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_blue_mushroom_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ORANGE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_orange_mushroom_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PURPLE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "patch_purple_mushroom_sprouts");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE,
                ModRegistry.HUGE_BLUE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context, HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE,
                ModRegistry.HUGE_ORANGE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context, HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE,
                ModRegistry.HUGE_PURPLE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        FeatureUtils.register(context, MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE,
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(
                                configuredFeatureLookup.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(
                                HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(
                                HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(
                                HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE)))));
        FeatureUtils.register(context, PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM.value()))));
        FeatureUtils.register(context, PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM.value()))));
        FeatureUtils.register(context, PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM.value()))));
        FeatureUtils.register(context, MYCELIAL_GROWTH_CONFIGURED_FEATURE,
                Feature.MULTIFACE_GROWTH,
                new MultifaceGrowthConfiguration((MultifaceSpreadeableBlock) ModBlocks.MYCELIAL_GROWTH.value(),
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
        FeatureUtils.register(context, PATCH_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context, PATCH_BLUE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context, PATCH_ORANGE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context, PATCH_PURPLE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value()))));
    }
}
