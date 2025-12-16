package fuzs.shroomcraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.init.ModModelLayers;
import fuzs.shroomcraft.client.model.ShroomfinModel;
import fuzs.shroomcraft.world.entity.animal.Shroomfin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

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
    protected void setupRotations(LivingEntityRenderState renderState, PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(renderState, poseStack, bodyRot, scale);
        float f = 4.3F * Mth.sin(0.6F * renderState.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!renderState.isInWater) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
