package fuzs.shroomcraft.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "mushroom_island_vegetation");
    public static final ResourceKey<PlacedFeature> BLUE_MUSHROOM_NORMAL_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "blue_mushroom_normal");
    public static final ResourceKey<PlacedFeature> ORANGE_MUSHROOM_NORMAL_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "orange_mushroom_normal");
    public static final ResourceKey<PlacedFeature> PURPLE_MUSHROOM_NORMAL_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "purple_mushroom_normal");
    public static final ResourceKey<PlacedFeature> BLUE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "blue_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> ORANGE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "orange_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> PURPLE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "purple_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> MYCELIAL_GROWTH_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "mycelial_growth");
    public static final ResourceKey<PlacedFeature> PATCH_MUSHROOM_SPROUTS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "patch_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_BLUE_MUSHROOM_SPROUTS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "patch_blue_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_MUSHROOM_SPROUTS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "patch_orange_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_PURPLE_MUSHROOM_SPROUTS_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "patch_purple_mushroom_sprouts");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
        PlacementUtils.register(context, BLUE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context, ORANGE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context, PURPLE_MUSHROOM_NORMAL_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(384, null));
        PlacementUtils.register(context, BLUE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_BLUE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context, ORANGE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_ORANGE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context, PURPLE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_PURPLE_MUSHROOM_CONFIGURED_FEATURE),
                getMushroomPlacement(64, null));
        PlacementUtils.register(context, MYCELIAL_GROWTH_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.MYCELIAL_GROWTH_CONFIGURED_FEATURE),
                CountPlacement.of(UniformInt.of(204, 250)),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                InSquarePlacement.spread(),
                BiomeFilter.biome());
        PlacementUtils.register(context, PATCH_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context, PATCH_BLUE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context, PATCH_ORANGE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
                getMushroomPlacement(16, null));
        PlacementUtils.register(context, PATCH_PURPLE_MUSHROOM_SPROUTS_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_CONFIGURED_FEATURE),
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
