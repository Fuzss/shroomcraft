package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MUSHROOM_ISLAND_VEGETATION = register("mushroom_island_vegetation");
    public static final ResourceKey<PlacedFeature> BLUE_MUSHROOM_NORMAL = register("blue_mushroom_normal");
    public static final ResourceKey<PlacedFeature> ORANGE_MUSHROOM_NORMAL = register("orange_mushroom_normal");
    public static final ResourceKey<PlacedFeature> PURPLE_MUSHROOM_NORMAL = register("purple_mushroom_normal");
    public static final ResourceKey<PlacedFeature> BLUE_MUSHROOM_MUSHROOM_FIELDS = register(
            "blue_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> ORANGE_MUSHROOM_MUSHROOM_FIELDS = register(
            "orange_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> PURPLE_MUSHROOM_MUSHROOM_FIELDS = register(
            "purple_mushroom_mushroom_fields");
    public static final ResourceKey<PlacedFeature> MYCELIAL_GROWTH = register("mycelial_growth");
    public static final ResourceKey<PlacedFeature> PATCH_MUSHROOM_SPROUTS = register("patch_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_BLUE_MUSHROOM_SPROUTS = register("patch_blue_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_MUSHROOM_SPROUTS = register(
            "patch_orange_mushroom_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_PURPLE_MUSHROOM_SPROUTS = register(
            "patch_purple_mushroom_sprouts");

    private static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Shroomcraft.id(name));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> featureLookup = context.lookup(Registries.FEATURE);
        PlacementUtils.register(context,
                MUSHROOM_ISLAND_VEGETATION,
                featureLookup.getOrThrow(ModFeatures.MUSHROOM_ISLAND_VEGETATION),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
        PlacementUtils.register(context,
                BLUE_MUSHROOM_NORMAL,
                featureLookup.getOrThrow(ModFeatures.BLUE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                ORANGE_MUSHROOM_NORMAL,
                featureLookup.getOrThrow(ModFeatures.ORANGE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                PURPLE_MUSHROOM_NORMAL,
                featureLookup.getOrThrow(ModFeatures.PURPLE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(384, null));
        PlacementUtils.register(context,
                BLUE_MUSHROOM_MUSHROOM_FIELDS,
                featureLookup.getOrThrow(ModFeatures.BLUE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                ORANGE_MUSHROOM_MUSHROOM_FIELDS,
                featureLookup.getOrThrow(ModFeatures.ORANGE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                PURPLE_MUSHROOM_MUSHROOM_FIELDS,
                featureLookup.getOrThrow(ModFeatures.PURPLE_MUSHROOM),
                VegetationPlacements.getMushroomPlacement(64, null));
        PlacementUtils.register(context,
                MYCELIAL_GROWTH,
                featureLookup.getOrThrow(ModFeatures.MYCELIAL_GROWTH),
                CountPlacement.of(UniformInt.of(208, 314)),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                InSquarePlacement.spread(),
                BiomeFilter.biome());
        PlacementUtils.register(context,
                PATCH_MUSHROOM_SPROUTS,
                featureLookup.getOrThrow(ModFeatures.MUSHROOM_SPROUTS),
                VegetationPlacements.getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                PATCH_BLUE_MUSHROOM_SPROUTS,
                featureLookup.getOrThrow(ModFeatures.BLUE_MUSHROOM_SPROUTS),
                VegetationPlacements.getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                PATCH_ORANGE_MUSHROOM_SPROUTS,
                featureLookup.getOrThrow(ModFeatures.ORANGE_MUSHROOM_SPROUTS),
                VegetationPlacements.getMushroomPlacement(16, null));
        PlacementUtils.register(context,
                PATCH_PURPLE_MUSHROOM_SPROUTS,
                featureLookup.getOrThrow(ModFeatures.PURPLE_MUSHROOM_SPROUTS),
                VegetationPlacements.getMushroomPlacement(16, null));
    }
}
