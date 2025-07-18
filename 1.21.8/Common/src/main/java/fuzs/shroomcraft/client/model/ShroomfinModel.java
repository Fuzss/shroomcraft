package fuzs.shroomcraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class ShroomfinModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart tailFin;

    public ShroomfinModel(ModelPart root) {
        super(root);
        this.tailFin = root.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -7.0F, 2.0F, 5.0F, 14.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("nose",
                CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -5.0F, -8.0F, 2.0F, 4.0F, 1.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("top_fin",
                CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -9.0F, -4.0F, 0.0F, 4.0F, 10.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("bottom_fin",
                CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 5.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_fin",
                CubeListBuilder.create().texOffs(28, 29).addBox(-4.5F, 0.0F, -1.5F, 4.0F, 0.0F, 4.0F),
                PartPose.offsetAndRotation(-0.5F, 22.5F, -2.5F, 0.0F, 0.0F, -0.6981F));
        partDefinition.addOrReplaceChild("left_fin",
                CubeListBuilder.create().texOffs(28, 29).addBox(0.5F, 0.0F, -1.5F, 4.0F, 0.0F, 4.0F).mirror(),
                PartPose.offsetAndRotation(0.5F, 22.5F, -2.5F, 0.0F, 0.0F, 0.6981F));
        partDefinition.addOrReplaceChild("tail_fin",
                CubeListBuilder.create().texOffs(20, 20).addBox(0.0F, -4.0F, 1.0F, 0.0F, 8.0F, 4.0F),
                PartPose.offset(0.0F, 21.0F, 6.0F));
        return LayerDefinition.create(meshDefinition, 48, 48);
    }

    @Override
    public void setupAnim(LivingEntityRenderState renderState) {
        super.setupAnim(renderState);
        float rotationAmount = renderState.isInWater ? 1.0F : 1.5F;
        this.tailFin.yRot = -rotationAmount * 0.45F * Mth.sin(0.6F * renderState.ageInTicks);
    }
}
