package fuzs.shroomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.shroomcraft.client.renderer.entity.state.ModMushroomCowRenderState;
import net.minecraft.client.model.CowModel;
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

/**
 * Copied from {@link net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer}, replacing the render state
 * with one that supports our custom variants.
 */
public class ModMushroomCowMushroomLayer extends RenderLayer<ModMushroomCowRenderState, CowModel> {
    private final BlockRenderDispatcher blockRenderer;

    public ModMushroomCowMushroomLayer(RenderLayerParent<ModMushroomCowRenderState, CowModel> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ModMushroomCowRenderState renderState, float yRot, float xRot) {
        if (!renderState.isBaby) {
            boolean bl = renderState.appearsGlowing && renderState.isInvisible;
            if (!renderState.isInvisible || bl) {
                BlockState blockState = renderState.variant.block.value().defaultBlockState();
                int i = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
                BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, bufferSource, packedLight, bl, blockState, i, blockStateModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, bufferSource, packedLight, bl, blockState, i, blockStateModel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, bufferSource, packedLight, bl, blockState, i, blockStateModel);
                poseStack.popPose();
            }
        }
    }

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
