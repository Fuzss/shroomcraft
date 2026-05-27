package fuzs.shroomcraft.data.loot;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.CluckshroomVariants;
import fuzs.shroomcraft.init.ModEntityTypes;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.Mooshroom;
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
        this.add(Mooshroom.ColorVariant.BLUE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.BLUE_MUSHROOM.value()))));
        this.add(Mooshroom.ColorVariant.ORANGE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.ORANGE_MUSHROOM.value()))));
        this.add(Mooshroom.ColorVariant.PURPLE.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_MUSHROOM.value()))));
        this.add(Mooshroom.ColorVariant.CRIMSON.shearingLootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS))));
        this.add(Mooshroom.ColorVariant.WARPED.shearingLootTable,
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
