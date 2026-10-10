package fuzs.shroomcraft.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TinyMushroomCropBlock extends TorchflowerCropBlock {
    public static final MapCodec<TorchflowerCropBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    propertiesCodec(),
                    BuiltInRegistries.BLOCK.holderByNameCodec()
                            .fieldOf("plant")
                            .forGetter((TorchflowerCropBlock block) -> ((TinyMushroomCropBlock) block).plantBlock))
            .apply(instance, TinyMushroomCropBlock::new));
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    private final Holder<Block> plantBlock;

    public TinyMushroomCropBlock(Properties properties, Holder<Block> plantBlock) {
        super(properties);
        this.plantBlock = plantBlock;
    }

    @Override
    public MapCodec<TorchflowerCropBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(2) == 0) {
            super.randomTick(state, level, pos, random);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this;
    }

    @Override
    public BlockState getStateForAge(int age) {
        return age == this.getMaxAge() ? this.plantBlock.value().defaultBlockState() : super.getStateForAge(age);
    }
}
