package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;

public class ModEntityDataSerializers {
    public static final Holder.Reference<EntityDataSerializer<MooshroomVariant>> MUSHROOM_VARIANT = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "mushroom_variant",
            () -> EntityDataSerializer.forValueType(MooshroomVariant.STREAM_CODEC));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobBlockVariant>>> CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "cluckshroom_variant",
            () -> EntityDataSerializer.forValueType(MobBlockVariant.streamCodec(CluckshroomVariants.REGISTRY_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}
