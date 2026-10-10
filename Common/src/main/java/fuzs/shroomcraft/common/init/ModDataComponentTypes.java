package fuzs.shroomcraft.common.init;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.animal.cow.MushroomCow;

public class ModDataComponentTypes {
    public static final Holder.Reference<DataComponentType<Either<MushroomCow.Variant, Holder<MobVariant>>>> MOOSHROOM_VARIANT = ModRegistry.REGISTRIES.registerDataComponentType(
            "mooshroom/variant",
            (DataComponentType.Builder<Either<MushroomCow.Variant, Holder<MobVariant>>> builder) -> {
                return builder.persistent(Codec.either(MushroomCow.Variant.CODEC,
                                MobVariant.codec(MooshroomVariants.REGISTRY_KEY)))
                        .networkSynchronized(ByteBufCodecs.either(MushroomCow.Variant.STREAM_CODEC,
                                MobVariant.streamCodec(MooshroomVariants.REGISTRY_KEY)));
            });
    public static final Holder.Reference<DataComponentType<Holder<MobVariant>>> CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.registerDataComponentType(
            "cluckshroom/variant",
            (DataComponentType.Builder<Holder<MobVariant>> builder) -> builder.persistent(MobVariant.codec(
                            CluckshroomVariants.REGISTRY_KEY))
                    .networkSynchronized(MobVariant.streamCodec(CluckshroomVariants.REGISTRY_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}
