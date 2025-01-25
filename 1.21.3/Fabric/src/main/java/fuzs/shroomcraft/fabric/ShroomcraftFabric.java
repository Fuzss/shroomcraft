package fuzs.shroomcraft.fabric;

import fuzs.shroomcraft.Shroomcraft;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class ShroomcraftFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
    }
}
