package fuzs.shroomcraft.neoforge.client;

import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.ShroomcraftClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Shroomcraft.MOD_ID, dist = Dist.CLIENT)
public class ShroomcraftNeoForgeClient {

    public ShroomcraftNeoForgeClient() {
        ClientModConstructor.construct(Shroomcraft.MOD_ID, ShroomcraftClient::new);
    }
}
