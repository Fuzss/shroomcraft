package fuzs.shroomcraft.fabric;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class ShroomcraftFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
        DynamicRegistries.registerSynced(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY, MobBlockVariant.DIRECT_CODEC);
        DynamicRegistries.registerSynced(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, MobBlockVariant.DIRECT_CODEC);
        DynamicRegistries.registerSynced(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY, MobBlockVariant.DIRECT_CODEC);
    }
}
