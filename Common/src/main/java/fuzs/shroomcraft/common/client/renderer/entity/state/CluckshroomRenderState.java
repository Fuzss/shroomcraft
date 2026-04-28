package fuzs.shroomcraft.common.client.renderer.entity.state;

import fuzs.shroomcraft.common.init.CluckshroomVariants;
import fuzs.shroomcraft.common.init.ModRegistry;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class CluckshroomRenderState extends ChickenRenderState {
    public Identifier textureLocation = MobBlockVariant.transformTextureLocation(MobBlockVariant.getTextureLocation(
            ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
            CluckshroomVariants.RED_CLUCKSHROOM_VARIANT));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}
