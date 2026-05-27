package fuzs.shroomcraft.world.entity.animal;

import fuzs.puzzleslib.api.event.v1.core.EventResultHolder;
import fuzs.puzzleslib.api.network.v4.codec.ExtraStreamCodecs;
import fuzs.puzzleslib.api.util.v1.CompoundTagHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModEntityTypes;
import fuzs.shroomcraft.init.ModRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Mooshroom extends MushroomCow {
    private static final EntityDataAccessor<ColorVariant> DATA_VARIANT_ID = SynchedEntityData.defineId(Mooshroom.class,
            ModRegistry.MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER.value());
    private static final Set<MobSpawnType> VALID_SPAWN_REASONS = Set.of(MobSpawnType.SPAWNER,
            MobSpawnType.TRIAL_SPAWNER,
            MobSpawnType.SPAWN_EGG,
            MobSpawnType.DISPENSER);

    @Nullable
    private UUID lastLightningBoltUUID;

    public Mooshroom(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkMooshroomSpawnRules(EntityType<? extends MushroomCow> entityType, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        return checkMushroomSpawnRules((EntityType<MushroomCow>) entityType, level, spawnReason, pos, random)
                || level.getBlockState(pos.below()).is(BlockTags.NYLIUM);
    }

    public static void onEntityLoad(Entity entity, ServerLevel serverLevel, boolean isLoadedFromDisk, @Nullable MobSpawnType entitySpawnReason) {
        if (!isLoadedFromDisk && entitySpawnReason != null && entity.getType() == EntityType.MOOSHROOM
                && entity instanceof MushroomCow mushroomCow && VALID_SPAWN_REASONS.contains(entitySpawnReason)
                && getSpawnAsCustomEntityOdds(serverLevel, entity.blockPosition(), serverLevel.getRandom())) {
            Mooshroom mob = mushroomCow.convertTo(ModEntityTypes.MOOSHROOM_ENTITY_TYPE.value(), false);
            if (mob != null) {
                DifficultyInstance difficulty = serverLevel.getCurrentDifficultyAt(mob.blockPosition());
                mob.finalizeSpawn(serverLevel, difficulty, entitySpawnReason, null);
            }
        }
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
                && entity.getType() == ModEntityTypes.MOOSHROOM_ENTITY_TYPE.value()) {
            if (level instanceof ServerLevel serverLevel) {
                Optional<Mob> optional = spawnOffspringFromSpawnEgg(player,
                        (Mob) entity,
                        serverLevel,
                        entity.position(),
                        itemInHand);
                optional.ifPresent((Mob mob) -> {
                    ((Mooshroom) entity).onOffspringSpawnedFromEgg(player, mob);
                });
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
                mob2.moveTo(pos.x(), pos.y(), pos.z(), 0.0F, 0.0F);
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData spawnGroupData) {
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
    public void shear(SoundSource soundSource) {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, soundSource, 1.0F, 1.0F);
            if (this.convertTo(EntityType.COW, false) != null) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                        this.getX(),
                        this.getY(0.5),
                        this.getZ(),
                        1,
                        0.0,
                        0.0,
                        0.0,
                        0.0);
                this.dropFromShearingLootTable(serverLevel,
                        this.getColorVariant().shearingLootTable,
                        (ServerLevel level, ItemStack item) -> {
                            for (int i = 0; i < item.getCount(); i++) {
                                level.addFreshEntity(new ItemEntity(this.level(),
                                        this.getX(),
                                        this.getY(1.0),
                                        this.getZ(),
                                        item.copyWithCount(1)));
                            }
                        });
            }
        }
    }

    /**
     * Copied from Minecraft 26.1.
     */
    protected void dropFromShearingLootTable(ServerLevel level, ResourceKey<LootTable> key, BiConsumer<ServerLevel, ItemStack> consumer) {
        this.dropFromLootTable(level,
                key,
                params -> params.withParameter(LootContextParams.ORIGIN, this.position())
                        .withParameter(LootContextParams.THIS_ENTITY, this)
                        .create(LootContextParamSets.SHEARING),
                consumer);
    }

    /**
     * Copied from Minecraft 26.1.
     */
    protected boolean dropFromLootTable(ServerLevel level, ResourceKey<LootTable> key, Function<LootParams.Builder, LootParams> paramsBuilder, BiConsumer<ServerLevel, ItemStack> consumer) {
        LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(key);
        LootParams params = paramsBuilder.apply(new LootParams.Builder(level));
        List<ItemStack> drops = lootTable.getRandomItems(params);
        if (!drops.isEmpty()) {
            drops.forEach((ItemStack item) -> consumer.accept(level, item));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.remove("Type");
        RegistryOps<Tag> registryOps = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        CompoundTagHelper.store(valueOutput,
                Shroomcraft.id("variant").toString(),
                ColorVariant.CODEC,
                registryOps,
                this.getColorVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag valueInput) {
        valueInput.remove("Type");
        super.readAdditionalSaveData(valueInput);
        RegistryOps<Tag> registryOps = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        CompoundTagHelper.read(valueInput, Shroomcraft.id("variant").toString(), ColorVariant.CODEC, registryOps)
                .ifPresent(this::setColorVariant);
    }

    @Override
    public MushroomCow.MushroomType getVariant() {
        return MushroomCow.MushroomType.RED;
    }

    public void setColorVariant(ColorVariant colorVariant) {
        this.entityData.set(DATA_VARIANT_ID, colorVariant);
    }

    public ColorVariant getColorVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Nullable
    @Override
    public MushroomCow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Mooshroom mooshroom = (Mooshroom) this.getType().create(level);
        if (mooshroom != null) {
            mooshroom.setColorVariant(this.getColorVariant());
        }

        return mooshroom;
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
        public final ResourceKey<LootTable> shearingLootTable;

        ColorVariant(int typeIndex, Holder<Block> block) {
            this.typeIndex = typeIndex;
            this.block = block;
            this.shearingLootTable = MobBlockVariant.getShearingLootTable(ModEntityTypes.MOOSHROOM_ENTITY_TYPE,
                    Shroomcraft.id(this.getSerializedName()));
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
