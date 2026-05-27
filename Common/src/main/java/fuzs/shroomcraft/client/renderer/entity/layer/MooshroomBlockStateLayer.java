package fuzs.shroomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.client.model.MooshroomModel;
import fuzs.shroomcraft.world.entity.animal.Mooshroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Identical, only replacing the render state with one that supports our custom variants.
 *
 * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer
 */
public class MooshroomBlockStateLayer extends RenderLayer<Mooshroom, MooshroomModel<Mooshroom>> {
    private final BlockRenderDispatcher blockRenderer;

    public MooshroomBlockStateLayer(RenderLayerParent<Mooshroom, MooshroomModel<Mooshroom>> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Mooshroom moobloom, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!moobloom.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean outlineOnly = minecraft.shouldEntityAppearGlowing(moobloom) && moobloom.isInvisible();
            if (!moobloom.isInvisible() || outlineOnly) {
                BlockState blockState = moobloom.getColorVariant().block.value().defaultBlockState();
                int packedOverlay = LivingEntityRenderer.getOverlayCoords(moobloom, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        packedOverlay,
                        bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        packedOverlay,
                        bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        packedOverlay,
                        bakedModel);
                poseStack.popPose();
            }
        }
    }

    /**
     * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer#renderMushroomBlock(PoseStack,
     *         MultiBufferSource, int, boolean, BlockState, int, BakedModel)
     */
    private void renderBlock(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState state, int packedOverlay, BakedModel model) {
        if (outlineOnly) {
            this.blockRenderer.getModelRenderer()
                    .renderModel(poseStack.last(),
                            buffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)),
                            state,
                            model,
                            0.0F,
                            0.0F,
                            0.0F,
                            packedLight,
                            packedOverlay);
        } else {
            this.blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}
