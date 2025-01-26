package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.ORANGE_MUSHROOM);

    static final TagFactory TAGS = TagFactory.make(Shroomcraft.MOD_ID);
    public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("shroomwood_logs");
    public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("blue_shroomwood_logs");
    public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("orange_shroomwood_logs");
    public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("purple_shroomwood_logs");
    public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("shroomwood_logs");
    public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("blue_shroomwood_logs");
    public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("orange_shroomwood_logs");
    public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("purple_shroomwood_logs");

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModBlockFamilies.bootstrap();
        ModFeatures.bootstrap();
    }
}
