package fuzs.shroomcraft.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {
    public static final ResourceKey<LootTable> SHEAR_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom");
    public static final ResourceKey<LootTable> SHEAR_BLUE_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom/blue");
    public static final ResourceKey<LootTable> SHEAR_ORANGE_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom/orange");
    public static final ResourceKey<LootTable> SHEAR_PURPLE_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom/purple");
    public static final ResourceKey<LootTable> SHEAR_CRIMSON_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom/crismon");
    public static final ResourceKey<LootTable> SHEAR_WARPED_MOOSHROOM_LOOT_TABLE = ModRegistry.REGISTRIES.registerLootTable(
            "shearing/mooshroom/warped");

    public static void bootstrap() {
        // NO-OP
    }
}
