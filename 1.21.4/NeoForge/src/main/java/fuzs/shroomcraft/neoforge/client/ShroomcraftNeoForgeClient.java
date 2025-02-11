package fuzs.shroomcraft.neoforge.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.ShroomcraftClient;
import fuzs.shroomcraft.data.client.ModLanguageProvider;
import fuzs.shroomcraft.data.client.ModModelProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Shroomcraft.MOD_ID, dist = Dist.CLIENT)
public class ShroomcraftNeoForgeClient {

    public ShroomcraftNeoForgeClient() {
        ClientModConstructor.construct(Shroomcraft.MOD_ID, ShroomcraftClient::new);
        DataProviderHelper.registerDataProviders(Shroomcraft.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}
