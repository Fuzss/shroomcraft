package fuzs.shroomcraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SproutsBlock extends RootsBlock implements BonemealableBlock {

    public SproutsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int successCounter = 0;
        label:
        for (int i = (this.getSpreadWidth() + 1) * 16 - 1; i >= 0; i--) {
            BlockPos currentPos = blockPos;
            BlockState defaultBlockState = blockState.getBlock().defaultBlockState();
            for (int j = 0; j < i / 16; ++j) {
                currentPos = currentPos.offset(randomSource.nextInt(3) - 1, (
                        randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
                if (!defaultBlockState.canSurvive(serverLevel, currentPos) || serverLevel.getBlockState(currentPos).isCollisionShapeFullBlock(
                        serverLevel, currentPos)) {
                    continue label;
                }
            }
            if (serverLevel.isEmptyBlock(currentPos) && currentPos.getY() > serverLevel.getMinY()) {
                serverLevel.setBlock(currentPos, defaultBlockState, 2);
                if (++successCounter >= this.getMostSuccesses()) {
                    return;
                }
            }
        }
    }

    protected int getSpreadWidth() {
        return 4;
    }

    protected int getMostSuccesses() {
        return 3;
    }
}
