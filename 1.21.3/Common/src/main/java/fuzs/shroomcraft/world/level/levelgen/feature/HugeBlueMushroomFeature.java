package fuzs.shroomcraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeBlueMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeBlueMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int treeHeight, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config) {
        for (int i = treeHeight - 3; i <= treeHeight; i++) {
            int j = i < treeHeight ? config.foliageRadius - 1 : config.foliageRadius - 2;
            int k = config.foliageRadius - 3;

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
                            BlockState blockState = config.capProvider.getState(random, pos);
                            if (blockState.hasProperty(HugeMushroomBlock.WEST) &&
                                    blockState.hasProperty(HugeMushroomBlock.EAST) &&
                                    blockState.hasProperty(HugeMushroomBlock.NORTH) &&
                                    blockState.hasProperty(HugeMushroomBlock.SOUTH) &&
                                    blockState.hasProperty(HugeMushroomBlock.UP) &&
                                    blockState.hasProperty(HugeMushroomBlock.DOWN)) {
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
    protected int getTreeRadiusForHeight(int unused, int height, int foliageRadius, int y) {
        int i = 0;
        if (y < height && y >= height - 3) {
            i = foliageRadius;
        } else if (y == height) {
            i = foliageRadius;
        }

        return i;
    }
}
