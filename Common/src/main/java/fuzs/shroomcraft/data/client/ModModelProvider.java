package fuzs.shroomcraft.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators builder) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> builder.family(blockFamily.getBaseBlock()).generateFor(blockFamily));
        builder.createPlant(ModBlocks.BLUE_MUSHROOM.value(),
                ModBlocks.POTTED_BLUE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.ORANGE_MUSHROOM.value(),
                ModBlocks.POTTED_ORANGE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.PURPLE_MUSHROOM.value(),
                ModBlocks.POTTED_PURPLE_MUSHROOM.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_BLOCK.value());
        builder.createMushroomBlock(ModBlocks.BLUE_MUSHROOM_STEM.value());
        builder.createMushroomBlock(ModBlocks.ORANGE_MUSHROOM_STEM.value());
        builder.createMushroomBlock(ModBlocks.PURPLE_MUSHROOM_STEM.value());
        builder.woodProvider(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        builder.woodProvider(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .logWithHorizontal(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value())
                .wood(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
    }
}
