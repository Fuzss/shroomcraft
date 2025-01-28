package fuzs.shroomcraft.world.entity.animal;

import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.UUID;

public class Cluckshroom extends Chicken implements Shearable, VariantHolder<Holder<MobBlockVariant>> {
    private static final EntityDataAccessor<Holder<MobBlockVariant>> DATA_TYPE = SynchedEntityData.defineId(Cluckshroom.class,
            ModRegistry.CLUCKSHROOM_VARIANT_VARIANT_ENTITY_DATA_SERIALIZER.value());

    @Nullable
    private UUID lastLightningBoltUUID;

    public Cluckshroom(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkMushroomSpawnRules(EntityType<?> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.MOOSHROOMS_SPAWNABLE_ON) &&
                isBrightEnoughToSpawn(level, pos);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        Registry<MobBlockVariant> registry = this.registryAccess()
                .lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
        builder.define(DATA_TYPE, registry.get(ModRegistry.RED_CLUCKSHROOM_VARIANT).or(registry::getAny).orElseThrow());
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
        if (spawnGroupData instanceof CluckshroomGroupData cluckshroomGroupData) {
            variant = cluckshroomGroupData.variant;
        } else {
            Registry<MobBlockVariant> registry = this.registryAccess()
                    .lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
            variant = registry.getRandom(level.getRandom()).orElseThrow(NoSuchElementException::new);
            spawnGroupData = new CluckshroomGroupData(variant);
        }

        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            Registry<MobBlockVariant> registry = this.registryAccess()
                    .lookupOrThrow(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY);
            int newIndex = (registry.getIdOrThrow(this.getVariant().value()) + 1) % registry.size();
            this.setVariant(registry.get(newIndex).orElseThrow(NoSuchElementException::new));
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
                    this.getVariant().value().shearingLootTable(),
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

    @Override
    public void setVariant(Holder<MobBlockVariant> variant) {
        this.entityData.set(DATA_TYPE, variant);
    }

    @Override
    public Holder<MobBlockVariant> getVariant() {
        return this.entityData.get(DATA_TYPE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        MobBlockVariant.codec(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY)
                .encodeStart(NbtOps.INSTANCE, this.getVariant())
                .ifSuccess((Tag tagX) -> tag.put("variant", tagX));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        MobBlockVariant.codec(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY)
                .parse(NbtOps.INSTANCE, tag.get("variant"))
                .ifSuccess(this::setVariant);
    }

    @Nullable
    @Override
    public Cluckshroom getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Cluckshroom cluckshroom = (Cluckshroom) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (cluckshroom != null) {
            cluckshroom.setVariant(this.getVariant());
        }

        return cluckshroom;
    }

    public static class CluckshroomGroupData extends AgeableMob.AgeableMobGroupData {
        public final Holder<MobBlockVariant> variant;

        public CluckshroomGroupData(Holder<MobBlockVariant> variant) {
            super(true);
            this.variant = variant;
        }
    }
}
