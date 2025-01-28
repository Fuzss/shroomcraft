package fuzs.shroomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.client.renderer.entity.state.BlockStateCarrierRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;

public class BlockStateCarrierLayer<S extends LivingEntityRenderState & BlockStateCarrierRenderState, M extends EntityModel<? super S>> extends RenderLayer<S, M> {
    private final BlockRenderDispatcher blockRenderer;

    public BlockStateCarrierLayer(RenderLayerParent<S, M> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    protected ModelPart getHead() {
        return this.getParentModel().root().getChild("head");
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S renderState, float yRot, float xRot) {
        if (!renderState.isBaby) {
            boolean bl = renderState.appearsGlowing && renderState.isInvisible;
            if (!renderState.isInvisible || bl) {
                BlockState blockState = renderState.getBlockState();
                int overlayCoords = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);

                poseStack.pushPose();
                poseStack.translate(0.0, 0.36, 0.15);
                poseStack.mulPose(Axis.YP.rotationDegrees(-6.0F));
                this.renderBlockState(poseStack, bufferSource, packedLight, bl, blockState, overlayCoords, bakedModel);
                poseStack.popPose();

                poseStack.pushPose();
                this.getHead().translateAndRotate(poseStack);
                poseStack.translate(0.02, -0.8, -0.03);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                this.renderBlockState(poseStack, bufferSource, packedLight, bl, blockState, overlayCoords, bakedModel);
                poseStack.popPose();
            }
        }
    }

    private void renderBlockState(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState blockState, int packedOverlay, BakedModel model) {
        if (outlineOnly) {
            this.blockRenderer.getModelRenderer()
                    .renderModel(poseStack.last(),
                            buffer.getBuffer(RenderType.outline(InventoryMenu.BLOCK_ATLAS)),
                            blockState,
                            model,
                            0.0F,
                            0.0F,
                            0.0F,
                            packedLight,
                            packedOverlay);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}
