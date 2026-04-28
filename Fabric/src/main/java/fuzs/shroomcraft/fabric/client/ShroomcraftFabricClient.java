package fuzs.shroomcraft.fabric.client;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.client.ShroomcraftClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class ShroomcraftFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(Shroomcraft.MOD_ID, ShroomcraftClient::new);
    }
}
