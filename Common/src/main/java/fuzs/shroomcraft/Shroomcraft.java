package fuzs.shroomcraft;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.*;
import fuzs.puzzleslib.api.event.v1.AddBlockEntityTypeBlocksCallback;
import fuzs.puzzleslib.api.event.v1.entity.ServerEntityLevelEvents;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadCallback;
import fuzs.shroomcraft.handler.BiomeModificationsHandler;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.fish.AbstractFish;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public class Shroomcraft implements ModConstructor {
    public static final String MOD_ID = "shroomcraft";
    public static final String MOD_NAME = "Shroomcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandler();
    }

    private static void registerEventHandler() {
        AddBlockEntityTypeBlocksCallback.EVENT.register((BiConsumer<BlockEntityType<?>, Block> consumer) -> {
            ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
                blockSetFamily.generateFor(consumer, BlockSetFamily.VARIANT_BLOCK_ENTITY_TYPE);
            });
        });
        ServerEntityLevelEvents.LOAD.register(ModMushroomCow::onEntityLoad);
        PlayerInteractEvents.USE_ENTITY.register(ModMushroomCow::onEntityInteract);
        LootTableLoadCallback.EVENT.register((Identifier identifier, LootTable.Builder lootTable, HolderLookup.@Nullable Provider registries) -> {
            if (BuiltInLootTables.FISHING_FISH.identifier().equals(identifier)) {
                MutableBoolean mutableBoolean = new MutableBoolean();
                LootTableLoadCallback.forEachPool(lootTable, (LootPool.Builder builder) -> {
                    if (mutableBoolean.isFalse()) {
                        mutableBoolean.setTrue();
                        LootPoolSingletonContainer.Builder<?> entriesBuilder = LootItem.lootTableItem(ModItems.SHROOMFIN.value())
                                .setWeight(15);
                        if (registries != null) {
                            entriesBuilder.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                    .setBiomes(HolderSet.direct(registries.lookupOrThrow(Registries.BIOME)
                                            .getOrThrow(Biomes.MUSHROOM_FIELDS)))));
                        }

                        builder.add(entriesBuilder);
                    }
                });
            }
        });
    }

    @Override
    public void onCommonSetup() {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            BlockSetType.register(blockSetFamily.getBlockSetType());
            WoodType.register(blockSetFamily.getWoodType());
            blockSetFamily.generateFor(BlockSetFamily.VARIANT_DISPENSE_BEHAVIOR);
        });
        DispenserBlock.registerBehavior(ModItems.SHROOMFIN_BUCKET.value(), new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource blockSource, ItemStack item) {
                DispensibleContainerItem dispensibleContainerItem = (DispensibleContainerItem) item.getItem();
                BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                Level level = blockSource.level();
                if (dispensibleContainerItem.emptyContents(null, level, blockPos, null)) {
                    dispensibleContainerItem.checkExtraContent(null, level, item, blockPos);
                    return this.consumeWithRemainder(blockSource, item, new ItemStack(Items.BUCKET));
                } else {
                    return super.execute(blockSource, item);
                }
            }
        });
        DispenserBlock.registerProjectileBehavior(ModItems.BLUE_SHROOMBOMB.value());
        DispenserBlock.registerProjectileBehavior(ModItems.ORANGE_SHROOMBOMB.value());
        DispenserBlock.registerProjectileBehavior(ModItems.PURPLE_SHROOMBOMB.value());
    }

    @Override
    public void onRegisterEntityAttributes(EntityAttributesContext context) {
        context.registerAttributes(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(), Cow.createAttributes());
        context.registerAttributes(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(), AbstractFish.createAttributes());
        context.registerAttributes(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(), Chicken.createAttributes());
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ModMushroomCow::checkMooshroomSpawnRules);
        context.registerSpawnPlacement(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        context.registerSpawnPlacement(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Cluckshroom::checkCluckshroomSpawnRules);
    }

    @Override
    public void onRegisterGameplayContent(GameplayContentContext context) {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            blockSetFamily.generateFor(context, BlockSetFamily.VARIANT_WOODEN_FLAMMABLE);
        });
        context.registerFlammable(ModBlocks.STRIPPED_MUSHROOM_STEM, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_MUSHROOM_HYPHAE, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE, 5, 5);
        context.registerFlammable(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE, 5, 5);
        context.registerCompostable(ModItems.BROWN_SHROOMSPORES, 0.3F);
        context.registerCompostable(ModItems.RED_SHROOMSPORES, 0.3F);
        context.registerCompostable(ModItems.BLUE_SHROOMSPORES, 0.3F);
        context.registerCompostable(ModItems.ORANGE_SHROOMSPORES, 0.3F);
        context.registerCompostable(ModItems.PURPLE_SHROOMSPORES, 0.3F);
        context.registerCompostable(ModItems.MYCELIAL_GROWTH, 0.5F);
        context.registerCompostable(ModItems.MUSHROOM_SPROUTS, 0.5F);
        context.registerCompostable(ModItems.BLUE_MUSHROOM_SPROUTS, 0.5F);
        context.registerCompostable(ModItems.ORANGE_MUSHROOM_SPROUTS, 0.5F);
        context.registerCompostable(ModItems.PURPLE_MUSHROOM_SPROUTS, 0.5F);
        context.registerCompostable(ModItems.BLUE_MUSHROOM, 0.65F);
        context.registerCompostable(ModItems.ORANGE_MUSHROOM, 0.65F);
        context.registerCompostable(ModItems.PURPLE_MUSHROOM, 0.65F);
        context.registerCompostable(ModItems.BLUE_MUSHROOM_STEM, 0.65F);
        context.registerCompostable(ModItems.ORANGE_MUSHROOM_STEM, 0.65F);
        context.registerCompostable(ModItems.PURPLE_MUSHROOM_STEM, 0.65F);
        context.registerCompostable(ModItems.BLUE_MUSHROOM_BLOCK, 0.85F);
        context.registerCompostable(ModItems.ORANGE_MUSHROOM_BLOCK, 0.85F);
        context.registerCompostable(ModItems.PURPLE_MUSHROOM_BLOCK, 0.85F);
        context.registerStrippable(Blocks.MUSHROOM_STEM.builtInRegistryHolder(), ModBlocks.STRIPPED_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.BLUE_MUSHROOM_STEM, ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.ORANGE_MUSHROOM_STEM, ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.PURPLE_MUSHROOM_STEM, ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM);
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY, MobBlockVariant.DIRECT_CODEC);
    }

    @Override
    public void onRegisterBiomeModifications(BiomeModificationsContext context) {
        BiomeModificationsHandler.onRegisterBiomeModifications(context);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
