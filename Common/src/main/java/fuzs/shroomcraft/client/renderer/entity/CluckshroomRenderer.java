package fuzs.shroomcraft.client.renderer.entity;

import fuzs.shroomcraft.client.init.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.BlockStateCarrierLayer;
import fuzs.shroomcraft.client.renderer.entity.layer.ModMushroomCowMushroomLayer;
import fuzs.shroomcraft.client.renderer.entity.state.CluckshroomRenderState;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CluckshroomRenderer extends AgeableMobRenderer<Cluckshroom, CluckshroomRenderState, ChickenModel> {

    public CluckshroomRenderer(Context context) {
        super(context,
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM)),
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM_BABY)),
                0.3F);
        this.addLayer(new BlockStateCarrierLayer<>(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(CluckshroomRenderState chickenRenderState) {
        return chickenRenderState.textureLocation;
    }

    @Override
    public CluckshroomRenderState createRenderState() {
        return new CluckshroomRenderState();
    }

    @Override
    public void extractRenderState(Cluckshroom cluckshroom, CluckshroomRenderState cluckshroomRenderState, float partialTick) {
        super.extractRenderState(cluckshroom, cluckshroomRenderState, partialTick);
        cluckshroomRenderState.flap = Mth.lerp(partialTick, cluckshroom.oFlap, cluckshroom.flap);
        cluckshroomRenderState.flapSpeed = Mth.lerp(partialTick, cluckshroom.oFlapSpeed, cluckshroom.flapSpeed);
        cluckshroomRenderState.textureLocation = MobBlockVariant.transformTextureLocation(cluckshroom.getVariant()
                .value()
                .textureLocation());
        cluckshroomRenderState.blockState = cluckshroom.getVariant().value().blockState();
    }
}
