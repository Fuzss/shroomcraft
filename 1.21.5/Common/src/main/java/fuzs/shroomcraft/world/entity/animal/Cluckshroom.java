package fuzs.shroomcraft.world.entity.animal;

import fuzs.puzzleslib.api.entity.v1.EntityHelper;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cluckshroom extends Chicken implements Shearable {
    private static final EntityDataAccessor<Holder<MobBlockVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(
            Cluckshroom.class,
            ModRegistry.CLUCKSHROOM_VARIANT_ENTITY_DATA_SERIALIZER.value());

    @Nullable
    private UUID lastLightningBoltUUID;

    public Cluckshroom(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkCluckshroomSpawnRules(EntityType<? extends Mob> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.MOOSHROOMS_SPAWNABLE_ON) &&
                isBrightEnoughToSpawn(level, pos);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.removeAllGoals(WaterAvoidingRandomStrollGoal.class::isInstance);
        this.goalSelector.addGoal(5, new CluckshroomRandomStrollGoal(this, 1.0));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        Registry<MobBlockVariant> registry = this.registryAccess()
                .lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
        builder.define(DATA_VARIANT_ID,
                registry.get(ModRegistry.RED_CLUCKSHROOM_VARIANT).or(registry::getAny).orElseThrow());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.is(Items.BOWL) && !this.isBaby()) {
            ItemStack newItemInHand = ItemUtils.createFilledResult(itemInHand,
                    player,
                    new ItemStack(Items.MUSHROOM_STEW),
                    false);
            player.setItemInHand(hand, newItemInHand);
            this.playSound(SoundEvents.MOOSHROOM_MILK, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        } else if (itemInHand.is(Items.SHEARS) && this.readyForShearing()) {
            if (this.level() instanceof ServerLevel serverLevel) {
                this.shear(serverLevel, SoundSource.PLAYERS, itemInHand);
                this.gameEvent(GameEvent.SHEAR, player);
                itemInHand.hurtAndBreak(1, player, getSlotForHand(hand));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void aiStep() {
        // prevent chicken from laying an egg
        this.eggTime = 6000;
        super.aiStep();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        Holder<MobBlockVariant> variant;
        if (spawnGroupData instanceof MobBlockVariantGroupData mobBlockVariantGroupData) {
            variant = mobBlockVariantGroupData.variant;
        } else {
            Holder<Biome> biome = level.getBiome(this.blockPosition());
            variant = getSpawnVariant(this.registryAccess(), biome);
            spawnGroupData = new MobBlockVariantGroupData(variant);
        }

        this.setBlockVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    public static Holder<MobBlockVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome) {
        Registry<MobBlockVariant> registry = registryAccess.lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
        return getRandomSpawnVariant(registry, (Holder<MobBlockVariant> holder) -> {
            return holder.value().biomes().contains(biome);
        }).or(() -> getRandomSpawnVariant(registry, (Holder<MobBlockVariant> holder) -> {
            TagKey<MobBlockVariant> tagKey =
                    biome.is(BiomeTags.IS_NETHER) ? ModRegistry.NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG :
                            ModRegistry.DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG;
            return holder.is(tagKey);
        })).or(() -> registry.get(ModRegistry.RED_CLUCKSHROOM_VARIANT)).or(registry::getAny).orElseThrow();
    }

    static Optional<Holder<MobBlockVariant>> getRandomSpawnVariant(Registry<MobBlockVariant> registry, Predicate<Holder<MobBlockVariant>> filter) {
        return Optional.ofNullable(registry.listElements()
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        (List<Holder.Reference<MobBlockVariant>> list) -> {
                            Collections.shuffle(list);
                            return !list.isEmpty() ? list.getFirst() : null;
                        })));
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            Registry<MobBlockVariant> registry = this.registryAccess()
                    .lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
            int newIndex = (registry.getIdOrThrow(this.getBlockVariant().value()) + 1) % registry.size();
            this.setBlockVariant(registry.get(newIndex).orElseThrow(NoSuchElementException::new));
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack shears) {
        level.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, soundSource, 1.0F, 1.0F);
        this.convertTo(EntityType.CHICKEN, ConversionParams.single(this, false, false), cow -> {
            level.sendParticles(ParticleTypes.EXPLOSION,
                    this.getX(),
                    this.getY(0.5),
                    this.getZ(),
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            this.dropFromShearingLootTable(level,
                    this.getBlockVariant().value().shearingLootTable(),
                    shears,
                    (serverLevelx, itemStackx) -> {
                        for (int i = 0; i < itemStackx.getCount(); i++) {
                            serverLevelx.addFreshEntity(new ItemEntity(this.level(),
                                    this.getX(),
                                    this.getY(1.0),
                                    this.getZ(),
                                    itemStackx.copyWithCount(1)));
                        }
                    });
        });
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    public void setBlockVariant(Holder<MobBlockVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    public Holder<MobBlockVariant> getBlockVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.store(Shroomcraft.id("variant").toString(),
                MobBlockVariant.codec(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY),
                this.registryAccess().createSerializationContext(NbtOps.INSTANCE),
                this.getBlockVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        tag.read(Shroomcraft.id("variant").toString(),
                MobBlockVariant.codec(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY),
                this.registryAccess().createSerializationContext(NbtOps.INSTANCE)).ifPresent(this::setBlockVariant);
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> dataComponentType) {
        return dataComponentType == ModRegistry.MOB_BLOCK_VARIANT_DATA_COMPONENT_TYPE.value() ?
                castComponentValue((DataComponentType<T>) dataComponentType,
                        new EitherHolder<>(this.getBlockVariant())) : super.get(dataComponentType);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter dataComponentGetter) {
        this.applyImplicitComponentIfPresent(dataComponentGetter,
                ModRegistry.MOB_BLOCK_VARIANT_DATA_COMPONENT_TYPE.value());
        super.applyImplicitComponents(dataComponentGetter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> dataComponentType, T object) {
        if (dataComponentType == ModRegistry.MOB_BLOCK_VARIANT_DATA_COMPONENT_TYPE.value()) {
            Optional<Holder<MobBlockVariant>> optional = castComponentValue(ModRegistry.MOB_BLOCK_VARIANT_DATA_COMPONENT_TYPE.value(),
                    object).unwrap(this.registryAccess());
            if (optional.isPresent()) {
                this.setBlockVariant(optional.get());
                return true;
            } else {
                return false;
            }
        } else {
            return super.applyImplicitComponent(dataComponentType, object);
        }
    }

    @Nullable
    @Override
    public Cluckshroom getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Cluckshroom cluckshroom = (Cluckshroom) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (cluckshroom != null) {
            cluckshroom.setBlockVariant(this.getBlockVariant());
        }

        return cluckshroom;
    }

    public static class MobBlockVariantGroupData extends AgeableMob.AgeableMobGroupData {
        public final Holder<MobBlockVariant> variant;

        public MobBlockVariantGroupData(Holder<MobBlockVariant> variant) {
            this(variant, true);
        }

        public MobBlockVariantGroupData(Holder<MobBlockVariant> variant, boolean shouldSpawnBaby) {
            super(shouldSpawnBaby);
            this.variant = variant;
        }
    }

    public static class CluckshroomRandomStrollGoal extends WaterAvoidingRandomStrollGoal {

        public CluckshroomRandomStrollGoal(Cluckshroom cluckshroom, double speedModifier) {
            super(cluckshroom, speedModifier);
        }

        @Override
        public void tick() {
            ServerLevel serverLevel = getServerLevel(this.mob);
            if (EntityHelper.isMobGriefingAllowed(serverLevel, this.mob)) {
                if (!this.mob.isBaby() && serverLevel.random.nextInt(1000) == 0 &&
                        this.mob.getDeltaMovement().lengthSqr() > 1.0E-5F) {
                    BlockPos blockPos = this.mob.blockPosition();
                    BlockState blockState = ((Cluckshroom) this.mob).getBlockVariant().value().blockState();
                    if (serverLevel.getBlockState(blockPos).isAir() && blockState.canSurvive(serverLevel, blockPos)) {
                        serverLevel.setBlockAndUpdate(blockPos, blockState);
                        SoundType soundType = blockState.getSoundType();
                        // copied from placing block item
                        serverLevel.playSound(null,
                                blockPos,
                                soundType.getPlaceSound(),
                                SoundSource.BLOCKS,
                                (soundType.getVolume() + 1.0F) / 2.0F,
                                soundType.getPitch() * 0.8F);
                        serverLevel.gameEvent(GameEvent.BLOCK_PLACE,
                                blockPos,
                                GameEvent.Context.of(this.mob, blockState));
                    }
                }
            }
        }
    }
}
