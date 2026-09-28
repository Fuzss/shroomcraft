package fuzs.shroomcraft.common.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record HugeBlueMushroomFeature(Holder<BlockStateProvider> capProvider,
                                      Holder<BlockStateProvider> stemProvider,
                                      int foliageRadius,
                                      BlockPredicate canPlaceOn) implements AbstractHugeMushroomFeature {
    public static final MapCodec<HugeBlueMushroomFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter(HugeBlueMushroomFeature::capProvider),
                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter(HugeBlueMushroomFeature::stemProvider),
                    Codec.INT.optionalFieldOf("foliage_radius", 2).forGetter(HugeBlueMushroomFeature::foliageRadius),
                    BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(HugeBlueMushroomFeature::canPlaceOn))
            .apply(instance, HugeBlueMushroomFeature::new));

    @Override
    public MapCodec<HugeBlueMushroomFeature> codec() {
        return CODEC;
    }

    @Override
    public void makeCap(WorldGenLevel level, RandomSource random, BlockPos pos, int treeHeight, BlockPos.MutableBlockPos mutablePos) {
        for (int i = treeHeight - 3; i <= treeHeight; i++) {
            int j = i < treeHeight ? this.foliageRadius - 1 : this.foliageRadius - 2;
            int k = this.foliageRadius - 3;

            for (int l = -j; l <= j; l++) {
                for (int m = -j; m <= j; m++) {
                    boolean bl = l == -j;
                    boolean bl2 = l == j;
                    boolean bl3 = m == -j;
                    boolean bl4 = m == j;
                    boolean bl5 = bl || bl2;
                    boolean bl6 = bl3 || bl4;
                    if (i >= treeHeight || bl5 != bl6 || i == treeHeight - 2 && bl5 && bl6) {
                        int offsetX = l + (i == treeHeight - 2 && Math.abs(l) > Math.abs(m) ? Mth.sign(l) : 0);
                        int offsetZ = m + (i == treeHeight - 2 && Math.abs(m) > Math.abs(l) ? Mth.sign(m) : 0);
                        mutablePos.setWithOffset(pos, offsetX, i, offsetZ);
                        if (!level.getBlockState(mutablePos).isSolidRender()) {
                            BlockState blockState = this.capProvider.value().getState(level, random, pos);
                            if (blockState.hasProperty(HugeMushroomBlock.WEST) && blockState.hasProperty(
                                    HugeMushroomBlock.EAST) && blockState.hasProperty(HugeMushroomBlock.NORTH)
                                    && blockState.hasProperty(HugeMushroomBlock.SOUTH) && blockState.hasProperty(
                                    HugeMushroomBlock.UP) && blockState.hasProperty(HugeMushroomBlock.DOWN)) {
                                blockState = blockState.setValue(HugeMushroomBlock.UP,
                                                Boolean.valueOf(i >= treeHeight - 2))
                                        .setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(i == treeHeight - 2))
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
