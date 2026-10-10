package fuzs.shroomcraft.common.client.renderer.entity;

import fuzs.shroomcraft.common.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.common.client.renderer.entity.layer.CluckshroomBlockStateLayer;
import fuzs.shroomcraft.common.client.renderer.entity.state.CluckshroomRenderState;
import fuzs.shroomcraft.common.world.entity.animal.chicken.Cluckshroom;
import net.minecraft.client.model.animal.chicken.AdultChickenModel;
import net.minecraft.client.model.animal.chicken.BabyChickenModel;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CluckshroomRenderer extends AgeableMobRenderer<Cluckshroom, CluckshroomRenderState, ChickenModel> {
    private final BlockModelResolver blockModelResolver;

    public CluckshroomRenderer(Context context) {
        super(context,
                new AdultChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM)),
                new BabyChickenModel(context.bakeLayer(ModModelLayers.CLUCKSHROOM_BABY)),
                0.3F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new CluckshroomBlockStateLayer(this));
    }

    @Override
    public Identifier getTextureLocation(CluckshroomRenderState state) {
        return state.variant == null ? MissingTextureAtlasSprite.getLocation() :
                state.isBaby ? state.variant.babyTexture().texturePath() : state.variant.adultTexture().texturePath();
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
        state.variant = cluckshroom.getBlockVariant().value();
        this.blockModelResolver.update(state.blockModel,
                state.variant.blockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}
