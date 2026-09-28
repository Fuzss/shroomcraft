package fuzs.shroomcraft.common.handler;

import fuzs.puzzleslib.common.api.biome.v2.BiomeLoadingPhase;
import fuzs.puzzleslib.common.api.biome.v2.BiomeTransformer;
import fuzs.puzzleslib.common.api.core.v1.context.BiomeTransformationsContext;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.init.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class BiomeModificationsHandler {

    private BiomeModificationsHandler() {
        // NO-OP
    }

    public static void onRegisterBiomeTransformations(BiomeTransformationsContext context) {
        context.registerBiomeTransformation(BiomeLoadingPhase.ADD,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.CRIMSON_FOREST);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    transformation.mobSpawns().addSpawn(ModEntityTypes.MOOSHROOM.value(), 8, 4, 8);
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.ADD,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.WARPED_FOREST);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    transformation.mobSpawns().addSpawn(ModEntityTypes.MOOSHROOM.value(), 8, 4, 8);
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.MODIFY,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.MUSHROOM_FIELDS);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    HolderGetter<PlacedFeature> placedFeatureLookup = lookupProvider.lookupOrThrow(Registries.PLACED_FEATURE);
                    transformation.generation()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(VegetationPlacements.MUSHROOM_ISLAND_VEGETATION));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.MUSHROOM_ISLAND_VEGETATION));
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.ADD,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.MUSHROOM_FIELDS);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    HolderGetter<PlacedFeature> placedFeatureLookup = lookupProvider.lookupOrThrow(Registries.PLACED_FEATURE);
                    transformation.mobSpawns().addSpawn(ModEntityTypes.MOOSHROOM.value(), 8, 4, 8);
                    transformation.mobSpawns().addSpawn(ModEntityTypes.SHROOMFIN.value(), 5, 1, 5);
                    transformation.mobSpawns().addSpawn(ModEntityTypes.CLUCKSHROOM.value(), 8, 4, 8);
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.MYCELIAL_GROWTH));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.BLUE_MUSHROOM_NORMAL));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.ORANGE_MUSHROOM_NORMAL));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PURPLE_MUSHROOM_NORMAL));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.BLUE_MUSHROOM_MUSHROOM_FIELDS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.ORANGE_MUSHROOM_MUSHROOM_FIELDS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PURPLE_MUSHROOM_MUSHROOM_FIELDS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PATCH_MUSHROOM_SPROUTS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PATCH_BLUE_MUSHROOM_SPROUTS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS));
                    transformation.generation()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    placedFeatureLookup.getOrThrow(ModPlacedFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS));
                });
    }
}
