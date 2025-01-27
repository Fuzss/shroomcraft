package fuzs.shroomcraft.init;

import fuzs.shroomcraft.world.level.levelgen.feature.HugeBlueMushroomFeature;
import fuzs.shroomcraft.world.level.levelgen.feature.HugeOrangeMushroomFeature;
import fuzs.shroomcraft.world.level.levelgen.feature.HugePurpleMushroomFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_blue_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_orange_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_purple_mushroom");
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_BLUE_MUSHROOM_FEATURE = ModRegistry.REGISTRIES.register(
            Registries.FEATURE,
            "huge_red_mushroom",
            () -> new HugeBlueMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_ORANGE_MUSHROOM_FEATURE = ModRegistry.REGISTRIES.register(
            Registries.FEATURE,
            "huge_orange_mushroom",
            () -> new HugeOrangeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_PURPLE_MUSHROOM_FEATURE = ModRegistry.REGISTRIES.register(
            Registries.FEATURE,
            "huge_purple_mushroom",
            () -> new HugePurpleMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHROOM_ISLAND_VEGETATION_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "mushroom_island_vegetation");
    public static final ResourceKey<PlacedFeature> MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIAL_GROWTH_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "mycelial_growth");
    public static final ResourceKey<PlacedFeature> MYCELIAL_GROWTH_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
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

    public static void bootstrap() {
        // NO-OP
    }
}
