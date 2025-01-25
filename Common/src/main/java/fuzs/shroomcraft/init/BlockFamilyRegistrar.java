package fuzs.shroomcraft.init;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class BlockFamilyRegistrar {
    static final Map<BlockFamily.Variant, BiFunction<BlockFamily.Builder, Block, BlockFamily.Builder>> VARIANT_BUILDERS = ImmutableMap.<BlockFamily.Variant, BiFunction<BlockFamily.Builder, Block, BlockFamily.Builder>>builder()
            .put(BlockFamily.Variant.STAIRS, BlockFamily.Builder::stairs)
            .put(BlockFamily.Variant.SLAB, BlockFamily.Builder::slab)
            .put(BlockFamily.Variant.WALL, BlockFamily.Builder::wall)
            .put(BlockFamily.Variant.FENCE, BlockFamily.Builder::fence)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockFamily.Builder::customFence)
            .put(BlockFamily.Variant.FENCE_GATE, BlockFamily.Builder::fenceGate)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockFamily.Builder::customFenceGate)
            .put(BlockFamily.Variant.DOOR, BlockFamily.Builder::door)
            .put(BlockFamily.Variant.TRAPDOOR, BlockFamily.Builder::trapdoor)
            .put(BlockFamily.Variant.BUTTON, BlockFamily.Builder::button)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockFamily.Builder::pressurePlate)
            .build();

    final Holder.Reference<Block> baseBlock;
    final Map<BlockFamily.Variant, Holder.Reference<Block>> blockVariants = new HashMap<>();
    final Map<BlockFamily.Variant, Holder.Reference<Item>> itemVariants = new HashMap<>();

    BlockFamilyRegistrar(Holder.Reference<Block> baseBlock) {
        this.baseBlock = baseBlock;
    }

    public static Builder any(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return new Builder(registries, baseBlock, basePath).stairs().slab().wall();
    }

    public static Builder metal(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath, BlockSetType blockSetType) {
        return new Builder(registries, baseBlock, basePath).stairs()
                .slab()
                .door(blockSetType)
                .trapdoor(blockSetType)
                .pressurePlate(blockSetType);
    }

    public static Builder wooden(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath, WoodType woodType) {
        return new Builder(registries, baseBlock, basePath).stairs()
                .slab()
                .fence()
                .fenceGate(woodType)
                .door(woodType.setType())
                .trapdoor(woodType.setType())
                .button(woodType.setType())
                .pressurePlate(woodType.setType())
                .sign(woodType);
    }

    public BlockFamily.Builder getFamily() {
        BlockFamily.Builder builder = new BlockFamily.Builder(this.baseBlock.value());
        VARIANT_BUILDERS.forEach((BlockFamily.Variant variant, BiFunction<BlockFamily.Builder, Block, BlockFamily.Builder> function) -> {
            if (this.blockVariants.containsKey(variant)) {
                function.apply(builder, this.blockVariants.get(variant).value());
            }
        });
        if (this.blockVariants.containsKey(BlockFamily.Variant.SIGN) &&
                this.blockVariants.containsKey(BlockFamily.Variant.WALL_SIGN)) {
            builder.sign(this.blockVariants.get(BlockFamily.Variant.SIGN).value(),
                    this.blockVariants.get(BlockFamily.Variant.WALL_SIGN).value());
        }
        return builder;
    }

    public BlockFamily.Builder getWoodenFamily() {
        return this.getFamily().recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks");
    }

    public static class Builder {
        final RegistryManager registries;
        final BlockFamilyRegistrar familyRegistrar;
        final String basePath;

        public Builder(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
            this.registries = registries;
            this.familyRegistrar = new BlockFamilyRegistrar(baseBlock);
            this.basePath = basePath;
        }

        public BlockFamilyRegistrar getFamily() {
            return this.familyRegistrar;
        }

        private Block baseBlock() {
            return this.familyRegistrar.baseBlock.value();
        }

        private String basePath() {
            return this.basePath;
        }

        public Builder stairs() {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.STAIRS,
                    this.registries.registerBlock(this.basePath() + "_stairs",
                            (BlockBehaviour.Properties properties) -> new StairBlock(this.baseBlock()
                                    .defaultBlockState(), properties),
                            () -> {
                                return BlockBehaviour.Properties.ofLegacyCopy(this.baseBlock());
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.STAIRS,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.STAIRS)));
            return this;
        }

        public Builder slab() {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.SLAB,
                    this.registries.registerBlock(this.basePath() + "_slab", SlabBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(this.baseBlock());
                    }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.SLAB,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.SLAB)));
            return this;
        }

        public Builder wall() {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.WALL,
                    this.registries.registerBlock(this.basePath() + "_wall", WallBlock::new, () -> {
                        return BlockBehaviour.Properties.ofLegacyCopy(this.baseBlock()).forceSolidOn();
                    }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.WALL,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.WALL)));
            return this;
        }

        public Builder fence() {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.FENCE,
                    this.registries.registerBlock(this.basePath() + "_fence", FenceBlock::new, () -> {
                        return BlockBehaviour.Properties.ofFullCopy(this.baseBlock());
                    }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.FENCE,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.FENCE)));
            return this;
        }

        public Builder fenceGate(WoodType woodType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.FENCE_GATE,
                    this.registries.registerBlock(this.basePath() + "_fence_gate",
                            (BlockBehaviour.Properties properties) -> new FenceGateBlock(woodType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock()).forceSolidOn();
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.FENCE_GATE,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.FENCE_GATE)));
            return this;
        }

        public Builder door(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.DOOR,
                    this.registries.registerBlock(this.basePath() + "_door",
                            (BlockBehaviour.Properties properties) -> new DoorBlock(blockSetType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.DOOR,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.DOOR),
                            DoubleHighBlockItem::new,
                            Item.Properties::new));
            return this;
        }

        public Builder trapdoor(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.TRAPDOOR,
                    this.registries.registerBlock(this.basePath() + "_trapdoor",
                            (BlockBehaviour.Properties properties) -> new TrapDoorBlock(blockSetType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .noOcclusion()
                                        .isValidSpawn(Blocks::never);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.TRAPDOOR,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.TRAPDOOR)));
            return this;
        }

        public Builder button(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.BUTTON,
                    this.registries.registerBlock(this.basePath() + "_button",
                            (BlockBehaviour.Properties properties) -> new ButtonBlock(blockSetType, 30, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .noCollission()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.BUTTON,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.BUTTON)));
            return this;
        }

        public Builder pressurePlate(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.PRESSURE_PLATE,
                    this.registries.registerBlock(this.basePath() + "_pressure_plate",
                            (BlockBehaviour.Properties properties) -> new PressurePlateBlock(blockSetType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .forceSolidOn()
                                        .noCollission()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.PRESSURE_PLATE,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.PRESSURE_PLATE)));
            return this;
        }

        public Builder sign(WoodType woodType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.SIGN,
                    this.registries.registerBlock(this.basePath() + "_sign",
                            (BlockBehaviour.Properties properties1) -> new StandingSignBlock(woodType, properties1),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .forceSolidOn()
                                        .noCollission();
                            }));
            Holder<Block> signHolder = this.familyRegistrar.blockVariants.get(BlockFamily.Variant.SIGN);
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.WALL_SIGN,
                    this.registries.registerBlock(this.basePath() + "_wall_sign",
                            (BlockBehaviour.Properties properties) -> new WallSignBlock(woodType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .overrideLootTable(signHolder.value().getLootTable())
                                        .overrideDescription(signHolder.value().getDescriptionId())
                                        .forceSolidOn()
                                        .noCollission();
                            }));
            Holder<Block> wallSignHolder = this.familyRegistrar.blockVariants.get(BlockFamily.Variant.WALL_SIGN);
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.SIGN,
                    this.registries.registerBlockItem(signHolder,
                            (Block block, Item.Properties properties) -> new SignItem(block,
                                    wallSignHolder.value(),
                                    properties),
                            () -> new Item.Properties().stacksTo(16)));
            return this;
        }

        public Builder boat() {
            Holder<Item>[] boatItemHolder = (Holder<Item>[]) Array.newInstance(Holder.class, 1);
            Holder<Item>[] chestBoatItemHolder = (Holder<Item>[]) Array.newInstance(Holder.class, 1);
            Holder<EntityType<Boat>> boatEntityTypeHolder = this.registries.registerEntityType(
                    this.basePath() + "_boat",
                    () -> EntityType.Builder.of((EntityType<Boat> entityType, Level level) -> {
                                return new Boat(entityType, level, () -> boatItemHolder[0].value());
                            }, MobCategory.MISC)
                            .noLootTable()
                            .sized(1.375F, 0.5625F)
                            .eyeHeight(0.5625F)
                            .clientTrackingRange(10));
            Holder<EntityType<ChestBoat>> chestBoatEntityTypeHolder = this.registries.registerEntityType(
                    this.basePath() + "_boat",
                    () -> EntityType.Builder.of((EntityType<ChestBoat> entityType, Level level) -> {
                                return new ChestBoat(entityType, level, () -> chestBoatItemHolder[0].value());
                            }, MobCategory.MISC)
                            .noLootTable()
                            .sized(1.375F, 0.5625F)
                            .eyeHeight(0.5625F)
                            .clientTrackingRange(10));
            boatItemHolder[0] = this.registries.registerItem(this.basePath() + "_chest_boat",
                    (Item.Properties properties) -> new BoatItem(boatEntityTypeHolder.value(), properties),
                    () -> new Item.Properties().stacksTo(1));
            chestBoatItemHolder[0] = this.registries.registerItem(this.basePath() + "_chest_boat",
                    (Item.Properties properties) -> new BoatItem(chestBoatEntityTypeHolder.value(), properties),
                    () -> new Item.Properties().stacksTo(1));
            return this;
        }
    }
}
