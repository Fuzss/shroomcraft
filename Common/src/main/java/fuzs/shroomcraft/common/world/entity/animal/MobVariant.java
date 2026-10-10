package fuzs.shroomcraft.common.world.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.core.registries.codec.RegistryFixedCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

public record MobVariant(ClientAsset.ResourceTexture adultTexture,
                         ClientAsset.ResourceTexture babyTexture,
                         BlockState blockState,
                         ResourceKey<LootTable> shearingLootTable,
                         HolderSet<Biome> biomes) {
    public static final Codec<MobVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(MobVariant::adultTexture),
            ClientAsset.ResourceTexture.CODEC.fieldOf("baby_asset_id").forGetter(MobVariant::babyTexture),
            BlockState.CODEC.fieldOf("carried_block").forGetter(MobVariant::blockState),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .fieldOf("shearing_loot_table")
                    .forGetter(MobVariant::shearingLootTable),
            RegistryCodecs.holderSet(Registries.BIOME)
                    .optionalFieldOf("biomes", HolderSet.empty())
                    .forGetter(MobVariant::biomes)).apply(instance, MobVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, MobVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
            ClientAsset.ResourceTexture.STREAM_CODEC,
            MobVariant::adultTexture,
            ClientAsset.ResourceTexture.STREAM_CODEC,
            MobVariant::babyTexture,
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
            MobVariant::blockState,
            ResourceKey.streamCodec(Registries.LOOT_TABLE),
            MobVariant::shearingLootTable,
            ByteBufCodecs.holderSet(Registries.BIOME),
            MobVariant::biomes,
            MobVariant::new);

    public static MobVariant create(Holder.Reference<? extends EntityType<?>> entityType, String variantName, Block block) {
        return create(entityType, variantName, block, HolderSet.empty());
    }

    public static MobVariant create(Holder.Reference<? extends EntityType<?>> entityType, String variantName, Block block, Holder<Biome> biome) {
        return create(entityType, variantName, block, HolderSet.direct(biome));
    }

    public static MobVariant create(Holder.Reference<? extends EntityType<?>> entityType, String variantName, Block block, HolderSet<Biome> biomes) {
        ClientAsset.ResourceTexture adultTexture = getVariantTexture(entityType, variantName);
        ClientAsset.ResourceTexture babyTexture = getVariantTexture(entityType, variantName + "_baby");
        ResourceKey<LootTable> lootTable = getShearingLootTable(entityType, variantName);
        return new MobVariant(adultTexture, babyTexture, block.defaultBlockState(), lootTable, biomes);
    }

    public static Codec<Holder<MobVariant>> codec(ResourceKey<Registry<MobVariant>> registryKey) {
        return RegistryFixedCodec.create(registryKey);
    }

    public static StreamCodec<RegistryFriendlyByteBuf, Holder<MobVariant>> streamCodec(ResourceKey<Registry<MobVariant>> registryKey) {
        return ByteBufCodecs.holderRegistry(registryKey);
    }

    public static ClientAsset.ResourceTexture getVariantTexture(Holder.Reference<? extends EntityType<?>> entityType, String variantName) {
        return new ClientAsset.ResourceTexture(entityType.key()
                .identifier()
                .withPath((String path) -> "entity/" + path + "/" + path + "_" + variantName));
    }

    public static ResourceKey<LootTable> getShearingLootTable(Holder.Reference<? extends EntityType<?>> entityType, String variantName) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                entityType.key().identifier().withPath((String path) -> "shearing/" + path + "/" + variantName));
    }
}
