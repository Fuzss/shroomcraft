package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;

public class ModDataComponentTypes {
    public static final Holder.Reference<DataComponentType<MooshroomVariant>> MOOSHROOM_VARIANT = ModRegistry.REGISTRIES.registerDataComponentType(
            "mooshroom/variant",
            (DataComponentType.Builder<MooshroomVariant> builder) -> builder.persistent(MooshroomVariant.CODEC)
                    .networkSynchronized(MooshroomVariant.STREAM_CODEC));
    public static final Holder.Reference<DataComponentType<Holder<MobBlockVariant>>> MOB_BLOCK_VARIANT = ModRegistry.REGISTRIES.registerDataComponentType(
            "mob_block_variant",
            (DataComponentType.Builder<Holder<MobBlockVariant>> builder) -> builder.persistent(MobBlockVariant.codec(
                            CluckshroomVariants.REGISTRY_KEY))
                    .networkSynchronized(MobBlockVariant.streamCodec(CluckshroomVariants.REGISTRY_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}
