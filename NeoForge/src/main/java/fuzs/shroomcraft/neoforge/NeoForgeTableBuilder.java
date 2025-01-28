package fuzs.shroomcraft.neoforge;

import fuzs.shroomcraft.AbstractTableBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public final class NeoForgeTableBuilder extends AbstractTableBuilder {
    private final LootTable table;

    public NeoForgeTableBuilder(LootTable table) {
        this.table = table;
    }

    @Override
    public AbstractTableBuilder withPool(LootPool.Builder lootPool) {
        return this.withPool(lootPool.build());
    }

    @Override
    public AbstractTableBuilder setParamSet(ContextKeySet paramSet) {
        this.table.paramSet = paramSet;
        return this;
    }

    @Override
    public AbstractTableBuilder setRandomSequence(ResourceLocation randomSequence) {
        this.table.randomSequence = Optional.of(randomSequence);
        return this;
    }

    @Override
    public AbstractTableBuilder apply(LootItemFunction.Builder functionBuilder) {
        return this.apply(functionBuilder.build());
    }

    @Override
    public LootTable build() {
        return this.table;
    }

    @Override
    public AbstractTableBuilder withPool(LootPool lootPool) {
        if (!(this.table.pools instanceof ArrayList<LootPool>)) {
            this.table.pools = new ArrayList<>(this.table.pools);
        }
        this.table.pools.add(lootPool);
        return this;
    }

    @Override
    public AbstractTableBuilder apply(LootItemFunction function) {
        if (!(this.table.functions instanceof ArrayList<LootItemFunction>)) {
            this.table.functions = new ArrayList<>(this.table.functions);
        }
        this.table.functions.add(function);
        return this;
    }

    @Override
    public AbstractTableBuilder forEachPool(Consumer<? super LootPool.Builder> consumer) {
        this;
        return this;
    }
}
