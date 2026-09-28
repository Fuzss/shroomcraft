package fuzs.shroomcraft.common.init;

import com.mojang.serialization.MapCodec;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugeBlueMushroomFeature;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugeOrangeMushroomFeature;
import fuzs.shroomcraft.common.world.level.levelgen.feature.HugePurpleMushroomFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ModFeatureTypes {
    public static final Holder.Reference<MapCodec<? extends Feature>> HUGE_PURPLE_MUSHROOM = ModRegistry.REGISTRIES.register(
            Registries.FEATURE_TYPE,
            "huge_purple_mushroom",
            () -> HugePurpleMushroomFeature.CODEC);
    public static final Holder.Reference<MapCodec<? extends Feature>> HUGE_ORANGE_MUSHROOM = ModRegistry.REGISTRIES.register(
            Registries.FEATURE_TYPE,
            "huge_orange_mushroom",
            () -> HugeOrangeMushroomFeature.CODEC);
    public static final Holder.Reference<MapCodec<? extends Feature>> HUGE_BLUE_MUSHROOM = ModRegistry.REGISTRIES.register(
            Registries.FEATURE_TYPE,
            "huge_blue_mushroom",
            () -> HugeBlueMushroomFeature.CODEC);

    public static void bootstrap() {
        // NO-OP
    }
}
