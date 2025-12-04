package fuzs.shroomcraft.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.init.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.ModMushroomCowMushroomLayer;
import fuzs.shroomcraft.client.renderer.entity.state.ModMushroomCowRenderState;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ModMushroomCowRenderer extends AgeableMobRenderer<ModMushroomCow, ModMushroomCowRenderState, CowModel> {
    private static final Map<ModMushroomCow.ColorVariant, ResourceLocation> TEXTURES = ImmutableMap.<ModMushroomCow.ColorVariant, ResourceLocation>builder()
            .put(ModMushroomCow.ColorVariant.BLUE, Shroomcraft.id("textures/entity/cow/blue_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.ORANGE, Shroomcraft.id("textures/entity/cow/orange_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.PURPLE, Shroomcraft.id("textures/entity/cow/purple_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.CRIMSON, Shroomcraft.id("textures/entity/cow/crimson_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.WARPED, Shroomcraft.id("textures/entity/cow/warped_mooshroom.png"))
            .build();

    public ModMushroomCowRenderer(Context context) {
        super(context,
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM)),
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM_BABY)),
                0.7F);
        this.addLayer(new ModMushroomCowMushroomLayer(this, context.getBlockRenderDispatcher()));
    }

    /**
     * Copied from Minecraft 1.21.5+ to support changed texture dimensions.
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = createBaseCowModel();
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    /**
     * Copied from Minecraft 1.21.5+ to support changed texture dimensions.
     */
    static MeshDefinition createBaseCowModel() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F)
                        .texOffs(1, 33)
                        .addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F)
                        .texOffs(22, 0)
                        .addBox("right_horn", -5.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(22, 0)
                        .addBox("left_horn", 4.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, -8.0F)
        );
        partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F).texOffs(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
        );
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().mirror().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        CubeListBuilder cubeListBuilder2 = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, -5.0F));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, -5.0F));
        return meshDefinition;
    }

    @Override
    public ResourceLocation getTextureLocation(ModMushroomCowRenderState renderState) {
        return TEXTURES.get(renderState.variant);
    }

    @Override
    public ModMushroomCowRenderState createRenderState() {
        return new ModMushroomCowRenderState();
    }

    @Override
    public void extractRenderState(ModMushroomCow entity, ModMushroomCowRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.variant = entity.getColorVariant();
    }
}
