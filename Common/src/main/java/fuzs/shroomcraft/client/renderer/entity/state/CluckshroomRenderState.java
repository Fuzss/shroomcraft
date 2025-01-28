package fuzs.shroomcraft.client.renderer.entity.state;

import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CluckshroomRenderState extends ChickenRenderState implements BlockStateCarrierRenderState {
    public ResourceLocation textureLocation = MobBlockVariant.transformTextureLocation(MobBlockVariant.getTextureLocation(
            ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
            ModRegistry.RED_CLUCKSHROOM_VARIANT));
    public BlockState blockState = Blocks.RED_MUSHROOM.defaultBlockState();

    @Override
    public BlockState getBlockState() {
        return this.blockState;
    }
}
