package fuzs.shroomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.client.renderer.entity.state.CluckshroomRenderState;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.state.BlockState;

public class CluckshroomBlockStateLayer extends RenderLayer<CluckshroomRenderState, ChickenModel> {
    private final BlockRenderDispatcher blockRenderer;

    public CluckshroomBlockStateLayer(RenderLayerParent<CluckshroomRenderState, ChickenModel> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CluckshroomRenderState renderState, float yRot, float xRot) {
        if (!renderState.isBaby) {
            boolean outlineOnly = renderState.appearsGlowing && renderState.isInvisible;
            if (!renderState.isInvisible || outlineOnly) {
                BlockState blockState = renderState.blockState;
                int overlayCoords = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
                BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(-0.03F, 0.58F, 0.09F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-6.0F));
                poseStack.scale(-0.5F, -0.5F, 0.5F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        overlayCoords,
                        blockStateModel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().head.translateAndRotate(poseStack);
                poseStack.translate(0.03F, -0.6F, -0.03F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.5F, -0.5F, 0.5F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        overlayCoords,
                        blockStateModel);
                poseStack.popPose();
            }
        }
    }

    /**
     * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer#renderMushroomBlock(PoseStack,
     *         MultiBufferSource, int, boolean, BlockState, int, BlockStateModel)
     */
    private void renderMushroomBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, boolean outlineOnly, BlockState blockState, int packedOverlay, BlockStateModel blockStateModel) {
        if (outlineOnly) {
            ModelBlockRenderer.renderModel(poseStack.last(),
                    multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)),
                    blockStateModel,
                    0.0F,
                    0.0F,
                    0.0F,
                    packedLight,
                    packedOverlay);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, packedLight, packedOverlay);
        }
    }
}
