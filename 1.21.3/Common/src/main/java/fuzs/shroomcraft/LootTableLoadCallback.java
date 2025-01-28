package fuzs.shroomcraft;

import fuzs.puzzleslib.api.event.v1.core.EventInvoker;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

@FunctionalInterface
public interface LootTableLoadCallback {
    EventInvoker<LootTableLoadCallback> EVENT = EventInvoker.lookup(LootTableLoadCallback.class);

    /**
     * Runs for every loot table upon loading, allows for modifying the loot table.
     *
     * @param resourceLocation the loot table id
     * @param lootTable        the loot table builder instance
     * @param registries       the registry access
     */
    void onLootTableLoad(ResourceLocation resourceLocation, LootTable.Builder lootTable, HolderLookup.Provider registries);
}
