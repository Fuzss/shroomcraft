package fuzs.shroomcraft.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import fuzs.puzzleslib.api.client.init.v1.ClientWoodTypeRegistry;
import fuzs.shroomcraft.client.init.ModModelLayers;
import fuzs.shroomcraft.client.model.ShroomfinModel;
import fuzs.shroomcraft.client.renderer.entity.CluckshroomRenderer;
import fuzs.shroomcraft.client.renderer.entity.ModMushroomCowRenderer;
import fuzs.shroomcraft.client.renderer.entity.ShroomfinRenderer;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class ShroomcraftClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        ModBlockFamilies.getAllFamilyRegistrars()
                .map(BlockFamilyRegistrar::getWoodType)
                .forEach(ClientWoodTypeRegistry::registerWoodType);
    }

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModBlockFamilies.SHROOMWOOD_FAMILY.boatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.SHROOMWOOD_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.SHROOMWOOD_FAMILY.chestBoatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.SHROOMWOOD_CHEST_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.boatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.BLUE_SHROOMWOOD_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.chestBoatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.BLUE_SHROOMWOOD_CHEST_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.boatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.ORANGE_SHROOMWOOD_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.chestBoatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.ORANGE_SHROOMWOOD_CHEST_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.boatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.PURPLE_SHROOMWOOD_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.chestBoatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.PURPLE_SHROOMWOOD_CHEST_BOAT));
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
                () -> CluckshroomRenderer.createBodyLayer().apply(ChickenModel.BABY_TRANSFORMER));
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        context.registerChunkRenderType(ModBlocks.BLUE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.ORANGE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.PURPLE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_BLUE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_ORANGE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_PURPLE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.MYCELIAL_GROWTH.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.TINY_BROWN_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.TINY_RED_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.TINY_BLUE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.TINY_ORANGE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModBlocks.TINY_PURPLE_MUSHROOM.value(), ChunkSectionLayer.CUTOUT);
        ModBlockFamilies.getAllFamilyRegistrars()
                .mapMulti((BlockFamilyRegistrar registrar, Consumer<Holder.Reference<Block>> consumer) -> {
                    consumer.accept(registrar.getBlock(BlockFamily.Variant.DOOR));
                    consumer.accept(registrar.getBlock(BlockFamily.Variant.TRAPDOOR));
                })
                .map(Holder.Reference::value)
                .forEach((Block block) -> {
                    context.registerChunkRenderType(block, ChunkSectionLayer.CUTOUT);
                });
    }
}
