package fuzs.shroomcraft;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

public final class LootTableHelper {

    private LootTableHelper() {
        // NO-OP
    }

    public static LootTable.Builder asMutable(LootTable lootTable) {
        return new ForwardingLootTableBuilder(lootTable);
    }

    public static LootPool.Builder asMutable(LootPool lootPool) {
        return new ForwardingLootPoolBuilder(lootPool);
    }
}
