package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS = register("blue_shroomwood_logs");
        public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS = register("orange_shroomwood_logs");
        public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS = register("purple_shroomwood_logs");
        public static final TagKey<Block> SHROOMWOOD_LOGS = register("shroomwood_logs");
        public static final TagKey<Block> SUPPORTS_MUSHROOM_SPROUTS = register("supports_mushroom_sprouts");
        public static final TagKey<Block> SUPPORTS_TINY_MUSHROOM = register("supports_tiny_mushroom");
        public static final TagKey<Block> HUGE_BLUE_MUSHROOM_CAN_PLACE_ON = register("huge_blue_mushroom_can_place_on");
        public static final TagKey<Block> HUGE_ORANGE_MUSHROOM_CAN_PLACE_ON = register(
                "huge_orange_mushroom_can_place_on");
        public static final TagKey<Block> HUGE_PURPLE_MUSHROOM_CAN_PLACE_ON = register(
                "huge_purple_mushroom_can_place_on");

        private static TagKey<Block> register(String name) {
            return TagKey.create(Registries.BLOCK, Shroomcraft.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS = register("blue_shroomwood_logs");
        public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS = register("orange_shroomwood_logs");
        public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS = register("purple_shroomwood_logs");
        public static final TagKey<Item> SHROOMWOOD_LOGS = register("shroomwood_logs");

        private static TagKey<Item> register(String name) {
            return TagKey.create(Registries.ITEM, Shroomcraft.id(name));
        }
    }

    public static class MooshroomVariants {
        public static final TagKey<MobVariant> DEFAULT_SPAWNS = register("default_spawns");
        public static final TagKey<MobVariant> NETHER_SPAWNS = register("nether_spawns");

        private static TagKey<MobVariant> register(String name) {
            return TagKey.create(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY, Shroomcraft.id(name));
        }
    }

    public static class CluckshroomVariants {
        public static final TagKey<MobVariant> DEFAULT_SPAWNS = register("default_spawns");
        public static final TagKey<MobVariant> NETHER_SPAWNS = register("nether_spawns");

        private static TagKey<MobVariant> register(String name) {
            return TagKey.create(fuzs.shroomcraft.common.init.CluckshroomVariants.REGISTRY_KEY, Shroomcraft.id(name));
        }
    }
}
