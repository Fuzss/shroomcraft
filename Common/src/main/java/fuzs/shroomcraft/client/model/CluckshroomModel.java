package fuzs.shroomcraft.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.MeshDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.world.entity.Entity;

public class CluckshroomModel<T extends Entity> extends ChickenModel<T> {

    public CluckshroomModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return new LayerDefinition(ChickenModel.createBodyLayer()).apply((MeshDefinition meshDefinition) -> {
            PartDefinition partDefinition = meshDefinition.getRoot();
            // fix rotation point to be at body and not in air
            partDefinition.addOrReplaceChild("left_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(3.0F, 13.0F, 0.0F));
            partDefinition.addOrReplaceChild("right_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(-3.0F, 13.0F, 0.0F));
            return meshDefinition;
        });
    }
}
