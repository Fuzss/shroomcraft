package fuzs.shroomcraft.fabric;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.shroomcraft.common.Shroomcraft;
import net.fabricmc.api.ModInitializer;

public class ShroomcraftFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
    }
}
