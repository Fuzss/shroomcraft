package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    static final TagFactory TAGS = TagFactory.make(Shroomcraft.MOD_ID);
    public static final TagKey<MobBlockVariant> NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG = TAGS.registerTagKey(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "nether_spawns");
    public static final TagKey<MobBlockVariant> DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG = TAGS.registerTagKey(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "default_spawns");
    public static final TagKey<Item> MUSHROOMS_ITEM_TAG = TAGS.registerItemTag("mushrooms");
    public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("purple_shroomwood_logs");
    public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("orange_shroomwood_logs");
    public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("blue_shroomwood_logs");
    public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("shroomwood_logs");
    public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("purple_shroomwood_logs");
    public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("orange_shroomwood_logs");
    public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("blue_shroomwood_logs");
    public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("shroomwood_logs");

    public static void bootstrap() {
        // NO-OP
    }
}
