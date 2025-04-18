package fuzs.shroomcraft.data.loot;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModShearingLootProvider extends AbstractLootProvider.Simple {

    public ModShearingLootProvider(DataProviderContext context) {
        super(LootContextParamSets.SHEARING, context);
    }

    @Override
    public void addLootTables() {
        this.add(ModRegistry.SHEAR_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(AlternativesEntry.alternatives(NestedLootTable.lootTableReference(ModRegistry.SHEAR_BLUE_MOOSHROOM_LOOT_TABLE)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .components(DataComponentMatchers.Builder.components()
                                                                        .exact(DataComponentExactPredicate.expect(ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                                                                                ModMushroomCow.ColorVariant.BLUE))
                                                                        .build()))),
                                        NestedLootTable.lootTableReference(ModRegistry.SHEAR_ORANGE_MOOSHROOM_LOOT_TABLE)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .components(DataComponentMatchers.Builder.components()
                                                                        .exact(DataComponentExactPredicate.expect(
                                                                                ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                                                                                ModMushroomCow.ColorVariant.ORANGE))
                                                                        .build()))),
                                        NestedLootTable.lootTableReference(ModRegistry.SHEAR_PURPLE_MOOSHROOM_LOOT_TABLE)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .components(DataComponentMatchers.Builder.components()
                                                                        .exact(DataComponentExactPredicate.expect(
                                                                                ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                                                                                ModMushroomCow.ColorVariant.PURPLE))
                                                                        .build()))),
                                        NestedLootTable.lootTableReference(ModRegistry.SHEAR_CRIMSON_MOOSHROOM_LOOT_TABLE)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .components(DataComponentMatchers.Builder.components()
                                                                        .exact(DataComponentExactPredicate.expect(
                                                                                ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                                                                                ModMushroomCow.ColorVariant.CRIMSON))
                                                                        .build()))),
                                        NestedLootTable.lootTableReference(ModRegistry.SHEAR_WARPED_MOOSHROOM_LOOT_TABLE)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .components(DataComponentMatchers.Builder.components()
                                                                        .exact(DataComponentExactPredicate.expect(
                                                                                ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                                                                                ModMushroomCow.ColorVariant.WARPED))
                                                                        .build())))))));
        this.add(ModRegistry.SHEAR_BLUE_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.BLUE_MUSHROOM.value()))));
        this.add(ModRegistry.SHEAR_ORANGE_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.ORANGE_MUSHROOM.value()))));
        this.add(ModRegistry.SHEAR_PURPLE_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_MUSHROOM.value()))));
        this.add(ModRegistry.SHEAR_CRIMSON_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS))));
        this.add(ModRegistry.SHEAR_WARPED_MOOSHROOM_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(Items.WARPED_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.RED_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.RED_MUSHROOM))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.BROWN_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.BROWN_MUSHROOM))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.CRIMSON_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.WARPED_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.WARPED_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.BLUE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.BLUE_MUSHROOM.value()))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.ORANGE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.ORANGE_MUSHROOM.value()))));
        this.add(MobBlockVariant.getShearingLootTable(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ModRegistry.PURPLE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_MUSHROOM.value()))));
    }
}
