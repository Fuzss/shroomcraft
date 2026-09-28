package fuzs.shroomcraft.common.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.client.model.animal.fish.ShroomfinModel;
import fuzs.shroomcraft.common.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.common.world.entity.animal.fish.Shroomfin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

/**
 * @see net.minecraft.client.renderer.entity.CodRenderer
 */
public class ShroomfinRenderer extends MobRenderer<Shroomfin, LivingEntityRenderState, ShroomfinModel> {
    private static final Identifier TEXTURE_LOCATION = Shroomcraft.id("textures/entity/fish/shroomfin.png");

    public ShroomfinRenderer(EntityRendererProvider.Context context) {
        super(context, new ShroomfinModel(context.bakeLayer(ModModelLayers.SHROOMFIN)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    protected void setupRotations(LivingEntityRenderState renderState, PoseStack poseStack, float bodyRot, float entityScale) {
        super.setupRotations(renderState, poseStack, bodyRot, entityScale);
        float bodyZRot = 4.3F * Mth.sin(0.6F * renderState.ageInTicks);
        poseStack.rotate(Axis.YP.rotationDegrees(bodyZRot));
        if (!renderState.isInWater) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.rotate(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
