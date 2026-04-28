package fuzs.shroomcraft.neoforge;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.data.ModRecipeProvider;
import fuzs.shroomcraft.common.data.loot.ModBlockLootProvider;
import fuzs.shroomcraft.common.data.loot.ModEntityLootProvider;
import fuzs.shroomcraft.common.data.loot.ModShearingLootProvider;
import fuzs.shroomcraft.common.data.tags.ModBlockTagProvider;
import fuzs.shroomcraft.common.data.tags.ModCluckshroomVariantTagProvider;
import fuzs.shroomcraft.common.data.tags.ModEntityTypeTagProvider;
import fuzs.shroomcraft.common.data.tags.ModItemTagProvider;
import fuzs.shroomcraft.common.init.ModRegistry;
import net.neoforged.fml.common.Mod;

@Mod(Shroomcraft.MOD_ID)
public class ShroomcraftNeoForge {

    public ShroomcraftNeoForge() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
        DataProviderHelper.registerDataProviders(Shroomcraft.MOD_ID,
                ModRegistry.REGISTRY_SET_BUILDER,
                ModBlockLootProvider::new,
                ModEntityLootProvider::new,
                ModShearingLootProvider::new,
                ModBlockTagProvider::new,
                ModItemTagProvider::new,
                ModEntityTypeTagProvider::new,
                ModCluckshroomVariantTagProvider::new,
                ModRecipeProvider::new);
    }
}
