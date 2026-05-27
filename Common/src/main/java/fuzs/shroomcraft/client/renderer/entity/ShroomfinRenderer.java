package fuzs.shroomcraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.model.ShroomfinModel;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.world.entity.animal.Shroomfin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ShroomfinRenderer extends MobRenderer<Shroomfin, ShroomfinModel<Shroomfin>> {
    private static final ResourceLocation TEXTURE_LOCATION = Shroomcraft.id("textures/entity/fish/shroomfin.png");

    public ShroomfinRenderer(EntityRendererProvider.Context context) {
        super(context, new ShroomfinModel<>(context.bakeLayer(ModModelLayers.SHROOMFIN)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Shroomfin shroomfin) {
        return TEXTURE_LOCATION;
    }

    @Override
    protected void setupRotations(Shroomfin shroomfin, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(shroomfin, poseStack, bob, yBodyRot, partialTick, scale);
        float bodyZRot = 4.3F * Mth.sin(0.6F * bob);
        poseStack.mulPose(Axis.YP.rotationDegrees(bodyZRot));
        if (!shroomfin.isInWater()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
