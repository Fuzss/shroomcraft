package fuzs.shroomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;

public class ModFeatures {
    public static final Holder.Reference<Block> BLUE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "blue_mushroom",
            () -> null);

    public static void bootstrap() {
        // NO-OP
    }
}
