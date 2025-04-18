package fuzs.shroomcraft;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.*;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.AddBlockEntityTypeBlocksCallback;
import fuzs.puzzleslib.api.event.v1.entity.ServerEntityLevelEvents;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadCallback;
import fuzs.shroomcraft.handler.AxeStrippingHandler;
import fuzs.shroomcraft.handler.BiomeModificationsHandler;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
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
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
        PlayerInteractEvents.USE_BLOCK.register(AxeStrippingHandler::onUseBlock);
        AddBlockEntityTypeBlocksCallback.EVENT.register((BiConsumer<BlockEntityType<?>, Block> consumer) -> {
            ModBlockFamilies.getAllFamilyRegistrars()
                    .mapMulti((BlockFamilyRegistrar registrar, Consumer<Holder.Reference<Block>> blockConsumer) -> {
                        blockConsumer.accept(registrar.getBlock(BlockFamily.Variant.SIGN));
                        blockConsumer.accept(registrar.getBlock(BlockFamily.Variant.WALL_SIGN));
                    })
                    .filter(Objects::nonNull)
                    .map(Holder::value)
                    .forEach((Block block) -> {
                        consumer.accept(BlockEntityType.SIGN, block);
                    });
            ModBlockFamilies.getAllFamilyRegistrars()
                    .mapMulti((BlockFamilyRegistrar registrar, Consumer<Holder.Reference<Block>> blockConsumer) -> {
                        blockConsumer.accept(registrar.hangingSignBlock());
                        blockConsumer.accept(registrar.wallHangingSignBlock());
                    })
                    .filter(Objects::nonNull)
                    .map(Holder::value)
                    .forEach((Block block) -> {
                        consumer.accept(BlockEntityType.HANGING_SIGN, block);
                    });
        });
        ServerEntityLevelEvents.SPAWN.register(ModMushroomCow::onEntitySpawn);
        PlayerInteractEvents.USE_ENTITY.register(ModMushroomCow::onEntityInteract);
        LootTableLoadCallback.EVENT.register((ResourceLocation resourceLocation, LootTable.Builder lootTable, @Nullable HolderLookup.Provider registries) -> {
            if (BuiltInLootTables.FISHING_FISH.location().equals(resourceLocation)) {
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
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            BlockSetType.register(registrar.getBlockSetType());
            WoodType.register(registrar.getWoodType());
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
    }

    @Override
    public void onRegisterDataPackRegistriesContext(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY, MobBlockVariant.DIRECT_CODEC);
    }

    @Override
    public void onRegisterBiomeModifications(BiomeModificationsContext context) {
        BiomeModificationsHandler.onRegisterBiomeModifications(context);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
