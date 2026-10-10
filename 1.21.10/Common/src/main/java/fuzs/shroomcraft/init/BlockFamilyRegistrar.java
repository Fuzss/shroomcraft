package fuzs.shroomcraft.init;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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
    static final Set<BlockFamily.Variant> FLAMMABLE_VARIANTS = ImmutableSet.of(BlockFamily.Variant.CUSTOM_FENCE,
            BlockFamily.Variant.FENCE,
            BlockFamily.Variant.CUSTOM_FENCE_GATE,
            BlockFamily.Variant.FENCE_GATE,
            BlockFamily.Variant.STAIRS,
            BlockFamily.Variant.SLAB);

    final Map<BlockFamily.Variant, Holder.Reference<Block>> blockVariants = new LinkedHashMap<>();
    final Map<BlockFamily.Variant, Holder.Reference<Item>> itemVariants = new LinkedHashMap<>();
    final Holder.Reference<Block> baseBlock;
    final BlockSetType blockSetType;
    final WoodType woodType;
    Holder.Reference<Block> hangingSignBlock;
    Holder.Reference<Block> wallHangingSignBlock;
    Holder.Reference<Item> hangingSignItem;
    Holder.Reference<Block> shelfBlock;
    Holder.Reference<Item> shelfItem;
    Holder.Reference<Item> boatItem;
    Holder.Reference<Item> chestBoatItem;
    Holder.Reference<EntityType<Boat>> boatEntityType;
    Holder.Reference<EntityType<ChestBoat>> chestBoatEntityType;

    BlockFamilyRegistrar(Holder.Reference<Block> baseBlock, BlockSetType blockSetType, WoodType woodType) {
        this.baseBlock = baseBlock;
        this.blockSetType = blockSetType;
        this.woodType = woodType;
    }

    public static Builder builder(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        BlockSetType blockSetType = new BlockSetType(registries.makeKey(basePath).toString());
        WoodType woodType = new WoodType(registries.makeKey(basePath).toString(), blockSetType);
        return new Builder(registries, new BlockFamilyRegistrar(baseBlock, blockSetType, woodType), basePath);
    }

    public static Builder any(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).stairs().slab().wall();
    }

    public static Builder metal(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        Builder builder = builder(registries, baseBlock, basePath);
        return builder.stairs()
                .slab()
                .door(builder.familyRegistrar.blockSetType)
                .trapdoor(builder.familyRegistrar.blockSetType)
                .pressurePlate(builder.familyRegistrar.blockSetType);
    }

    public static Builder wooden(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        Builder builder = builder(registries, baseBlock, basePath);
        return builder.stairs()
                .slab()
                .fence()
                .fenceGate(builder.familyRegistrar.woodType)
                .door(builder.familyRegistrar.blockSetType)
                .trapdoor(builder.familyRegistrar.blockSetType)
                .pressurePlate(builder.familyRegistrar.blockSetType)
                .button(builder.familyRegistrar.blockSetType)
                .sign(builder.familyRegistrar.woodType)
                .hangingSign(builder.familyRegistrar.woodType)
                .shelf()
                .boat();
    }

    public Map<BlockFamily.Variant, Holder.Reference<Block>> getBlockVariants() {
        return this.blockVariants;
    }

    public void forEachBlockVariant(Consumer<Holder.Reference<Block>> consumer) {
        this.blockVariants.values().forEach(consumer);
        consumer.accept(this.hangingSignBlock);
        consumer.accept(this.wallHangingSignBlock);
        consumer.accept(this.shelfBlock);
    }

    /**
     * Shelf is missing from this, must be handled separately.
     */
    public void forEachFlammableVariant(Consumer<Holder.Reference<Block>> consumer) {
        this.blockVariants.forEach((BlockFamily.Variant variant, Holder.Reference<Block> holder) -> {
            if (FLAMMABLE_VARIANTS.contains(variant)) {
                consumer.accept(holder);
            }
        });
    }

    public Map<BlockFamily.Variant, Holder.Reference<Item>> getItemVariants() {
        return this.itemVariants;
    }

    public void forEachItemVariant(Consumer<Holder.Reference<Item>> consumer) {
        this.itemVariants.values().forEach(consumer);
        consumer.accept(this.hangingSignItem);
        consumer.accept(this.shelfItem);
        consumer.accept(this.boatItem);
        consumer.accept(this.chestBoatItem);
    }

    public void forEachEntityType(Consumer<Holder.Reference<? extends EntityType<?>>> consumer) {
        consumer.accept(this.boatEntityType);
        consumer.accept(this.chestBoatEntityType);
    }

    public Holder.Reference<Block> getBlock(BlockFamily.Variant variant) {
        return this.blockVariants.get(variant);
    }

    public Holder.Reference<Item> getItem(BlockFamily.Variant variant) {
        return this.itemVariants.get(variant);
    }

    public Holder.Reference<Block> getBaseBlock() {
        return this.baseBlock;
    }

    public BlockSetType getBlockSetType() {
        return this.blockSetType;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    public Holder.Reference<Block> hangingSignBlock() {
        return this.hangingSignBlock;
    }

    public Holder.Reference<Block> wallHangingSignBlock() {
        return this.wallHangingSignBlock;
    }

    public Holder.Reference<Item> hangingSignItem() {
        return this.hangingSignItem;
    }

    public Holder.Reference<Block> shelfBlock() {
        return this.shelfBlock;
    }

    public Holder.Reference<Item> shelfItem() {
        return this.shelfItem;
    }

    public Holder.Reference<Item> boatItem() {
        return this.boatItem;
    }

    public Holder.Reference<Item> chestBoatItem() {
        return this.chestBoatItem;
    }

    public Holder.Reference<EntityType<Boat>> boatEntityType() {
        return this.boatEntityType;
    }

    public Holder.Reference<EntityType<ChestBoat>> chestBoatEntityType() {
        return this.chestBoatEntityType;
    }

    public BlockFamily.Builder getFamily() {
        BlockFamily.Builder builder = new BlockFamily.Builder(this.baseBlock.value());
        VARIANT_BUILDERS.forEach((BlockFamily.Variant variant, BiFunction<BlockFamily.Builder, Block, BlockFamily.Builder> function) -> {
            if (this.blockVariants.containsKey(variant)) {
                function.apply(builder, this.blockVariants.get(variant).value());
            }
        });
        if (this.blockVariants.containsKey(BlockFamily.Variant.SIGN)
                && this.blockVariants.containsKey(BlockFamily.Variant.WALL_SIGN)) {
            builder.sign(this.blockVariants.get(BlockFamily.Variant.SIGN).value(),
                    this.blockVariants.get(BlockFamily.Variant.WALL_SIGN).value());
        }

        return builder;
    }

    public BlockFamily getWoodenFamily() {
        return this.getFamily().recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    }

    public static class Builder {
        final RegistryManager registries;
        final BlockFamilyRegistrar familyRegistrar;
        final String basePath;

        public Builder(RegistryManager registries, BlockFamilyRegistrar familyRegistrar, String basePath) {
            this.registries = registries;
            this.familyRegistrar = familyRegistrar;
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
                            DoubleHighBlockItem::new));
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

        public Builder pressurePlate(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.PRESSURE_PLATE,
                    this.registries.registerBlock(this.basePath() + "_pressure_plate",
                            (BlockBehaviour.Properties properties) -> new PressurePlateBlock(blockSetType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .forceSolidOn()
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.PRESSURE_PLATE,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.PRESSURE_PLATE)));
            return this;
        }

        public Builder button(BlockSetType blockSetType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.BUTTON,
                    this.registries.registerBlock(this.basePath() + "_button",
                            (BlockBehaviour.Properties properties) -> new ButtonBlock(blockSetType, 30, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .noCollision()
                                        .pushReaction(PushReaction.DESTROY);
                            }));
            this.familyRegistrar.itemVariants.put(BlockFamily.Variant.BUTTON,
                    this.registries.registerBlockItem(this.familyRegistrar.blockVariants.get(BlockFamily.Variant.BUTTON)));
            return this;
        }

        public Builder sign(WoodType woodType) {
            this.familyRegistrar.blockVariants.put(BlockFamily.Variant.SIGN,
                    this.registries.registerBlock(this.basePath() + "_sign",
                            (BlockBehaviour.Properties properties) -> new StandingSignBlock(woodType, properties),
                            () -> {
                                return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                        .forceSolidOn()
                                        .noCollision();
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
                                        .noCollision();
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

        public Builder hangingSign(WoodType woodType) {
            this.familyRegistrar.hangingSignBlock = this.registries.registerBlock(this.basePath() + "_hanging_sign",
                    (BlockBehaviour.Properties properties) -> new CeilingHangingSignBlock(woodType, properties),
                    () -> {
                        return BlockBehaviour.Properties.ofFullCopy(this.baseBlock()).forceSolidOn().noCollision();
                    });
            Holder<Block> hangingSignHolder = this.familyRegistrar.hangingSignBlock;
            this.familyRegistrar.wallHangingSignBlock = this.registries.registerBlock(
                    this.basePath() + "_wall_hanging_sign",
                    (BlockBehaviour.Properties properties) -> new WallHangingSignBlock(woodType, properties),
                    () -> {
                        return BlockBehaviour.Properties.ofFullCopy(this.baseBlock())
                                .overrideLootTable(hangingSignHolder.value().getLootTable())
                                .overrideDescription(hangingSignHolder.value().getDescriptionId())
                                .forceSolidOn()
                                .noCollision();
                    });
            Holder<Block> wallHangingSignHolder = this.familyRegistrar.wallHangingSignBlock;
            this.familyRegistrar.hangingSignItem = this.registries.registerBlockItem(hangingSignHolder,
                    (Block block, Item.Properties properties) -> new HangingSignItem(block,
                            wallHangingSignHolder.value(),
                            properties),
                    () -> new Item.Properties().stacksTo(16));
            return this;
        }

        public Builder shelf() {
            this.familyRegistrar.shelfBlock = this.registries.registerBlock(this.basePath() + "_shelf",
                    ShelfBlock::new,
                    () -> {
                        return BlockBehaviour.Properties.ofFullCopy(this.baseBlock()).sound(SoundType.SHELF);
                    });
            this.familyRegistrar.shelfItem = this.registries.registerBlockItem(this.familyRegistrar.shelfBlock,
                    () -> new Item.Properties().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
            return this;
        }

        public Builder boat() {
            this.familyRegistrar.boatEntityType = this.registries.registerEntityType(this.basePath() + "_boat",
                    () -> EntityType.Builder.of((EntityType<Boat> entityType, Level level) -> {
                                return new Boat(entityType, level, () -> this.familyRegistrar.boatItem.value());
                            }, MobCategory.MISC)
                            .noLootTable()
                            .sized(1.375F, 0.5625F)
                            .eyeHeight(0.5625F)
                            .clientTrackingRange(10));
            this.familyRegistrar.chestBoatEntityType = this.registries.registerEntityType(
                    this.basePath() + "_chest_boat",
                    () -> EntityType.Builder.of((EntityType<ChestBoat> entityType, Level level) -> {
                                return new ChestBoat(entityType, level, () -> this.familyRegistrar.chestBoatItem.value());
                            }, MobCategory.MISC)
                            .noLootTable()
                            .sized(1.375F, 0.5625F)
                            .eyeHeight(0.5625F)
                            .clientTrackingRange(10));
            this.familyRegistrar.boatItem = this.registries.registerItem(this.basePath() + "_boat",
                    (Item.Properties properties) -> new BoatItem(this.familyRegistrar.boatEntityType.value(),
                            properties),
                    () -> new Item.Properties().stacksTo(1));
            this.familyRegistrar.chestBoatItem = this.registries.registerItem(this.basePath() + "_chest_boat",
                    (Item.Properties properties) -> new BoatItem(this.familyRegistrar.chestBoatEntityType.value(),
                            properties),
                    () -> new Item.Properties().stacksTo(1));
            return this;
        }
    }
}
