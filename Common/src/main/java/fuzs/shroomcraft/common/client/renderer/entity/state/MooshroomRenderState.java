package fuzs.shroomcraft.common.client.renderer.entity.state;

import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jspecify.annotations.Nullable;

public class MooshroomRenderState extends LivingEntityRenderState {
    public @Nullable MobVariant variant;
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}
