package fuzs.shroomcraft.common.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record HugePurpleMushroomFeature(Holder<BlockStateProvider> capProvider,
                                        Holder<BlockStateProvider> stemProvider,
                                        int foliageRadius,
                                        BlockPredicate canPlaceOn) implements AbstractHugeMushroomFeature {
    public static final MapCodec<HugePurpleMushroomFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter(HugePurpleMushroomFeature::capProvider),
                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter(HugePurpleMushroomFeature::stemProvider),
                    Codec.INT.optionalFieldOf("foliage_radius", 2).forGetter(HugePurpleMushroomFeature::foliageRadius),
                    BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(HugePurpleMushroomFeature::canPlaceOn))
            .apply(instance, HugePurpleMushroomFeature::new));

    @Override
    public MapCodec<HugePurpleMushroomFeature> codec() {
        return CODEC;
    }

    @Override
    public void makeCap(WorldGenLevel level, RandomSource random, BlockPos pos, int treeHeight, BlockPos.MutableBlockPos mutablePos) {
        // same as red mushroom, but does not generate bottom most layer
        for (int i = treeHeight - 2; i <= treeHeight; i++) {
            int j = i < treeHeight ? this.foliageRadius : this.foliageRadius - 1;
            int k = this.foliageRadius - 2;

            for (int l = -j; l <= j; l++) {
                for (int m = -j; m <= j; m++) {
                    boolean bl = l == -j;
                    boolean bl2 = l == j;
                    boolean bl3 = m == -j;
                    boolean bl4 = m == j;
                    boolean bl5 = bl || bl2;
                    boolean bl6 = bl3 || bl4;
                    if (i >= treeHeight || bl5 != bl6) {
                        mutablePos.setWithOffset(pos, l, i, m);
                        if (!level.getBlockState(mutablePos).isSolidRender()) {
                            BlockState blockState = this.capProvider.value().getState(level, random, pos);
                            if (blockState.hasProperty(HugeMushroomBlock.WEST) && blockState.hasProperty(
                                    HugeMushroomBlock.EAST) && blockState.hasProperty(HugeMushroomBlock.NORTH)
                                    && blockState.hasProperty(HugeMushroomBlock.SOUTH) && blockState.hasProperty(
                                    HugeMushroomBlock.UP)) {
                                blockState = blockState.setValue(HugeMushroomBlock.UP,
                                                Boolean.valueOf(i >= treeHeight - 1))
                                        .setValue(HugeMushroomBlock.WEST, Boolean.valueOf(l < -k))
                                        .setValue(HugeMushroomBlock.EAST, Boolean.valueOf(l > k))
                                        .setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(m < -k))
                                        .setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(m > k));
                            }

                            this.setBlock(level, mutablePos, blockState);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getTreeRadiusForHeight(int unused, int height, int foliageRadius, int y) {
        int i = 0;
        if (y < height && y >= height - 3) {
            i = foliageRadius;
        } else if (y == height) {
            i = foliageRadius;
        }

        return i;
    }
}
