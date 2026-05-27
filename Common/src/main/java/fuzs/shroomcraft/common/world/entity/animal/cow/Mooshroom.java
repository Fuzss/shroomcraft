package fuzs.shroomcraft.common.world.entity.animal.cow;

import fuzs.puzzleslib.common.api.event.v1.core.EventResultHolder;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Mooshroom extends MushroomCow {
    private static final EntityDataAccessor<MooshroomVariant> DATA_VARIANT_ID = SynchedEntityData.defineId(Mooshroom.class,
            ModRegistry.MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER.value());
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
        if (!isLoadedFromDisk && entitySpawnReason != null && entity.getType() == EntityType.MOOSHROOM
                && VALID_SPAWN_REASONS.contains(entitySpawnReason) && getSpawnAsCustomEntityOdds(serverLevel,
                entity.blockPosition(),
                serverLevel.getRandom())) {
            ((MushroomCow) entity).convertTo(ModEntityTypes.MOOSHROOM_ENTITY_TYPE.value(),
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
            return randomSource.nextInt(MooshroomVariant.OVERWORLD_VARIANTS.size() + 1) != 0;
        }
    }

    public static EventResultHolder<InteractionResult> onEntityInteract(Player player, Level level, InteractionHand interactionHand, Entity entity, Vec3 hitVector) {
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
        builder.define(DATA_VARIANT_ID, MooshroomVariant.BLUE);
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            this.setCustomVariant(this.getCustomVariant().apply(1));
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        MooshroomVariant variant;
        if (spawnGroupData instanceof MooshroomGroupData mooshroomGroupData) {
            variant = mooshroomGroupData.variant;
        } else {
            variant = getRandomVariant(level, this.blockPosition());
            spawnGroupData = new MooshroomGroupData(variant);
        }

        this.setCustomVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    private static MooshroomVariant getRandomVariant(LevelAccessor level, BlockPos pos) {
        Holder<Biome> holder = level.getBiome(pos);
        if (holder.is(Biomes.CRIMSON_FOREST)) {
            return MooshroomVariant.CRIMSON;
        } else if (holder.is(Biomes.WARPED_FOREST)) {
            return MooshroomVariant.WARPED;
        } else {
            List<MooshroomVariant> variants;
            if (holder.is(BiomeTags.IS_NETHER)) {
                variants = MooshroomVariant.NETHER_VARIANTS;
            } else {
                variants = MooshroomVariant.OVERWORLD_VARIANTS;
            }

            return Util.getRandom(variants, level.getRandom());
        }
    }

    @Override
    protected void dropFromShearingLootTable(ServerLevel level, ResourceKey<LootTable> key, ItemInstance tool, BiConsumer<ServerLevel, ItemStack> consumer) {
        super.dropFromShearingLootTable(level,
                key == BuiltInLootTables.SHEAR_MOOSHROOM ? this.getCustomVariant().shearingLootTable : key,
                tool,
                consumer);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.discard("Type");
        valueOutput.store(Shroomcraft.id("variant").toString(), MooshroomVariant.CODEC, this.getCustomVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        valueInput.read(Shroomcraft.id("variant").toString(), MooshroomVariant.CODEC).ifPresent(this::setCustomVariant);
    }

    @Override
    public MushroomCow.Variant getVariant() {
        return MushroomCow.Variant.RED;
    }

    public void setCustomVariant(MooshroomVariant colorVariant) {
        this.entityData.set(DATA_VARIANT_ID, colorVariant);
    }

    public MooshroomVariant getCustomVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> dataComponentType) {
        return dataComponentType == ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value() ?
                castComponentValue((DataComponentType<T>) dataComponentType, this.getCustomVariant()) :
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
            this.setCustomVariant(castComponentValue(ModRegistry.MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE.value(),
                    object));
            return true;
        } else {
            return super.applyImplicitComponent(dataComponentType, object);
        }
    }

    @Nullable
    @Override
    public MushroomCow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Mooshroom mooshroom = (Mooshroom) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (mooshroom != null) {
            mooshroom.setCustomVariant(this.getCustomVariant());
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
        public final MooshroomVariant variant;

        MooshroomGroupData(MooshroomVariant variant) {
            super(true);
            this.variant = variant;
        }
    }
}
