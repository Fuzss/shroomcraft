package fuzs.shroomcraft.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.ModMushroomCowMushroomLayer;
import fuzs.shroomcraft.client.renderer.entity.state.ModMushroomCowRenderState;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.resources.Identifier;

import java.util.Map;

public class ModMushroomCowRenderer extends AgeableMobRenderer<ModMushroomCow, ModMushroomCowRenderState, CowModel> {
    private static final Map<ModMushroomCow.ColorVariant, Identifier> TEXTURES = ImmutableMap.<ModMushroomCow.ColorVariant, Identifier>builder()
            .put(ModMushroomCow.ColorVariant.BLUE, Shroomcraft.id("textures/entity/cow/blue_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.ORANGE, Shroomcraft.id("textures/entity/cow/orange_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.PURPLE, Shroomcraft.id("textures/entity/cow/purple_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.CRIMSON, Shroomcraft.id("textures/entity/cow/crimson_mooshroom.png"))
            .put(ModMushroomCow.ColorVariant.WARPED, Shroomcraft.id("textures/entity/cow/warped_mooshroom.png"))
            .build();

    private final BlockModelResolver blockModelResolver;

    public ModMushroomCowRenderer(Context context) {
        super(context,
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM)),
                new CowModel(context.bakeLayer(ModModelLayers.MOOSHROOM_BABY)),
                0.7F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new ModMushroomCowMushroomLayer(this));
    }

    @Override
    public Identifier getTextureLocation(ModMushroomCowRenderState renderState) {
        return TEXTURES.get(renderState.variant);
    }

    @Override
    public ModMushroomCowRenderState createRenderState() {
        return new ModMushroomCowRenderState();
    }

    @Override
    public void extractRenderState(ModMushroomCow entity, ModMushroomCowRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.variant = entity.getColorVariant();
        this.blockModelResolver.update(state.blockModel,
                state.variant.block.value().defaultBlockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}
