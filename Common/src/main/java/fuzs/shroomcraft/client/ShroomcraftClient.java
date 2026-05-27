package fuzs.shroomcraft.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import fuzs.puzzleslib.api.client.init.v1.family.ClientBlockSetFamily;
import fuzs.puzzleslib.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.client.model.CluckshroomModel;
import fuzs.shroomcraft.client.model.MooshroomModel;
import fuzs.shroomcraft.client.model.ShroomfinModel;
import fuzs.shroomcraft.client.model.geom.ModModelLayers;
import fuzs.shroomcraft.client.renderer.entity.CluckshroomRenderer;
import fuzs.shroomcraft.client.renderer.entity.MooshroomRenderer;
import fuzs.shroomcraft.client.renderer.entity.ShroomfinRenderer;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModEntityTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

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
        context.registerEntityRenderer(ModEntityTypes.MOOSHROOM_ENTITY_TYPE.value(), MooshroomRenderer::new);
        context.registerEntityRenderer(ModEntityTypes.SHROOMFIN_ENTITY_TYPE.value(), ShroomfinRenderer::new);
        context.registerEntityRenderer(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE.value(), CluckshroomRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.SHROOMWOOD_BOAT, BoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.SHROOMWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.BLUE_SHROOMWOOD_BOAT, BoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.BLUE_SHROOMWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.ORANGE_SHROOMWOOD_BOAT, BoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.ORANGE_SHROOMWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.PURPLE_SHROOMWOOD_BOAT, BoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.PURPLE_SHROOMWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
        context.registerLayerDefinition(ModModelLayers.MOOSHROOM, MooshroomModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.SHROOMFIN, ShroomfinModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKSHROOM, CluckshroomModel::createBodyLayer);
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            ClientBlockSetFamily.registerFor(blockSetFamily, context, ClientBlockSetFamily.VARIANT_RENDER_TYPE);
        });
        context.registerRenderType(RenderType.cutout(), ModBlocks.BLUE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.ORANGE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.PURPLE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_BLUE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_ORANGE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_PURPLE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.MYCELIAL_GROWTH.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.BLUE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.ORANGE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.PURPLE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.TINY_BROWN_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.TINY_RED_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.TINY_BLUE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.TINY_ORANGE_MUSHROOM.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.TINY_PURPLE_MUSHROOM.value());
    }
}
