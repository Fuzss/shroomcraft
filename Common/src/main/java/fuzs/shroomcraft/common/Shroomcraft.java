package fuzs.shroomcraft.common;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.*;
import fuzs.puzzleslib.common.api.event.v1.AddBlockEntityTypeBlocksCallback;
import fuzs.puzzleslib.common.api.event.v1.entity.ServerEntityEvents;
import fuzs.puzzleslib.common.api.event.v1.server.LootTableLoadCallback;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.shroomcraft.common.handler.BiomeModificationsHandler;
import fuzs.shroomcraft.common.init.*;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import fuzs.shroomcraft.common.world.entity.animal.chicken.Cluckshroom;
import fuzs.shroomcraft.common.world.entity.animal.cow.Mooshroom;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
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
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
                blockSetFamily.registerFor(consumer, BlockSetFamily.VARIANT_BLOCK_ENTITY_TYPE);
            });
        });
        ServerEntityEvents.LOAD.register(Mooshroom::onEntityLoad);
        LootTableLoadCallback.EVENT.register((Identifier identifier, LootTable.Builder lootTable, HolderGetter.@Nullable Provider registries) -> {
            if (BuiltInLootTables.FISHING_FISH.identifier().equals(identifier)) {
                MutableBoolean mutableBoolean = new MutableBoolean();
                LootTableLoadCallback.forEachPool(lootTable, (LootPool.Builder builder) -> {
                    if (mutableBoolean.isFalse()) {
                        mutableBoolean.setTrue();
                        UniformContainerBase.Builder<?> entriesBuilder = LootItem.lootTableItem(ModItems.SHROOMFIN.value())
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
            blockSetFamily.register();
            blockSetFamily.registerFor(BlockSetFamily.VARIANT_DISPENSE_BEHAVIOR);
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
        context.registerAttributes(ModEntityTypes.MOOSHROOM.value(), Cow.createAttributes());
        context.registerAttributes(ModEntityTypes.SHROOMFIN.value(), AbstractFish.createAttributes());
        context.registerAttributes(ModEntityTypes.CLUCKSHROOM.value(), Chicken.createAttributes());
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModEntityTypes.MOOSHROOM.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mooshroom::checkMooshroomSpawnRules);
        context.registerSpawnPlacement(ModEntityTypes.SHROOMFIN.value(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        context.registerSpawnPlacement(ModEntityTypes.CLUCKSHROOM.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Cluckshroom::checkCluckshroomSpawnRules);
    }

    @Override
    public void onRegisterGameplayContent(GameplayContentContext context) {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            context.registerFuel(blockSetFamily.getBaseBlock(), ContextIntProviders.COOKING_TIME_WOOD_BLOCKS);
            context.registerFlammable(blockSetFamily.getBaseBlock(), 5, 20);
            // We provide the stem blocks as logs, but the vanilla variants are fire-resistant, so we retain that behavior.
            blockSetFamily.registerFor(context,
                    filterVariants(BlockSetFamily.VARIANT_WOODEN_COOKING_TIME, Set.of(BlockSetVariant.LOG)),
                    filterVariants(BlockSetFamily.VARIANT_WOODEN_FLAMMABLE, Set.of(BlockSetVariant.LOG)));
        });
        context.registerStrippable(Blocks.MUSHROOM_STEM.builtInRegistryHolder(), ModBlocks.STRIPPED_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.BLUE_MUSHROOM_STEM, ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.ORANGE_MUSHROOM_STEM, ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM);
        context.registerStrippable(ModBlocks.PURPLE_MUSHROOM_STEM, ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM);
    }

    private static <T> Map<BlockSetVariant, T> filterVariants(Map<BlockSetVariant, T> values, Set<BlockSetVariant> skippedVariants) {
        return values.entrySet().stream().filter((Map.Entry<BlockSetVariant, T> entry) -> {
            return !skippedVariants.contains(entry.getKey());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(CluckshroomVariants.REGISTRY_KEY, MobVariant.DIRECT_CODEC);
        context.registerSyncedRegistry(MooshroomVariants.REGISTRY_KEY, MobVariant.DIRECT_CODEC);
    }

    @Override
    public void onRegisterBiomeTransformations(BiomeTransformationsContext context) {
        BiomeModificationsHandler.onRegisterBiomeTransformations(context);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
