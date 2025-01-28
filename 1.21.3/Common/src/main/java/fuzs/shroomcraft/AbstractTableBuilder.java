package fuzs.shroomcraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

public abstract class AbstractTableBuilder extends LootTable.Builder {

    @Override
    public abstract AbstractTableBuilder withPool(LootPool.Builder lootPool);

    @Override
    public abstract AbstractTableBuilder setParamSet(ContextKeySet paramSet);

    @Override
    public abstract AbstractTableBuilder setRandomSequence(ResourceLocation randomSequence);

    @Override
    public abstract AbstractTableBuilder apply(LootItemFunction.Builder functionBuilder);

    @ApiStatus.Internal
    @Override
    public abstract LootTable build();

    public abstract AbstractTableBuilder withPool(LootPool lootPool);

    public abstract AbstractTableBuilder apply(LootItemFunction function);

    public abstract AbstractTableBuilder forEachPool(Consumer<? super LootPool.Builder> consumer);
}
