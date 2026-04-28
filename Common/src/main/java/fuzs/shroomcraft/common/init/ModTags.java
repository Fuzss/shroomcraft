package fuzs.shroomcraft.common.init;

import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
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
    public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("blue_shroomwood_logs");
    public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("orange_shroomwood_logs");
    public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("purple_shroomwood_logs");
    public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("shroomwood_logs");
    public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("blue_shroomwood_logs");
    public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("orange_shroomwood_logs");
    public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("purple_shroomwood_logs");
    public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("shroomwood_logs");
    public static final TagKey<Block> SUPPORTS_MUSHROOM_SPROUTS_BLOCK_TAG = TAGS.registerBlockTag(
            "supports_mushroom_sprouts");
    public static final TagKey<Block> SUPPORTS_TINY_MUSHROOM_BLOCK_TAG = TAGS.registerBlockTag("supports_tiny_mushroom");
    public static final TagKey<Block> HUGE_BLUE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = TAGS.registerBlockTag(
            "huge_blue_mushroom_can_place_on");
    public static final TagKey<Block> HUGE_ORANGE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = TAGS.registerBlockTag(
            "huge_orange_mushroom_can_place_on");
    public static final TagKey<Block> HUGE_PURPLE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = TAGS.registerBlockTag(
            "huge_purple_mushroom_can_place_on");

    public static void bootstrap() {
        // NO-OP
    }
}
