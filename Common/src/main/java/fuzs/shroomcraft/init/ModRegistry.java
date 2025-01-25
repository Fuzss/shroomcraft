package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.puzzleslib.api.init.v3.tags.TypedTagFactory;
import fuzs.shroomcraft.Shroomcraft;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<Block> BLUE_MUSHROOM_BLOCK = REGISTRIES.registerBlock("blue_mushroom", () -> null);

    static final TagFactory TAGS = TagFactory

    public static void bootstrap() {
        // NO-OP
    }
}
