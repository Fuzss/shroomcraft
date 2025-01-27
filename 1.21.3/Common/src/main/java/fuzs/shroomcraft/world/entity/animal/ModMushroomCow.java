package fuzs.shroomcraft.world.entity.animal;

import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;

public class ModMushroomCow extends MushroomCow {
    private static final EntityDataAccessor<ColorVariant> DATA_TYPE = SynchedEntityData.defineId(ModMushroomCow.class,
            ModRegistry.MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER.value());

    @Nullable
    private UUID lastLightningBoltUUID;

    public ModMushroomCow(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }

    public static boolean checkCustomMushroomSpawnRules(EntityType<? extends MushroomCow> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkMushroomSpawnRules((EntityType<MushroomCow>) entityType, level, spawnReason, pos, random);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE, ColorVariant.BLUE);
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
                lootTable == BuiltInLootTables.SHEAR_MOOSHROOM ? ModRegistry.SHEAR_MOOSHROOM_LOOT_TABLE : lootTable,
                shears,
                dropConsumer);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        ColorVariant.CODEC.encodeStart(NbtOps.INSTANCE, this.getColorVariant())
                .ifSuccess((Tag tagX) -> tag.put("color_variant", tagX));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        ColorVariant.CODEC.parse(NbtOps.INSTANCE, tag.get("color_variant"))
                .resultOrPartial(Util.prefix(this.getDisplayName().getString(), Shroomcraft.LOGGER::error))
                .ifPresent(this::setColorVariant);
    }

    @Override
    public void setVariant(MushroomCow.Variant variant) {
        // NO-OP
    }

    @Override
    public MushroomCow.Variant getVariant() {
        return MushroomCow.Variant.RED;
    }

    public void setColorVariant(ColorVariant colorVariant) {
        this.entityData.set(DATA_TYPE, colorVariant);
    }

    public ColorVariant getColorVariant() {
        return this.entityData.get(DATA_TYPE);
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
        public static final IntFunction<ColorVariant> BY_ID = ByIdMap.continuous(ColorVariant::ordinal,
                values(),
                ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, ColorVariant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID,
                ColorVariant::ordinal);

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
