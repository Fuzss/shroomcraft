package fuzs.shroomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.client.renderer.entity.state.ModMushroomCowRenderState;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

/**
 * Identical, only replacing the render state with one that supports our custom variants.
 *
 * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer
 */
public class ModMushroomCowMushroomLayer extends RenderLayer<ModMushroomCowRenderState, CowModel> {

    public ModMushroomCowMushroomLayer(RenderLayerParent<ModMushroomCowRenderState, CowModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, ModMushroomCowRenderState state, float yRot, float xRot) {
        if (!state.isBaby && !state.blockModel.isEmpty()) {
            boolean outlineOnly = state.appearsGlowing() && state.isInvisible;
            if (!state.isInvisible || outlineOnly) {
                int packedOverlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(poseStack,
                        nodeCollector,
                        packedLight,
                        outlineOnly,
                        state.outlineColor,
                        state.blockModel,
                        packedOverlay);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(poseStack,
                        nodeCollector,
                        packedLight,
                        outlineOnly,
                        state.outlineColor,
                        state.blockModel,
                        packedOverlay);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(poseStack,
                        nodeCollector,
                        packedLight,
                        outlineOnly,
                        state.outlineColor,
                        state.blockModel,
                        packedOverlay);
                poseStack.popPose();
            }
        }
    }

    /**
     * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer#submitMushroomBlock(PoseStack,
     *         SubmitNodeCollector, int, boolean, int, BlockModelRenderState, int)
     */
    private void submitMushroomBlock(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, boolean appearsGlowingWithInvisibility, int outlineColor, BlockModelRenderState mushroomModel, int overlayCoords) {
        if (appearsGlowingWithInvisibility) {
            mushroomModel.submitOnlyOutline(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        } else {
            mushroomModel.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        }
    }
}
