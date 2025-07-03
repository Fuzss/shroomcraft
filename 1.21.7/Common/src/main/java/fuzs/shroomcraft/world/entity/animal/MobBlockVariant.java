package fuzs.shroomcraft.world.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

public record MobBlockVariant(ResourceLocation textureLocation,
                              BlockState blockState,
                              ResourceKey<LootTable> shearingLootTable,
                              HolderSet<Biome> biomes) {
    public static final Codec<MobBlockVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(MobBlockVariant::textureLocation),
            BlockState.CODEC.fieldOf("carried_block").forGetter(MobBlockVariant::blockState),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .fieldOf("shearing_loot_table")
                    .forGetter(MobBlockVariant::shearingLootTable),
            RegistryCodecs.homogeneousList(Registries.BIOME)
                    .optionalFieldOf("biomes", HolderSet.empty())
                    .forGetter(MobBlockVariant::biomes)).apply(instance, MobBlockVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, MobBlockVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            MobBlockVariant::textureLocation,
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
            MobBlockVariant::blockState,
            ResourceKey.streamCodec(Registries.LOOT_TABLE),
            MobBlockVariant::shearingLootTable,
            ByteBufCodecs.holderSet(Registries.BIOME),
            MobBlockVariant::biomes,
            MobBlockVariant::new);

    public MobBlockVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MobBlockVariant> resourceKey, Block block) {
        this(entityType, resourceKey, block, HolderSet.empty());
    }

    public MobBlockVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MobBlockVariant> resourceKey, Block block, Holder<Biome> biome) {
        this(entityType, resourceKey, block, HolderSet.direct(biome));
    }

    public MobBlockVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MobBlockVariant> resourceKey, Block block, HolderSet<Biome> biomes) {
        this(getTextureLocation(entityType, resourceKey),
                block.defaultBlockState(),
                getShearingLootTable(entityType, resourceKey),
                biomes);
    }

    public static Codec<Holder<MobBlockVariant>> codec(ResourceKey<Registry<MobBlockVariant>> registryKey) {
        return RegistryFixedCodec.create(registryKey);
    }

    public static StreamCodec<RegistryFriendlyByteBuf, Holder<MobBlockVariant>> streamCodec(ResourceKey<Registry<MobBlockVariant>> registryKey) {
        return ByteBufCodecs.holderRegistry(registryKey);
    }

    public static ResourceLocation transformTextureLocation(ResourceLocation resourceLocation) {
        return resourceLocation.withPath((String string) -> "textures/" + string + ".png");
    }

    public static ResourceLocation getTextureLocation(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MobBlockVariant> resourceKey) {
        String path = entityType.key().location().getPath();
        return resourceKey.location().withPath((String string) -> "entity/" + path + "/" + string + "_" + path);
    }

    public static ResourceKey<LootTable> getShearingLootTable(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MobBlockVariant> resourceKey) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                resourceKey.location()
                        .withPath((String string) -> "shearing/" + entityType.key().location().getPath() + "/" +
                                string));
    }
}
