package fuzs.shroomcraft.fabric;

import fuzs.shroomcraft.AbstractTableBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.function.Consumer;

public final class FabricTableBuilder extends AbstractTableBuilder {
    private final LootTable.Builder builder;

    public FabricTableBuilder(LootTable.Builder builder) {
        this.builder = builder;
    }

    @Override
    public AbstractTableBuilder withPool(LootPool.Builder lootPool) {
        this.builder.withPool(lootPool);
        return this;
    }

    @Override
    public AbstractTableBuilder setParamSet(ContextKeySet paramSet) {
        this.builder.setParamSet(paramSet);
        return this;
    }

    @Override
    public AbstractTableBuilder setRandomSequence(ResourceLocation randomSequence) {
        this.builder.setRandomSequence(randomSequence);
        return this;
    }

    @Override
    public AbstractTableBuilder apply(LootItemFunction.Builder functionBuilder) {
        this.builder.apply(functionBuilder);
        return this;
    }

    @Override
    public LootTable build() {
        return this.builder.build();
    }

    @Override
    public AbstractTableBuilder withPool(LootPool lootPool) {
        this.builder.pool(lootPool);
        return this;
    }

    @Override
    public AbstractTableBuilder apply(LootItemFunction function) {
        this.builder.apply(function);
        return this;
    }

    @Override
    public AbstractTableBuilder forEachPool(Consumer<? super LootPool.Builder> consumer) {
        this.builder.modifyPools(consumer);
        return this;
    }
}
