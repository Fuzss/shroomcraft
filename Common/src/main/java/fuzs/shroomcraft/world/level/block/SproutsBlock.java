package fuzs.shroomcraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SproutsBlock extends RootsBlock {

    public SproutsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }
}
