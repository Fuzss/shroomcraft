package fuzs.shroomcraft.world.entity.animal;

import fuzs.puzzleslib.api.event.v1.core.EventResult;
import fuzs.puzzleslib.api.event.v1.core.EventResultHolder;
import fuzs.puzzleslib.api.network.v4.codec.ExtraStreamCodecs;
import fuzs.puzzleslib.api.util.v1.EntityHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModLootTables;
import fuzs.shroomcraft.init.ModRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;

public class ModMushroomCow extends MushroomCow {
    private static final EntityDataAccessor<ColorVariant> DATA_VARIANT_ID = SynchedEntityData.defineId(ModMushroomCow.class,
            ModRegistry.MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER.value());
    private static final Set<EntitySpawnReason> VALID_SPAWN_REASONS = Set.of(EntitySpawnReason.SPAWNER,
            EntitySpawnReason.TRIAL_SPAWNER,
            EntitySpawnReason.SPAWN_ITEM_USE,
            EntitySpawnReason.DISPENSER);

    @Nullable
    private UUID lastLightningBoltUUID;

    public ModMushroomCow(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkMooshroomSpawnRules(EntityType<? extends MushroomCow> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkMushroomSpawnRules((EntityType<MushroomCow>) entityType, level, spawnReason, pos, random)
                || level.getBlockState(pos.below()).is(BlockTags.NYLIUM);
    }

    public static EventResult onEntityLoad(Entity entity, ServerLevel serverLevel, boolean isNewlySpawned) {
        @Nullable EntitySpawnReason entitySpawnReason = EntityHelper.getMobSpawnReason(entity);
        if (isNewlySpawned && entitySpawnReason != null && entity.getType() == EntityType.MOOSHROOM
                && VALID_SPAWN_REASONS.contains(entitySpawnReason) && getSpawnAsCustomEntityOdds(serverLevel,
                entity.blockPosition(),
                serverLevel.getRandom())) {
            MushroomCow mushroomCow = (MushroomCow) entity;

            mushroomCow.convertTo(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(),
                    ConversionParams.single(mushroomCow, true, true),
                    mob -> {
                        DifficultyInstance difficulty = new DifficultyInstance(serverLevel.getDifficulty(),
                                serverLevel.getDayTime(),
                                0L,
                                serverLevel.getMoonBrightness());
                        mob.finalizeSpawn(serverLevel, difficulty, entitySpawnReason, null);
                    });

            return EventResult.INTERRUPT;
        }

        return EventResult.PASS;
    }

    public static boolean getSpawnAsCustomEntityOdds(ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.getBiome(blockPos).is(BiomeTags.IS_NETHER)) {
            return true;
        } else {
            return randomSource.nextInt(ColorVariant.getOverworldVariants().length + 1) != 0;
        }
    }

    public static EventResultHolder<InteractionResult> onEntityInteract(Player player, Level level, InteractionHand interactionHand, Entity entity) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (itemInHand.is(Items.MOOSHROOM_SPAWN_EGG) && entity.isAlive()
                && entity.getType() == ModRegistry.MOOSHROOM_ENTITY_TYPE.value()) {
            if (level instanceof ServerLevel serverLevel) {
                Optional<Mob> optional = spawnOffspringFromSpawnEgg(player,
                        (Mob) entity,
                        serverLevel,
                        entity.position(),
                        itemInHand);
                optional.ifPresent(mob -> ((ModMushroomCow) entity).onOffspringSpawnedFromEgg(player, mob));
                if (optional.isEmpty()) {
                    return EventResultHolder.interrupt(InteractionResult.PASS);
                } else {
                    return EventResultHolder.interrupt(InteractionResult.CONSUME);
                }
            } else {
                return EventResultHolder.interrupt(InteractionResult.SUCCESS);
            }
        }

        return EventResultHolder.pass();
    }

    private static Optional<Mob> spawnOffspringFromSpawnEgg(Player player, Mob mob, ServerLevel serverLevel, Vec3 pos, ItemStack stack) {
        Mob mob2;
        if (mob instanceof AgeableMob) {
            mob2 = ((AgeableMob) mob).getBreedOffspring(serverLevel, (AgeableMob) mob);
        } else {
            mob2 = null;
        }

        if (mob2 == null) {
            return Optional.empty();
        } else {
            mob2.setBaby(true);
            if (!mob2.isBaby()) {
                return Optional.empty();
            } else {
                mob2.snapTo(pos.x(), pos.y(), pos.z(), 0.0F, 0.0F);
                serverLevel.addFreshEntityWithPassengers(mob2);
                mob2.setCustomName(stack.get(DataComponents.CUSTOM_NAME));
                stack.consume(1, player);
                return Optional.of(mob2);
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // allows for breeding with normal mooshrooms, but only one-sided,
        // the vanilla mooshroom will not behave properly though due to the implementation of Mooshroom::canMate
        this.goalSelector.removeAllGoals(BreedGoal.class::isInstance);
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, MushroomCow.class));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT_ID, ColorVariant.BLUE);
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            ColorVariant[] colorVariants = this.getColorVariant().isNetherVariant() ? ColorVariant.getNetherVariants() :
                    ColorVariant.getOverworldVariants();
            this.setColorVariant(colorVariants[(this.getColorVariant().typeIndex + 1) % colorVariants.length]);
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        ColorVariant variant;
        if (spawnGroupData instanceof MooshroomGroupData mooshroomGroupData) {
            variant = mooshroomGroupData.variant;
        } else {
            variant = getRandomVariant(level, this.blockPosition());
            spawnGroupData = new MooshroomGroupData(variant);
        }

        this.setColorVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    private static ColorVariant getRandomVariant(LevelAccessor level, BlockPos pos) {
        Holder<Biome> holder = level.getBiome(pos);
        if (holder.is(Biomes.CRIMSON_FOREST)) {
            return ColorVariant.CRIMSON;
        } else if (holder.is(Biomes.WARPED_FOREST)) {
            return ColorVariant.WARPED;
        } else {
            ColorVariant[] colorVariants;
            if (holder.is(BiomeTags.IS_NETHER)) {
                colorVariants = ColorVariant.getNetherVariants();
            } else {
                colorVariants = ColorVariant.getOverworldVariants();
            }
            return Util.getRandom(colorVariants, level.getRandom());
        }
    }

    @Override
    protected void dropFromShearingLootTable(ServerLevel level, ResourceKey<LootTable> lootTable, ItemStack shears, BiConsumer<ServerLevel, ItemStack> dropConsumer) {
        super.dropFromShearingLootTable(level,
                lootTable == BuiltInLootTables.SHEAR_MOOSHROOM ? ModLootTables.SHEAR_MOOSHROOM_LOOT_TABLE : lootTable,
                shears,
                dropConsumer);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.store(Shroomcraft.id("variant").toString(), ColorVariant.CODEC, this.getColorVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        valueInput.read(Shroomcraft.id("variant").toString(), ColorVariant.CODEC).ifPresent(this::setColorVariant);
    }

    @Override
    public MushroomCow.Variant getVariant() {
        return MushroomCow.Variant.RED;
    }

    public void setColorVariant(ColorVariant colorVariant) {
        this.entityData.set(DATA_VARIANT_ID, colorVariant);
    }

    public ColorVariant getColorVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> dataComponentType) {
        return dataComponentType == ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value() ?
                castComponentValue((DataComponentType<T>) dataComponentType, this.getColorVariant()) :
                super.get(dataComponentType);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter dataComponentGetter) {
        this.applyImplicitComponentIfPresent(dataComponentGetter,
                ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value());
        super.applyImplicitComponents(dataComponentGetter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> dataComponentType, T object) {
        if (dataComponentType == ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value()) {
            this.setColorVariant(castComponentValue(ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(), object));
            return true;
        } else {
            return super.applyImplicitComponent(dataComponentType, object);
        }
    }

    @Nullable
    @Override
    public MushroomCow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        ModMushroomCow modMushroomCow = (ModMushroomCow) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (modMushroomCow != null) {
            modMushroomCow.setColorVariant(this.getColorVariant());
        }

        return modMushroomCow;
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        return new ItemStack(Items.MOOSHROOM_SPAWN_EGG);
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else {
            return otherAnimal instanceof MushroomCow && this.isInLove() && otherAnimal.isInLove();
        }
    }

    static class MooshroomGroupData extends AgeableMob.AgeableMobGroupData {
        public final ColorVariant variant;

        MooshroomGroupData(ColorVariant variant) {
            super(true);
            this.variant = variant;
        }
    }

    public enum ColorVariant implements StringRepresentable {
        BLUE(0, ModBlocks.BLUE_MUSHROOM),
        ORANGE(1, ModBlocks.ORANGE_MUSHROOM),
        PURPLE(2, ModBlocks.PURPLE_MUSHROOM),
        CRIMSON(0, Blocks.CRIMSON_FUNGUS.builtInRegistryHolder()),
        WARPED(1, Blocks.WARPED_FUNGUS.builtInRegistryHolder());

        public static final StringRepresentable.StringRepresentableCodec<ColorVariant> CODEC = StringRepresentable.fromEnum(
                ColorVariant::values);
        public static final StreamCodec<ByteBuf, ColorVariant> STREAM_CODEC = ExtraStreamCodecs.fromEnum(ColorVariant.class);

        private final int typeIndex;
        public final Holder<Block> block;

        ColorVariant(int typeIndex, Holder<Block> block) {
            this.typeIndex = typeIndex;
            this.block = block;
        }

        public static ColorVariant[] getOverworldVariants() {
            return Arrays.stream(ColorVariant.values())
                    .filter(ColorVariant::isOverworldVariant)
                    .toArray(ColorVariant[]::new);
        }

        public static ColorVariant[] getNetherVariants() {
            return Arrays.stream(ColorVariant.values())
                    .filter(ColorVariant::isNetherVariant)
                    .toArray(ColorVariant[]::new);
        }

        public boolean isOverworldVariant() {
            return !this.isNetherVariant();
        }

        public boolean isNetherVariant() {
            return this == CRIMSON || this == WARPED;
        }

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
