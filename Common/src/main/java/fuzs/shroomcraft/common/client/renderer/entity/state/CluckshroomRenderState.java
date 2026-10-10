package fuzs.shroomcraft.common.client.renderer.entity.state;

import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import org.jspecify.annotations.Nullable;

public class CluckshroomRenderState extends ChickenRenderState {
    public @Nullable MobVariant variant;
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}
