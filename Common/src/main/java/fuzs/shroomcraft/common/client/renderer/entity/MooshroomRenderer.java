package fuzs.shroomcraft.common.client.renderer.entity;

import fuzs.shroomcraft.common.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.common.client.renderer.entity.layer.MooshroomBlockStateLayer;
import fuzs.shroomcraft.common.client.renderer.entity.state.MooshroomRenderState;
import fuzs.shroomcraft.common.world.entity.animal.cow.Mooshroom;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;

public class MooshroomRenderer extends AgeableMobRenderer<Mooshroom, MooshroomRenderState, CowModel> {
    private final BlockModelResolver blockModelResolver;

    public MooshroomRenderer(Context context) {
        super(context,
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM)),
                new BabyCowModel(context.bakeLayer(ModModelLayers.MOOSHROOM_BABY)),
                0.7F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new MooshroomBlockStateLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MooshroomRenderState state) {
        return state.variant == null ? MissingTextureAtlasSprite.getLocation() :
                state.isBaby ? state.variant.babyTexture().texturePath() :
                        state.variant.adultTexture().texturePath();
    }

    @Override
    public MooshroomRenderState createRenderState() {
        return new MooshroomRenderState();
    }

    @Override
    public void extractRenderState(Mooshroom mooshroom, MooshroomRenderState state, float partialTick) {
        super.extractRenderState(mooshroom, state, partialTick);
        state.variant = mooshroom.getBlockVariant().value();
        this.blockModelResolver.update(state.blockModel,
                state.variant.blockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}
