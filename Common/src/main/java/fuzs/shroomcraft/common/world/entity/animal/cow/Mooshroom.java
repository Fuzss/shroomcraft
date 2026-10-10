package fuzs.shroomcraft.common.world.entity.animal.cow;

import com.mojang.datafixers.util.Either;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.init.ModDataComponentTypes;
import fuzs.shroomcraft.common.init.ModEntityDataSerializers;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.init.ModTags;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import fuzs.shroomcraft.common.world.entity.animal.MobVariantUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Mooshroom extends MushroomCow {
    private static final EntityDataAccessor<Holder<MobVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(
            Mooshroom.class,
            ModEntityDataSerializers.MOOSHROOM_VARIANT.value());
    private static final Set<EntitySpawnReason> VALID_SPAWN_REASONS = Set.of(EntitySpawnReason.SPAWNER,
            EntitySpawnReason.TRIAL_SPAWNER,
            EntitySpawnReason.SPAWN_ITEM_USE,
            EntitySpawnReason.DISPENSER);

    @Nullable
    private UUID lastLightningBoltUUID;

    public Mooshroom(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkMooshroomSpawnRules(EntityType<? extends MushroomCow> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkMushroomSpawnRules((EntityType<MushroomCow>) entityType, level, spawnReason, pos, random)
                || level.getBlockState(pos.below()).is(BlockTags.NYLIUM);
    }

    public static void onEntityLoad(Entity entity, ServerLevel serverLevel, boolean isLoadedFromDisk, @Nullable EntitySpawnReason entitySpawnReason) {
        if (!isLoadedFromDisk && entitySpawnReason != null && entity.is(EntityTypeIds.MOOSHROOM)
                && VALID_SPAWN_REASONS.contains(entitySpawnReason) && getSpawnAsCustomEntityOdds(serverLevel,
                entity.blockPosition(),
                serverLevel.getRandom())) {
            ((MushroomCow) entity).convertTo(ModEntityTypes.MOOSHROOM.value(),
                    ConversionParams.single((MushroomCow) entity, false, false),
                    (Mooshroom mob) -> {
                        DifficultyInstance difficulty = serverLevel.getCurrentDifficultyAt(mob.blockPosition());
                        mob.finalizeSpawn(serverLevel, difficulty, entitySpawnReason, null);
                    });
        }
    }

    public static boolean getSpawnAsCustomEntityOdds(ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.getBiome(blockPos).is(BiomeTags.IS_NETHER)) {
            return true;
        } else {
            int mooshroomVariants = serverLevel.registryAccess()
                    .lookupOrThrow(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY)
                    .size();
            int vanillaVariants = MushroomCow.Variant.values().length;
            return randomSource.nextFloat() < mooshroomVariants / (float) (mooshroomVariants + vanillaVariants);
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
        Registry<MobVariant> registry = this.registryAccess()
                .lookupOrThrow(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY);
        builder.define(DATA_VARIANT_ID, registry.getAny().orElseThrow());
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            Registry<MobVariant> registry = this.registryAccess()
                    .lookupOrThrow(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY);
            Holder<MobVariant> currentVariant = this.getBlockVariant();
            TagKey<MobVariant> groupTag = currentVariant.is(ModTags.MooshroomVariants.NETHER_SPAWNS) ?
                    ModTags.MooshroomVariants.NETHER_SPAWNS :
                    ModTags.MooshroomVariants.DEFAULT_SPAWNS;
            this.setBlockVariant(MobVariantUtil.getNextVariant(registry, currentVariant, groupTag));
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        Holder<MobVariant> variant;
        if (spawnGroupData instanceof MobVariantUtil.VariantGroupData variantGroupData) {
            variant = variantGroupData.variant;
        } else {
            Holder<Biome> biome = level.getBiome(this.blockPosition());
            variant = MobVariantUtil.getSpawnVariant(this.registryAccess()
                    .lookupOrThrow(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY), biome, getFallbackTag(biome), level.getRandom());
            spawnGroupData = new MobVariantUtil.VariantGroupData(variant);
        }

        this.setBlockVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    private static TagKey<MobVariant> getFallbackTag(Holder<Biome> biome) {
        return biome.is(BiomeTags.IS_NETHER) ? ModTags.MooshroomVariants.NETHER_SPAWNS :
                ModTags.MooshroomVariants.DEFAULT_SPAWNS;
    }

    @Override
    protected void dropFromShearingLootTable(ServerLevel level, ResourceKey<LootTable> key, ItemInstance tool, BiConsumer<ServerLevel, ItemStack> consumer) {
        super.dropFromShearingLootTable(level,
                key == BuiltInLootTables.SHEAR_MOOSHROOM ? this.getBlockVariant().value().shearingLootTable() : key,
                tool,
                consumer);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.discard("Type");
        valueOutput.store(Shroomcraft.id("variant").toString(),
                MobVariant.codec(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY),
                this.getBlockVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        valueInput.read(Shroomcraft.id("variant").toString(),
                MobVariant.codec(fuzs.shroomcraft.common.init.MooshroomVariants.REGISTRY_KEY)).ifPresent(this::setBlockVariant);
    }

    @Override
    public MushroomCow.Variant getVariant() {
        return MushroomCow.Variant.RED;
    }

    public void setBlockVariant(Holder<MobVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    public Holder<MobVariant> getBlockVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> dataComponentType) {
        if (dataComponentType == ModDataComponentTypes.MOOSHROOM_VARIANT.value()) {
            return castComponentValue((DataComponentType<T>) dataComponentType, Either.right(this.getBlockVariant()));
        } else if (dataComponentType == DataComponents.MOOSHROOM_VARIANT) {
            return null;
        } else {
            return super.get(dataComponentType);
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter dataComponentGetter) {
        this.applyImplicitComponentIfPresent(dataComponentGetter,
                ModDataComponentTypes.MOOSHROOM_VARIANT.value());
        super.applyImplicitComponents(dataComponentGetter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> dataComponentType, T object) {
        if (dataComponentType == ModDataComponentTypes.MOOSHROOM_VARIANT.value()) {
            Optional<Holder<MobVariant>> optional = castComponentValue(ModDataComponentTypes.MOOSHROOM_VARIANT.value(),
                    object).right();
            if (optional.isPresent()) {
                this.setBlockVariant(optional.get());
                return true;
            } else {
                return false;
            }
        } else if (dataComponentType == DataComponents.MOOSHROOM_VARIANT) {
            return false;
        } else {
            return super.applyImplicitComponent(dataComponentType, object);
        }
    }

    @Nullable
    @Override
    public MushroomCow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Mooshroom mooshroom = (Mooshroom) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (mooshroom != null) {
            mooshroom.setBlockVariant(this.getBlockVariant());
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
}
