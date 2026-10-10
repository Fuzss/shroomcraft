package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;

public class ModEntityDataSerializers {
    public static final Holder.Reference<EntityDataSerializer<Holder<MobVariant>>> MOOSHROOM_VARIANT = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "mooshroom_variant",
            () -> EntityDataSerializer.forValueType(MobVariant.streamCodec(MooshroomVariants.REGISTRY_KEY)));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobVariant>>> CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "cluckshroom_variant",
            () -> EntityDataSerializer.forValueType(MobVariant.streamCodec(CluckshroomVariants.REGISTRY_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}
