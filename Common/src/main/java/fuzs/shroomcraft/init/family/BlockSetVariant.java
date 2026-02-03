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
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_stairs",
                            (BlockBehaviour.Properties properties) -> new StairBlock(familyRegistrar.baseBlock.value()
                                    .defaultBlockState(), properties),
                            () -> {
                                return BlockBehaviour.Properties.ofLegacyCopy(familyRegistrar.baseBlock.value());
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant SLAB = new VanillaBlockSetVariant(BlockFamily.Variant.SLAB, BlockFamily.Builder::slab) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this, registries.registerBlock(basePath + "_slab", SlabBlock::new, () -> {
                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value());
            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant WALL = new VanillaBlockSetVariant(BlockFamily.Variant.WALL, BlockFamily.Builder::wall) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this, registries.registerBlock(basePath + "_wall", WallBlock::new, () -> {
                return BlockBehaviour.Properties.ofLegacyCopy(familyRegistrar.baseBlock.value()).forceSolidOn();
            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant FENCE = new VanillaBlockSetVariant(BlockFamily.Variant.FENCE, BlockFamily.Builder::fence) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_fence", FenceBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value());
                    }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant FENCE_GATE = new VanillaBlockSetVariant(BlockFamily.Variant.FENCE_GATE,
            BlockFamily.Builder::fenceGate) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_fence_gate",
                            (BlockBehaviour.Properties properties) -> new FenceGateBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn();
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant DOOR = new VanillaBlockSetVariant(BlockFamily.Variant.DOOR, BlockFamily.Builder::door) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_door",
                            (BlockBehaviour.Properties properties) -> new DoorBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this), DoubleHighBlockItem::new));
        }
    };
    BlockSetVariant TRAPDOOR = new VanillaBlockSetVariant(BlockFamily.Variant.TRAPDOOR, BlockFamily.Builder::trapdoor) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_trapdoor",
                            (BlockBehaviour.Properties properties) -> new TrapDoorBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noOcclusion()
                                        .isValidSpawn(Blocks::never);
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant BUTTON = new VanillaBlockSetVariant(BlockFamily.Variant.BUTTON, BlockFamily.Builder::button) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_button",
                            (BlockBehaviour.Properties properties) -> new ButtonBlock(familyRegistrar.blockSetType,
                                    30,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant PRESSURE_PLATE = new VanillaBlockSetVariant(BlockFamily.Variant.PRESSURE_PLATE,
            BlockFamily.Builder::pressurePlate) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_pressure_plate",
                            (BlockBehaviour.Properties properties) -> new PressurePlateBlock(familyRegistrar.blockSetType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this)));
        }
    };
    BlockSetVariant SIGN = new StandaloneBlockSetVariant(BlockFamily.Variant.SIGN) {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_sign",
                            (BlockBehaviour.Properties properties) -> new StandingSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            Holder<Block> signHolder = familyRegistrar.blockVariants.get(this);
            familyRegistrar.blockVariants.put(WALL_SIGN,
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
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(signHolder,
                            (Block block, Item.Properties properties) -> new SignItem(block,
                                    familyRegistrar.blockVariants.get(WALL_SIGN).value(),
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
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_hanging_sign",
                            (BlockBehaviour.Properties properties) -> new CeilingHangingSignBlock(familyRegistrar.woodType,
                                    properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                        .forceSolidOn()
                                        .noCollision();
                            }));
            Holder<Block> hangingSignHolder = familyRegistrar.blockVariants.get(this);
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
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(hangingSignHolder,
                            (Block block, Item.Properties properties) -> new HangingSignItem(block,
                                    familyRegistrar.blockVariants.get(this).value(),
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
            familyRegistrar.blockVariants.put(this,
                    registries.registerBlock(basePath + "_shelf", ShelfBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(familyRegistrar.baseBlock.value())
                                .sound(SoundType.SHELF);
                    }));
            familyRegistrar.itemVariants.put(this,
                    registries.registerBlockItem(familyRegistrar.blockVariants.get(this),
                            () -> new Item.Properties().component(DataComponents.CONTAINER,
                                    ItemContainerContents.EMPTY)));
        }
    };
    BlockSetVariant BOAT = new StandaloneBlockSetVariant("boat") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.entityVariants.put(this,
                    (Holder.Reference<EntityType<?>>) (Holder.Reference<?>) registries.registerEntityType(
                            basePath + "_boat",
                            () -> EntityType.Builder.of((EntityType<Boat> entityType, Level level) -> {
                                        return new Boat(entityType,
                                                level,
                                                () -> familyRegistrar.itemVariants.get(this).value());
                                    }, MobCategory.MISC)
                                    .noLootTable()
                                    .sized(1.375F, 0.5625F)
                                    .eyeHeight(0.5625F)
                                    .clientTrackingRange(10)));
            familyRegistrar.itemVariants.put(this,
                    registries.registerItem(basePath + "_boat",
                            (Item.Properties properties) -> new BoatItem((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                                    this).value(), properties),
                            () -> new Item.Properties().stacksTo(1)));
        }
    };
    BlockSetVariant CHEST_BOAT = new StandaloneBlockSetVariant("chest_boat") {
        @Override
        public void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            familyRegistrar.entityVariants.put(this,
                    (Holder.Reference<EntityType<?>>) (Holder.Reference<?>) registries.registerEntityType(
                            basePath + "_chest_boat",
                            () -> EntityType.Builder.of((EntityType<ChestBoat> entityType, Level level) -> {
                                        return new ChestBoat(entityType,
                                                level,
                                                () -> familyRegistrar.itemVariants.get(this).value());
                                    }, MobCategory.MISC)
                                    .noLootTable()
                                    .sized(1.375F, 0.5625F)
                                    .eyeHeight(0.5625F)
                                    .clientTrackingRange(10)));
            familyRegistrar.itemVariants.put(this,
                    registries.registerItem(basePath + "_chest_boat",
                            (Item.Properties properties) -> new BoatItem((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                                    this).value(), properties),
                            () -> new Item.Properties().stacksTo(1)));
        }
    };

    void generateFor(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath);
}
