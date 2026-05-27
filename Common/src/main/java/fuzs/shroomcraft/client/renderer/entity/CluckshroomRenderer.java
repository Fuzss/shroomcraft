package fuzs.shroomcraft.client.renderer.entity;

import fuzs.shroomcraft.client.model.CluckshroomModel;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.CluckshroomBlockStateLayer;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CluckshroomRenderer extends MobRenderer<Cluckshroom, CluckshroomModel<Cluckshroom>> {

    public CluckshroomRenderer(Context context) {
        super(context, new CluckshroomModel<>(context.bakeLayer(ModModelLayers.CLUCKSHROOM)), 0.3F);
        this.addLayer(new CluckshroomBlockStateLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(Cluckshroom cluckshroom) {
        return MobBlockVariant.transformTextureLocation(cluckshroom.getBlockVariant().value().textureLocation());
    }

    @Override
    protected float getBob(Cluckshroom cluckshroom, float partialTicks) {
        float flap = Mth.lerp(partialTicks, cluckshroom.oFlap, cluckshroom.flap);
        float flapSpeed = Mth.lerp(partialTicks, cluckshroom.oFlapSpeed, cluckshroom.flapSpeed);
        return (Mth.sin(flap) + 1.0F) * flapSpeed;
    }
}
