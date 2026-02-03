package fuzs.shroomcraft.init.family;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.BlockFamily;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

@SuppressWarnings("unchecked")
public sealed interface BlockSetVariant extends StringRepresentable permits StandaloneBlockSetVariant, VanillaBlockSetVariant {
    BlockSetVariant STAIRS = new VanillaBlockSetVariant(BlockFamily.Variant.STAIRS, BlockFamily.Builder::stairs) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.STAIRS,
                    registries.registerBlock(basePath + "_stairs",
                            (BlockBehaviour.Properties properties) -> new StairBlock(familyRegistrar.baseBlock.value()
                                    .defaultBlockState(), properties),
                            () -> {
                                return BlockBehaviour.Properties.ofLegacyCopy(familyRegistrar.baseBlock.value());
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.STAIRS,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.STAIRS)));
        }
    };
    BlockSetVariant SLAB = new VanillaBlockSetVariant(BlockFamily.Variant.SLAB, BlockFamily.Builder::slab) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.SLAB,
                    registries.registerBlock(basePath + "_slab", SlabBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value());
                    }));
            familyRegistrar.itemVariants.put(BlockSetVariant.SLAB,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.SLAB)));
        }
    };
    BlockSetVariant WALL = new VanillaBlockSetVariant(BlockFamily.Variant.WALL, BlockFamily.Builder::wall) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.WALL,
                    registries.registerBlock(basePath + "_wall", WallBlock::new, () -> {
                        return BlockBehaviour.Properties.ofLegacyCopy(familyRegistrar.baseBlock.value()).forceSolidOn();
                    }));
            familyRegistrar.itemVariants.put(BlockSetVariant.WALL,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.WALL)));
        }
    };
    BlockSetVariant FENCE = new VanillaBlockSetVariant(BlockFamily.Variant.FENCE, BlockFamily.Builder::fence) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.FENCE,
                    registries.registerBlock(basePath + "_fence", FenceBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value());
                    }));
            familyRegistrar.itemVariants.put(BlockSetVariant.FENCE,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.FENCE)));
        }
    };
    BlockSetVariant FENCE_GATE = new VanillaBlockSetVariant(BlockFamily.Variant.FENCE_GATE,
            BlockFamily.Builder::fenceGate) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.FENCE_GATE,
                    registries.registerBlock(basePath + "_fence_gate",
                            (BlockBehaviour.Properties properties) -> new FenceGateBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn();
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.FENCE_GATE,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.FENCE_GATE)));
        }
    };
    BlockSetVariant DOOR = new VanillaBlockSetVariant(BlockFamily.Variant.DOOR, BlockFamily.Builder::door) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.DOOR,
                    registries.registerBlock(basePath + "_door",
                            (BlockBehaviour.Properties properties) -> new DoorBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.DOOR,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.DOOR),
                            DoubleHighBlockItem::new));
        }
    };
    BlockSetVariant TRAPDOOR = new VanillaBlockSetVariant(BlockFamily.Variant.TRAPDOOR, BlockFamily.Builder::trapdoor) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.TRAPDOOR,
                    registries.registerBlock(basePath + "_trapdoor",
                            (BlockBehaviour.Properties properties) -> new TrapDoorBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noOcclusion()
                                        .isValidSpawn(Blocks::never);
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.TRAPDOOR,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.TRAPDOOR)));
        }
    };
    BlockSetVariant BUTTON = new VanillaBlockSetVariant(BlockFamily.Variant.BUTTON, BlockFamily.Builder::button) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.BUTTON,
                    registries.registerBlock(basePath + "_button",
                            (BlockBehaviour.Properties properties) -> new ButtonBlock(familyRegistrar.blockSetType,
                                    30,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.BUTTON,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.BUTTON)));
        }
    };
    BlockSetVariant PRESSURE_PLATE = new VanillaBlockSetVariant(BlockFamily.Variant.PRESSURE_PLATE,
            BlockFamily.Builder::pressurePlate) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.PRESSURE_PLATE,
                    registries.registerBlock(basePath + "_pressure_plate",
                            (BlockBehaviour.Properties properties) -> new PressurePlateBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.PRESSURE_PLATE,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(BlockSetVariant.PRESSURE_PLATE)));
        }
    };
    BlockSetVariant SIGN = new StandaloneBlockSetVariant(BlockFamily.Variant.SIGN) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(BlockSetVariant.SIGN,
                    registries.registerBlock(basePath + "_sign",
                            (BlockBehaviour.Properties properties) -> new StandingSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            Holder<Block> signHolder = familyRegistrar.blockVariants.get(BlockSetVariant.SIGN);
            familyRegistrar.blockVariants.put(BlockSetVariant.WALL_SIGN,
                    registries.registerBlock(basePath + "_wall_sign",
                            (BlockBehaviour.Properties properties) -> new WallSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .overrideLootTable(signHolder.value().getLootTable())
                                        .overrideDescription(signHolder.value().getDescriptionId())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            familyRegistrar.itemVariants.put(BlockSetVariant.SIGN,
                    registries.registerBlockItem(signHolder,
                            (Block block, Item.Properties properties) -> new SignItem(block,
                                    familyRegistrar.blockVariants.get(BlockSetVariant.WALL_SIGN).value(),
                                    properties),
                            () -> new Item.Properties().stacksTo(16)));
        }
    };
    BlockSetVariant WALL_SIGN = new StandaloneBlockSetVariant(BlockFamily.Variant.WALL_SIGN) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            throw new UnsupportedOperationException();
        }
    };
    BlockSetVariant HANGING_SIGN = new StandaloneBlockSetVariant("hanging_sign") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(HANGING_SIGN,
                    registries.registerBlock(basePath + "_hanging_sign",
                            (BlockBehaviour.Properties properties) -> new CeilingHangingSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            Holder<Block> hangingSignHolder = familyRegistrar.blockVariants.get(HANGING_SIGN);
            familyRegistrar.blockVariants.put(WALL_HANGING_SIGN,
                    registries.registerBlock(basePath + "_wall_hanging_sign",
                            (BlockBehaviour.Properties properties) -> new WallHangingSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .overrideLootTable(hangingSignHolder.value().getLootTable())
                                        .overrideDescription(hangingSignHolder.value().getDescriptionId())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            familyRegistrar.itemVariants.put(HANGING_SIGN,
                    registries.registerBlockItem(hangingSignHolder,
                            (Block block, Item.Properties properties) -> new HangingSignItem(block,
                                    familyRegistrar.blockVariants.get(HANGING_SIGN).value(),
                                    properties),
                            () -> new Item.Properties().stacksTo(16)));
        }
    };
    BlockSetVariant WALL_HANGING_SIGN = new StandaloneBlockSetVariant("wall_hanging_sign") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            throw new UnsupportedOperationException();
        }
    };
    BlockSetVariant SHELF = new StandaloneBlockSetVariant("shelf") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(SHELF,
                    registries.registerBlock(basePath + "_shelf", ShelfBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                .sound(SoundType.SHELF);
                    }));
            familyRegistrar.itemVariants.put(SHELF,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(SHELF),
                            () -> new Item.Properties().component(DataComponents.CONTAINER,
                                    ItemContainerContents.EMPTY)));
        }
    };
    BlockSetVariant BOAT = new StandaloneBlockSetVariant("boat") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.entityVariants.put(BOAT,
                    (Holder.Reference<EntityType<?>>) (Holder.Reference<?>) registries.registerEntityType(
                            basePath + "_boat",
                            () -> EntityType.Builder.of((EntityType<Boat> entityType, Level level) -> {
                                        return new Boat(entityType,
                                                level,
                                                () -> familyRegistrar.itemVariants.get(BOAT).value());
                                    }, MobCategory.MISC)
                                    .noLootTable()
                                    .sized(1.375F, 0.5625F)
                                    .eyeHeight(0.5625F)
                                    .clientTrackingRange(10)));
            familyRegistrar.itemVariants.put(BOAT,
                    registries.registerItem(basePath + "_boat",
                            (Item.Properties properties) -> new BoatItem((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                                    BOAT).value(), properties),
                            () -> new Item.Properties().stacksTo(1)));
        }
    };
    BlockSetVariant CHEST_BOAT = new StandaloneBlockSetVariant("chest_boat") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.entityVariants.put(CHEST_BOAT,
                    (Holder.Reference<EntityType<?>>) (Holder.Reference<?>) registries.registerEntityType(
                            basePath + "_chest_boat",
                            () -> EntityType.Builder.of((EntityType<ChestBoat> entityType, Level level) -> {
                                        return new ChestBoat(entityType,
                                                level,
                                                () -> familyRegistrar.itemVariants.get(CHEST_BOAT).value());
                                    }, MobCategory.MISC)
                                    .noLootTable()
                                    .sized(1.375F, 0.5625F)
                                    .eyeHeight(0.5625F)
                                    .clientTrackingRange(10)));
            familyRegistrar.itemVariants.put(CHEST_BOAT,
                    registries.registerItem(basePath + "_chest_boat",
                            (Item.Properties properties) -> new BoatItem((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                                    CHEST_BOAT).value(), properties),
                            () -> new Item.Properties().stacksTo(1)));
        }
    };

    void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath);
}
