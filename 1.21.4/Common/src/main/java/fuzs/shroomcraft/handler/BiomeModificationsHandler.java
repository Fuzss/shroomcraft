package fuzs.shroomcraft.handler;

import fuzs.puzzleslib.api.biome.v1.BiomeLoadingContext;
import fuzs.puzzleslib.api.biome.v1.BiomeLoadingPhase;
import fuzs.puzzleslib.api.biome.v1.BiomeModificationContext;
import fuzs.puzzleslib.api.core.v1.context.BiomeModificationsContext;
import fuzs.shroomcraft.init.ModFeatures;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class BiomeModificationsHandler {

    private BiomeModificationsHandler() {
        // NO-OP
    }

    public static void onRegisterBiomeModifications(BiomeModificationsContext context) {
        context.registerBiomeModification(BiomeLoadingPhase.ADDITIONS,
                (BiomeLoadingContext biomeLoadingContext) -> biomeLoadingContext.is(Biomes.CRIMSON_FOREST),
                (BiomeModificationContext biomeModificationContext) -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                });
        context.registerBiomeModification(BiomeLoadingPhase.ADDITIONS,
                (BiomeLoadingContext biomeLoadingContext) -> biomeLoadingContext.is(Biomes.WARPED_FOREST),
                (BiomeModificationContext biomeModificationContext) -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                });
        context.registerBiomeModification(BiomeLoadingPhase.MODIFICATIONS,
                (BiomeLoadingContext biomeLoadingContext) -> biomeLoadingContext.is(Biomes.MUSHROOM_FIELDS),
                (BiomeModificationContext biomeModificationContext) -> {
                    biomeModificationContext.generationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    VegetationPlacements.MUSHROOM_ISLAND_VEGETATION);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE);
                });
        context.registerBiomeModification(BiomeLoadingPhase.ADDITIONS,
                (BiomeLoadingContext biomeLoadingContext) -> biomeLoadingContext.is(Biomes.MUSHROOM_FIELDS),
                (BiomeModificationContext biomeModificationContext) -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.WATER_AMBIENT,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(),
                                            5,
                                            1,
                                            5));
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.MYCELIAL_GROWTH_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.BLUE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.ORANGE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PURPLE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.BLUE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.ORANGE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PURPLE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                });
    }
}
