package fuzs.shroomcraft.common.world.entity.animal.cow;

import fuzs.puzzleslib.common.api.network.v4.codec.ExtraStreamCodecs;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.init.ModBlocks;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;

public enum MooshroomVariant implements StringRepresentable {
    BLUE(0, ModBlocks.BLUE_MUSHROOM),
    ORANGE(1, ModBlocks.ORANGE_MUSHROOM),
    PURPLE(2, ModBlocks.PURPLE_MUSHROOM),
    CRIMSON(0, Blocks.CRIMSON_FUNGUS.builtInRegistryHolder()),
    WARPED(1, Blocks.WARPED_FUNGUS.builtInRegistryHolder());

    public static final StringRepresentableCodec<MooshroomVariant> CODEC = StringRepresentable.fromEnum(MooshroomVariant::values);
    public static final StreamCodec<ByteBuf, MooshroomVariant> STREAM_CODEC = ExtraStreamCodecs.fromEnum(
            MooshroomVariant.class);
    public static final List<MooshroomVariant> OVERWORLD_VARIANTS = Arrays.stream(MooshroomVariant.values())
            .filter(MooshroomVariant::isOverworldVariant)
            .toList();
    public static final List<MooshroomVariant> NETHER_VARIANTS = Arrays.stream(MooshroomVariant.values())
            .filter(MooshroomVariant::isNetherVariant)
            .toList();
    private static final IntFunction<MooshroomVariant> OVERWORLD_BY_ID = ByIdMap.continuous(MooshroomVariant::id,
            OVERWORLD_VARIANTS.toArray(MooshroomVariant[]::new),
            ByIdMap.OutOfBoundsStrategy.WRAP);
    private static final IntFunction<MooshroomVariant> NETHER_BY_ID = ByIdMap.continuous(MooshroomVariant::id,
            NETHER_VARIANTS.toArray(MooshroomVariant[]::new),
            ByIdMap.OutOfBoundsStrategy.WRAP);

    private final int id;
    public final Holder<Block> block;
    public final ResourceKey<LootTable> shearingLootTable;

    MooshroomVariant(int id, Holder<Block> block) {
        this.id = id;
        this.block = block;
        this.shearingLootTable = MobBlockVariant.getShearingLootTable(ModEntityTypes.MOOSHROOM_ENTITY_TYPE,
                Shroomcraft.id(this.getSerializedName()));
    }

    private int id() {
        return this.id;
    }

    public boolean isOverworldVariant() {
        return !this.isNetherVariant();
    }

    public boolean isNetherVariant() {
        return this == CRIMSON || this == WARPED;
    }

    private IntFunction<MooshroomVariant> idMapper() {
        return this.isNetherVariant() ? MooshroomVariant.NETHER_BY_ID : MooshroomVariant.OVERWORLD_BY_ID;
    }

    public MooshroomVariant apply(int idOffset) {
        return this.idMapper().apply(this.id() + idOffset);
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
