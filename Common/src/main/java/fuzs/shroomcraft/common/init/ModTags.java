package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = register("blue_shroomwood_logs");
        public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = register("orange_shroomwood_logs");
        public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = register("purple_shroomwood_logs");
        public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = register("shroomwood_logs");
        public static final TagKey<Block> SUPPORTS_MUSHROOM_SPROUTS_BLOCK_TAG = register("supports_mushroom_sprouts");
        public static final TagKey<Block> SUPPORTS_TINY_MUSHROOM_BLOCK_TAG = register("supports_tiny_mushroom");
        public static final TagKey<Block> HUGE_BLUE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = register(
                "huge_blue_mushroom_can_place_on");
        public static final TagKey<Block> HUGE_ORANGE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = register(
                "huge_orange_mushroom_can_place_on");
        public static final TagKey<Block> HUGE_PURPLE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG = register(
                "huge_purple_mushroom_can_place_on");

        private static TagKey<Block> register(String name) {
            return TagKey.create(Registries.BLOCK, Shroomcraft.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MUSHROOMS_ITEM_TAG = register("mushrooms");
        public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = register("blue_shroomwood_logs");
        public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = register("orange_shroomwood_logs");
        public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = register("purple_shroomwood_logs");
        public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = register("shroomwood_logs");

        private static TagKey<Item> register(String name) {
            return TagKey.create(Registries.ITEM, Shroomcraft.id(name));
        }
    }

    public static class MobBlockVariants {
        public static final TagKey<MobBlockVariant> NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG = register("nether_spawns");
        public static final TagKey<MobBlockVariant> DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG = register("default_spawns");

        private static TagKey<MobBlockVariant> register(String name) {
            return TagKey.create(CluckshroomVariants.REGISTRY_KEY, Shroomcraft.id(name));
        }
    }
}
