package fuzs.shroomcraft;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.BlockInteractionsContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shroomcraft implements ModConstructor {
    public static final String MOD_ID = "shroomcraft";
    public static final String MOD_NAME = "Shroomcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onRegisterBlockInteractions(BlockInteractionsContext context) {
        context.registerStrippable(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), Blocks.MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), ModBlocks.BLUE_MUSHROOM_STEM.value());
        context.registerStrippable(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(),
                ModBlocks.ORANGE_MUSHROOM_STEM.value());
        context.registerStrippable(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(),
                ModBlocks.PURPLE_MUSHROOM_STEM.value());
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
