package fuzs.shroomcraft.common.client.renderer.entity.state;

import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MooshroomRenderState extends LivingEntityRenderState {
    public MooshroomVariant variant = MooshroomVariant.BLUE;
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}
