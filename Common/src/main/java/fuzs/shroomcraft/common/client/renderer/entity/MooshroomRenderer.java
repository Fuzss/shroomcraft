package fuzs.shroomcraft.common.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.common.client.renderer.entity.layer.MooshroomBlockStateLayer;
import fuzs.shroomcraft.common.client.renderer.entity.state.MooshroomRenderState;
import fuzs.shroomcraft.common.world.entity.animal.cow.Mooshroom;
import fuzs.shroomcraft.common.world.entity.animal.cow.MooshroomVariant;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.resources.Identifier;

import java.util.Map;

public class MooshroomRenderer extends AgeableMobRenderer<Mooshroom, MooshroomRenderState, CowModel> {
    private static final Map<MooshroomVariant, Identifier> TEXTURES = ImmutableMap.<MooshroomVariant, Identifier>builder()
            .put(MooshroomVariant.BLUE, Shroomcraft.id("textures/entity/cow/blue_mooshroom.png"))
            .put(MooshroomVariant.ORANGE, Shroomcraft.id("textures/entity/cow/orange_mooshroom.png"))
            .put(MooshroomVariant.PURPLE, Shroomcraft.id("textures/entity/cow/purple_mooshroom.png"))
            .put(MooshroomVariant.CRIMSON, Shroomcraft.id("textures/entity/cow/crimson_mooshroom.png"))
            .put(MooshroomVariant.WARPED, Shroomcraft.id("textures/entity/cow/warped_mooshroom.png"))
            .build();

    private final BlockModelResolver blockModelResolver;

    public MooshroomRenderer(Context context) {
        super(context,
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM)),
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM_BABY)),
                0.7F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new MooshroomBlockStateLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MooshroomRenderState renderState) {
        return TEXTURES.get(renderState.variant);
    }

    @Override
    public MooshroomRenderState createRenderState() {
        return new MooshroomRenderState();
    }

    @Override
    public void extractRenderState(Mooshroom entity, MooshroomRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.variant = entity.getCustomVariant();
        this.blockModelResolver.update(state.blockModel,
                state.variant.block.value().defaultBlockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}
