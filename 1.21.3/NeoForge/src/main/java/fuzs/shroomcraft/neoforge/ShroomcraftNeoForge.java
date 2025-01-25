package fuzs.shroomcraft.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.data.ModRecipeProvider;
import fuzs.shroomcraft.data.loot.ModBlockLootProvider;
import fuzs.shroomcraft.data.tags.ModBlockTagProvider;
import fuzs.shroomcraft.data.tags.ModItemTagProvider;
import net.neoforged.fml.common.Mod;

@Mod(Shroomcraft.MOD_ID)
public class ShroomcraftNeoForge {

    public ShroomcraftNeoForge() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
        DataProviderHelper.registerDataProviders(Shroomcraft.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModItemTagProvider::new,
                ModRecipeProvider::new);
    }
}
