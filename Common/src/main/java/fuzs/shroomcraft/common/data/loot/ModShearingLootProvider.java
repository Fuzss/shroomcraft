package fuzs.shroomcraft.common.data.loot;

import fuzs.puzzleslib.common.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.common.init.CluckshroomVariants;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.init.ModItems;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModShearingLootProvider extends AbstractLootProvider.Simple {

    public ModShearingLootProvider(DataProviderContext context) {
        super(LootContextParamSets.SHEARING, context);
    }

    @Override
    public void addLootTables() {
        this.add(MooshroomVariant.BLUE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.BLUE_MUSHROOM.value()))));
        this.add(MooshroomVariant.ORANGE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.ORANGE_MUSHROOM.value()))));
        this.add(MooshroomVariant.PURPLE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_MUSHROOM.value()))));
        this.add(MooshroomVariant.CRIMSON.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS))));
        this.add(MooshroomVariant.WARPED.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(Items.WARPED_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.RED_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.RED_MUSHROOM))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.BROWN_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.BROWN_MUSHROOM))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.CRIMSON_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.WARPED_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.WARPED_FUNGUS))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.BLUE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.BLUE_MUSHROOM.value()))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.ORANGE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.ORANGE_MUSHROOM.value()))));
        this.add(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE,
                        CluckshroomVariants.PURPLE_CLUCKSHROOM_VARIANT),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_MUSHROOM.value()))));
    }
}
