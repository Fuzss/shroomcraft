package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.world.entity.animal.chicken.Cluckshroom;
import fuzs.shroomcraft.common.world.entity.animal.cow.Mooshroom;
import fuzs.shroomcraft.common.world.entity.animal.fish.Shroomfin;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

public class ModEntityTypes {
    public static final Holder.Reference<EntityType<Mooshroom>> MOOSHROOM_ENTITY_TYPE = ModRegistry.REGISTRIES.registerEntityType(
            "mooshroom",
            () -> EntityType.Builder.of(Mooshroom::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10));
    public static final Holder.Reference<EntityType<Shroomfin>> SHROOMFIN_ENTITY_TYPE = ModRegistry.REGISTRIES.registerEntityType(
            "shroomfin",
            () -> EntityType.Builder.of(Shroomfin::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.4F)
                    .eyeHeight(0.26F)
                    .clientTrackingRange(4));
    public static final Holder.Reference<EntityType<Cluckshroom>> CLUCKSHROOM_ENTITY_TYPE = ModRegistry.REGISTRIES.registerEntityType(
            "cluckshroom",
            () -> EntityType.Builder.of(Cluckshroom::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10));

    public static void bootstrap() {
        // NO-OP
    }
}
