package fuzs.shroomcraft.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.common.api.client.init.v1.family.ClientBlockSetFamily;
import fuzs.shroomcraft.client.model.ShroomfinModel;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.CluckshroomRenderer;
import fuzs.shroomcraft.client.renderer.entity.ModMushroomCowRenderer;
import fuzs.shroomcraft.client.renderer.entity.ShroomfinRenderer;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.object.boat.BoatModel;

public class ShroomcraftClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        ModBlockFamilies.getAllBlockSetFamilies().forEach(ClientBlockSetFamily::register);
    }

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        ClientBlockSetFamily.registerFor(ModBlockFamilies.SHROOMWOOD_FAMILY,
                context,
                ModModelLayers.SHROOMWOOD_BOAT,
                ModModelLayers.SHROOMWOOD_CHEST_BOAT);
        ClientBlockSetFamily.registerFor(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY,
                context,
                ModModelLayers.BLUE_SHROOMWOOD_BOAT,
                ModModelLayers.BLUE_SHROOMWOOD_CHEST_BOAT);
        ClientBlockSetFamily.registerFor(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY,
                context,
                ModModelLayers.ORANGE_SHROOMWOOD_BOAT,
                ModModelLayers.ORANGE_SHROOMWOOD_CHEST_BOAT);
        ClientBlockSetFamily.registerFor(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY,
                context,
                ModModelLayers.PURPLE_SHROOMWOOD_BOAT,
                ModModelLayers.PURPLE_SHROOMWOOD_CHEST_BOAT);
        context.registerEntityRenderer(ModRegistry.MOOSHROOM_ENTITY_TYPE.value(), ModMushroomCowRenderer::new);
        context.registerEntityRenderer(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(), ShroomfinRenderer::new);
        context.registerEntityRenderer(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value(), CluckshroomRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.SHROOMWOOD_BOAT, BoatModel::createBoatModel);
        context.registerLayerDefinition(ModModelLayers.SHROOMWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
        context.registerLayerDefinition(ModModelLayers.BLUE_SHROOMWOOD_BOAT, BoatModel::createBoatModel);
        context.registerLayerDefinition(ModModelLayers.BLUE_SHROOMWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
        context.registerLayerDefinition(ModModelLayers.ORANGE_SHROOMWOOD_BOAT, BoatModel::createBoatModel);
        context.registerLayerDefinition(ModModelLayers.ORANGE_SHROOMWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
        context.registerLayerDefinition(ModModelLayers.PURPLE_SHROOMWOOD_BOAT, BoatModel::createBoatModel);
        context.registerLayerDefinition(ModModelLayers.PURPLE_SHROOMWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
        context.registerLayerDefinition(ModModelLayers.MOOSHROOM, CowModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.MOOSHROOM_BABY,
                () -> CowModel.createBodyLayer().apply(CowModel.BABY_TRANSFORMER));
        context.registerLayerDefinition(ModModelLayers.SHROOMFIN, ShroomfinModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKSHROOM, CluckshroomRenderer::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKSHROOM_BABY,
                () -> CluckshroomRenderer.createBodyLayer().apply(CluckshroomRenderer.BABY_TRANSFORMER));
    }
}
