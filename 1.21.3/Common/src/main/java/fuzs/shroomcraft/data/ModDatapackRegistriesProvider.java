package fuzs.shroomcraft.data;

import com.google.common.collect.ImmutableList;
import fuzs.puzzleslib.api.data.v2.AbstractDatapackRegistriesProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModFeatures;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModDatapackRegistriesProvider extends AbstractDatapackRegistriesProvider {

    public ModDatapackRegistriesProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBootstrap(RegistryBoostrapConsumer consumer) {
        consumer.add(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
                ModDatapackRegistriesProvider::bootstrapCluckshroomVariants);
        consumer.add(Registries.CONFIGURED_FEATURE, ModDatapackRegistriesProvider::bootstrapConfiguredFeatures);
        consumer.add(Registries.PLACED_FEATURE, ModDatapackRegistriesProvider::bootstrapPlacedFeatures);
    }

    static void bootstrapCluckshroomVariants(BootstrapContext<MobBlockVariant> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        context.register(ModRegistry.RED_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.RED_CLUCKSHROOM_VARIANT,
                        Blocks.RED_MUSHROOM));
        context.register(ModRegistry.BROWN_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.BROWN_CLUCKSHROOM_VARIANT,
                        Blocks.BROWN_MUSHROOM));
        context.register(ModRegistry.CRIMSON_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.CRIMSON_CLUCKSHROOM_VARIANT,
                        Blocks.CRIMSON_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST)));
        context.register(ModRegistry.WARPED_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.WARPED_CLUCKSHROOM_VARIANT,
                        Blocks.WARPED_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.WARPED_FOREST)));
        context.register(ModRegistry.BLUE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.BLUE_CLUCKSHROOM_VARIANT,
                        ModBlocks.BLUE_MUSHROOM.value()));
        context.register(ModRegistry.ORANGE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.ORANGE_CLUCKSHROOM_VARIANT,
                        ModBlocks.ORANGE_MUSHROOM.value()));
        context.register(ModRegistry.PURPLE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.PURPLE_CLUCKSHROOM_VARIANT,
                        ModBlocks.PURPLE_MUSHROOM.value()));
    }

    static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context,
                ModFeatures.HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_BLUE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context,
                ModFeatures.HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_ORANGE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context,
                ModFeatures.HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_PURPLE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        FeatureUtils.register(context,
                ModFeatures.MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE,
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(
                                configuredFeatureLookup.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(ModFeatures.HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(ModFeatures.HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE)),
                        PlacementUtils.inlinePlaced(configuredFeatureLookup.getOrThrow(ModFeatures.HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE)))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM.value()))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM.value()))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM.value()))));
        FeatureUtils.register(context,
                ModFeatures.MYCELIAL_GROWTH_CONFIGURED_FEATURE,
                Feature.MULTIFACE_GROWTH,
                new MultifaceGrowthConfiguration((MultifaceBlock) ModBlocks.MYCELIAL_GROWTH.value(),
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
        FeatureUtils.register(context,
                ModFeatures.PATCH_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value()))));
        FeatureUtils.register(context,
                ModFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value()))));
    }

    /**
     * Copied from {@link net.minecraft.data.worldgen.features.VegetationFeatures#grassPatch(BlockStateProvider, int)}.
     */
    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }

    static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context,
                ModFeatures.MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
        PlacementUtils.register(context,
                ModFeatures.BLUE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                ModFeatures.ORANGE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                ModFeatures.PURPLE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                ModFeatures.BLUE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                ModFeatures.ORANGE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                ModFeatures.PURPLE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                ModFeatures.MYCELIAL_GROWTH_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.MYCELIAL_GROWTH_CONFIGURED_FEATURE),
                CountPlacement.of(UniformInt.of(204, 250)),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                InSquarePlacement.spread(),
                BiomeFilter.biome());
        PlacementUtils.register(context,
                ModFeatures.PATCH_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                ModFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                ModFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                ModFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
    }

    /**
     * Copied from
     * {@link net.minecraft.data.worldgen.placement.VegetationPlacements#getMushroomPlacement(int, PlacementModifier)}.
     */
    private static List<PlacementModifier> getMushroomPlacement(int rarity, @Nullable PlacementModifier placement) {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (placement != null) {
            builder.add(placement);
        }

        if (rarity != 0) {
            builder.add(RarityFilter.onAverageOnceEvery(rarity));
        }

        builder.add(InSquarePlacement.spread());
        builder.add(PlacementUtils.HEIGHTMAP);
        builder.add(BiomeFilter.biome());
        return builder.build();
    }
}
