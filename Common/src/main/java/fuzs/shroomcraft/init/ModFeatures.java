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

    public static void bootstrap() {
        // NO-OP
    }
}
