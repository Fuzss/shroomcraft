package fuzs.shroomcraft.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.client.model.MooshroomModel;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.layer.MooshroomBlockStateLayer;
import fuzs.shroomcraft.world.entity.animal.Mooshroom;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MooshroomRenderer extends MobRenderer<Mooshroom, MooshroomModel<Mooshroom>> {
    private static final Map<Mooshroom.ColorVariant, ResourceLocation> TEXTURES = ImmutableMap.<Mooshroom.ColorVariant, ResourceLocation>builder()
            .put(Mooshroom.ColorVariant.BLUE, Shroomcraft.id("textures/entity/cow/blue_mooshroom.png"))
            .put(Mooshroom.ColorVariant.ORANGE, Shroomcraft.id("textures/entity/cow/orange_mooshroom.png"))
            .put(Mooshroom.ColorVariant.PURPLE, Shroomcraft.id("textures/entity/cow/purple_mooshroom.png"))
            .put(Mooshroom.ColorVariant.CRIMSON, Shroomcraft.id("textures/entity/cow/crimson_mooshroom.png"))
            .put(Mooshroom.ColorVariant.WARPED, Shroomcraft.id("textures/entity/cow/warped_mooshroom.png"))
            .build();

    public MooshroomRenderer(Context context) {
        super(context, new MooshroomModel<>(context.bakeLayer(ModModelLayers.MOOSHROOM)), 0.7F);
        this.addLayer(new MooshroomBlockStateLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(Mooshroom mooshroom) {
        return TEXTURES.get(mooshroom.getColorVariant());
    }
}
