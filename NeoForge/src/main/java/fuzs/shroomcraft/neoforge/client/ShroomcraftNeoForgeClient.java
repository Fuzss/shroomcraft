package fuzs.shroomcraft.neoforge.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.client.ShroomcraftClient;
import fuzs.shroomcraft.common.data.client.ModLanguageProvider;
import fuzs.shroomcraft.common.data.client.ModModelProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Shroomcraft.MOD_ID, dist = Dist.CLIENT)
public class ShroomcraftNeoForgeClient {

    public ShroomcraftNeoForgeClient() {
        ClientModConstructor.construct(Shroomcraft.MOD_ID, ShroomcraftClient::new);
        DataProviderBuilder.of(Shroomcraft.MOD_ID).addProvider(ModLanguageProvider::new, ModModelProvider::new);
    }
}
