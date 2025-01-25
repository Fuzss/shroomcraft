package fuzs.shroomcraft.neoforge;

import fuzs.shroomcraft.Shroomcraft;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.neoforged.fml.common.Mod;

@Mod(Shroomcraft.MOD_ID)
public class ShroomcraftNeoForge {

    public ShroomcraftNeoForge() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
    }
}
