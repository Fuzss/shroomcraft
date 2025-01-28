package fuzs.shroomcraft;

import fuzs.puzzleslib.api.biome.v1.BiomeLoadingPhase;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.BiomeModificationsContext;
import fuzs.puzzleslib.api.core.v1.context.EntityAttributesCreateContext;
import fuzs.puzzleslib.api.core.v1.context.SpawnPlacementsContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.AddBlockEntityTypeBlocksCallback;
import fuzs.puzzleslib.api.event.v1.entity.ServerEntityLevelEvents;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadCallback;
import fuzs.shroomcraft.handler.AxeStrippingHandler;
import fuzs.shroomcraft.init.*;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
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
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
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
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.GenerationStep;
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
    }

    @Override
    public void onEntityAttributeCreation(EntityAttributesCreateContext context) {
        context.registerEntityAttributes(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(), Cow.createAttributes());
        context.registerEntityAttributes(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(), AbstractFish.createAttributes());
        context.registerEntityAttributes(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(), Chicken.createAttributes());
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ModMushroomCow::checkCustomMushroomSpawnRules);
        context.registerSpawnPlacement(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        context.registerSpawnPlacement(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Cluckshroom::checkMushroomSpawnRules);
    }

    @Override
    public void onRegisterBiomeModifications(BiomeModificationsContext context) {
        context.register(BiomeLoadingPhase.ADDITIONS,
                biomeLoadingContext -> biomeLoadingContext.is(Biomes.CRIMSON_FOREST),
                biomeModificationContext -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                });
        context.register(BiomeLoadingPhase.ADDITIONS,
                biomeLoadingContext -> biomeLoadingContext.is(Biomes.WARPED_FOREST),
                biomeModificationContext -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                });
        context.register(BiomeLoadingPhase.MODIFICATIONS,
                biomeLoadingContext -> biomeLoadingContext.is(Biomes.MUSHROOM_FIELDS),
                biomeModificationContext -> {
                    biomeModificationContext.generationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    VegetationPlacements.MUSHROOM_ISLAND_VEGETATION);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.MUSHROOM_ISLAND_VEGETATION_PLACED_FEATURE);
                });
        context.register(BiomeLoadingPhase.ADDITIONS,
                biomeLoadingContext -> biomeLoadingContext.is(Biomes.MUSHROOM_FIELDS),
                biomeModificationContext -> {
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.CREATURE,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                                            8,
                                            4,
                                            8));
                    biomeModificationContext.mobSpawnSettings()
                            .addSpawn(MobCategory.WATER_AMBIENT,
                                    new MobSpawnSettings.SpawnerData(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(),
                                            5,
                                            1,
                                            5));
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.MYCELIAL_GROWTH_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.BLUE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.ORANGE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PURPLE_MUSHROOM_NORMAL_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.BLUE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.ORANGE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PURPLE_MUSHROOM_MUSHROOM_FIELDS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_BLUE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_ORANGE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                    biomeModificationContext.generationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                    ModFeatures.PATCH_PURPLE_MUSHROOM_SPROUTS_PLACED_FEATURE);
                });
    }

    @Override
    public ContentRegistrationFlags[] getContentRegistrationFlags() {
        return new ContentRegistrationFlags[]{ContentRegistrationFlags.BIOME_MODIFICATIONS};
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
