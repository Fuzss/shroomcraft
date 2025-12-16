package fuzs.shroomcraft.client.renderer.entity;

import fuzs.shroomcraft.client.init.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.CluckshroomBlockStateLayer;
import fuzs.shroomcraft.client.renderer.entity.state.CluckshroomRenderState;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CluckshroomRenderer extends AgeableMobRenderer<Cluckshroom, CluckshroomRenderState, ChickenModel> {

    public CluckshroomRenderer(Context context) {
        super(context,
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM)),
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM_BABY)),
                0.3F);
        this.addLayer(new CluckshroomBlockStateLayer(this, context.getBlockRenderDispatcher()));
    }

    public static LayerDefinition createBodyLayer() {
        // fix rotation point to be at body and not in air
        return ChickenModel.createBodyLayer().apply(meshDefinition -> {
            PartDefinition partDefinition = meshDefinition.getRoot();
            partDefinition.addOrReplaceChild("left_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(3.0F, 13.0F, 0.0F));
            partDefinition.addOrReplaceChild("right_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(-3.0F, 13.0F, 0.0F));
            return meshDefinition;
        });
    }

    @Override
    public Identifier getTextureLocation(CluckshroomRenderState chickenRenderState) {
        return chickenRenderState.textureLocation;
    }

    @Override
    public CluckshroomRenderState createRenderState() {
        return new CluckshroomRenderState();
    }

    @Override
    public void extractRenderState(Cluckshroom cluckshroom, CluckshroomRenderState cluckshroomRenderState, float partialTick) {
        super.extractRenderState(cluckshroom, cluckshroomRenderState, partialTick);
        cluckshroomRenderState.flap = Mth.lerp(partialTick, cluckshroom.oFlap, cluckshroom.flap);
        cluckshroomRenderState.flapSpeed = Mth.lerp(partialTick, cluckshroom.oFlapSpeed, cluckshroom.flapSpeed);
        cluckshroomRenderState.textureLocation = MobBlockVariant.transformTextureLocation(cluckshroom.getBlockVariant()
                .value()
                .textureLocation());
        cluckshroomRenderState.blockState = cluckshroom.getBlockVariant().value().blockState();
    }
}
