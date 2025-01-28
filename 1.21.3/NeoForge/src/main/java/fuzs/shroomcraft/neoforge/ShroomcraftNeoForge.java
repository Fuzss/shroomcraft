package fuzs.shroomcraft.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.data.ModDatapackRegistriesProvider;
import fuzs.shroomcraft.data.ModRecipeProvider;
import fuzs.shroomcraft.data.loot.ModBlockLootProvider;
import fuzs.shroomcraft.data.loot.ModEntityLootProvider;
import fuzs.shroomcraft.data.loot.ModShearingLootProvider;
import fuzs.shroomcraft.data.tags.ModBlockTagProvider;
import fuzs.shroomcraft.data.tags.ModEntityTypeTagProvider;
import fuzs.shroomcraft.data.tags.ModItemTagProvider;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(Shroomcraft.MOD_ID)
public class ShroomcraftNeoForge {

    public ShroomcraftNeoForge(ModContainer modContainer) {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
        registerLoadingHandlers(modContainer.getEventBus());
        DataProviderHelper.registerDataProviders(Shroomcraft.MOD_ID,
                ModDatapackRegistriesProvider::new,
                ModBlockLootProvider::new,
                ModEntityLootProvider::new,
                ModShearingLootProvider::new,
                ModBlockTagProvider::new,
                ModItemTagProvider::new,
                ModEntityTypeTagProvider::new,
                ModRecipeProvider::new);
    }

    private static void registerLoadingHandlers(IEventBus eventBus) {
        eventBus.addListener((final DataPackRegistryEvent.NewRegistry evt) -> {
            evt.dataPackRegistry(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
                    MobBlockVariant.DIRECT_CODEC,
                    MobBlockVariant.DIRECT_CODEC);
            evt.dataPackRegistry(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
                    MobBlockVariant.DIRECT_CODEC,
                    MobBlockVariant.DIRECT_CODEC);
            evt.dataPackRegistry(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
                    MobBlockVariant.DIRECT_CODEC,
                    MobBlockVariant.DIRECT_CODEC);
        });
    }
}
