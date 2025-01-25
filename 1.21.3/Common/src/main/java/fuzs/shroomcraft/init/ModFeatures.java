package fuzs.shroomcraft.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLUE_MUSHROOM = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_blue_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_ORANGE_MUSHROOM = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_orange_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_MUSHROOM = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "huge_purple_mushroom");

    public static void bootstrap() {
        // NO-OP
    }
}
