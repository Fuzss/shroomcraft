package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);

    static final TagFactory TAGS = TagFactory.make(Shroomcraft.MOD_ID);

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModFeatures.bootstrap();
    }
}
