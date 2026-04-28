package fuzs.shroomcraft.common.client.renderer.entity;

import fuzs.shroomcraft.common.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.common.client.renderer.entity.layer.CluckshroomBlockStateLayer;
import fuzs.shroomcraft.common.client.renderer.entity.state.CluckshroomRenderState;
import fuzs.shroomcraft.common.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.animal.chicken.AdultChickenModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import java.util.Set;

public class CluckshroomRenderer extends AgeableMobRenderer<Cluckshroom, CluckshroomRenderState, AdultChickenModel> {
    /**
     * Copied from {@code ChickenModel#BABY_TRANSFORMER} from Minecraft 1.21.11.
     */
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false,
            5.0F,
            2.0F,
            2.0F,
            1.99F,
            24.0F,
            Set.of("head", "beak", "red_thing"));

    private final BlockModelResolver blockModelResolver;

    public CluckshroomRenderer(Context context) {
        super(context,
                new AdultChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM)),
                new AdultChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM_BABY)),
                0.3F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new CluckshroomBlockStateLayer(this));
    }

    public static LayerDefinition createBodyLayer() {
        // fix rotation point to be at body and not in air
        return AdultChickenModel.createBodyLayer().apply(meshDefinition -> {
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
    public void extractRenderState(Cluckshroom cluckshroom, CluckshroomRenderState state, float partialTick) {
        super.extractRenderState(cluckshroom, state, partialTick);
        state.flap = Mth.lerp(partialTick, cluckshroom.oFlap, cluckshroom.flap);
        state.flapSpeed = Mth.lerp(partialTick, cluckshroom.oFlapSpeed, cluckshroom.flapSpeed);
        state.textureLocation = MobBlockVariant.transformTextureLocation(cluckshroom.getBlockVariant()
                .value()
                .textureLocation());
        this.blockModelResolver.update(state.blockModel,
                cluckshroom.getBlockVariant().value().blockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}
