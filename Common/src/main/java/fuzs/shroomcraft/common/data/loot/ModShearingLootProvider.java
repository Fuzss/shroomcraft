package fuzs.shroomcraft.common.data.loot;

import fuzs.puzzleslib.common.api.data.v3.loot.AbstractLootSubProvider;
import fuzs.shroomcraft.common.init.CluckshroomVariants;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.init.ModItems;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModShearingLootProvider extends AbstractLootSubProvider {

    public ModShearingLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.registerMooshroomShearingDrops(MooshroomVariant.BLUE, ModItems.BLUE_MUSHROOM.value());
        this.registerMooshroomShearingDrops(MooshroomVariant.ORANGE, ModItems.ORANGE_MUSHROOM.value());
        this.registerMooshroomShearingDrops(MooshroomVariant.PURPLE, ModItems.PURPLE_MUSHROOM.value());
        this.registerMooshroomShearingDrops(MooshroomVariant.CRIMSON, Items.CRIMSON_FUNGUS);
        this.registerMooshroomShearingDrops(MooshroomVariant.WARPED, Items.WARPED_FUNGUS);
        this.registerCluckshroomShearingDrops(CluckshroomVariants.RED, Items.RED_MUSHROOM);
        this.registerCluckshroomShearingDrops(CluckshroomVariants.BROWN, Items.BROWN_MUSHROOM);
        this.registerCluckshroomShearingDrops(CluckshroomVariants.CRIMSON, Items.CRIMSON_FUNGUS);
        this.registerCluckshroomShearingDrops(CluckshroomVariants.WARPED, Items.WARPED_FUNGUS);
        this.registerCluckshroomShearingDrops(CluckshroomVariants.BLUE, ModItems.BLUE_MUSHROOM.value());
        this.registerCluckshroomShearingDrops(CluckshroomVariants.ORANGE, ModItems.ORANGE_MUSHROOM.value());
        this.registerCluckshroomShearingDrops(CluckshroomVariants.PURPLE, ModItems.PURPLE_MUSHROOM.value());
    }

    public final void registerMooshroomShearingDrops(MooshroomVariant variant, Item item) {
        this.output.accept(variant.shearingLoot,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(5))
                                .add(LootItem.lootTableItem(item))));
    }

    public final void registerCluckshroomShearingDrops(ResourceKey<MobBlockVariant> variant, Item item) {
        this.output.accept(MobBlockVariant.getShearingLootTable(ModEntityTypes.CLUCKSHROOM, variant),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(3))
                                .add(LootItem.lootTableItem(item))));
    }
}
