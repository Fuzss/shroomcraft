package fuzs.shroomcraft.data.client;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.BiFunction;

public class ModLanguageProvider extends AbstractLanguageProvider {
    @Deprecated(forRemoval = true)
    static final Map<BlockFamily.Variant, BiFunction<BlockFamilyBuilder, Block, BlockFamilyBuilder>> VARIANT_FUNCTIONS = ImmutableMap.<BlockFamily.Variant, BiFunction<BlockFamilyBuilder, Block, BlockFamilyBuilder>>builder()
            .put(BlockFamily.Variant.BUTTON, BlockFamilyBuilder::button)
            .put(BlockFamily.Variant.CHISELED, BlockFamilyBuilder::chiseled)
            .put(BlockFamily.Variant.CRACKED, BlockFamilyBuilder::cracked)
            .put(BlockFamily.Variant.CUT, BlockFamilyBuilder::cut)
            .put(BlockFamily.Variant.DOOR, BlockFamilyBuilder::door)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockFamilyBuilder::fence)
            .put(BlockFamily.Variant.FENCE, BlockFamilyBuilder::fence)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockFamilyBuilder::fenceGate)
            .put(BlockFamily.Variant.FENCE_GATE, BlockFamilyBuilder::fenceGate)
            .put(BlockFamily.Variant.MOSAIC, BlockFamilyBuilder::mosaic)
            .put(BlockFamily.Variant.SIGN, BlockFamilyBuilder::sign)
            .put(BlockFamily.Variant.SLAB, BlockFamilyBuilder::slab)
            .put(BlockFamily.Variant.STAIRS, BlockFamilyBuilder::stairs)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockFamilyBuilder::pressurePlate)
            .put(BlockFamily.Variant.POLISHED, BlockFamilyBuilder::polished)
            .put(BlockFamily.Variant.TRAPDOOR, BlockFamilyBuilder::trapdoor)
            .put(BlockFamily.Variant.WALL, BlockFamilyBuilder::wall)
            .build();

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Deprecated(forRemoval = true)
    static void generateFor(BlockFamilyBuilder builder, BlockFamily blockFamily) {
        builder.baseBlock(blockFamily.getBaseBlock());
        blockFamily.getVariants().forEach((BlockFamily.Variant variant, Block block) -> {
            BiFunction<BlockFamilyBuilder, Block, BlockFamilyBuilder> variantFunction = VARIANT_FUNCTIONS.get(variant);
            if (variantFunction != null) {
                variantFunction.apply(builder, block);
            }
        });
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        generateFor(builder.blockFamily("Shroomwood"), ModBlockFamilies.SHROOMWOOD_FAMILY);
        generateFor(builder.blockFamily("Blue Shroomwood"), ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY);
        generateFor(builder.blockFamily("Orange Shroomwood"), ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY);
        generateFor(builder.blockFamily("Purple Shroomwood"), ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY);
        builder.add(ModBlocks.BLUE_MUSHROOM.value(), "Blue Mushroom");
        builder.add(ModBlocks.ORANGE_MUSHROOM.value(), "Orange Mushroom");
        builder.add(ModBlocks.PURPLE_MUSHROOM.value(), "Purple Mushroom");
        builder.add(ModBlocks.POTTED_BLUE_MUSHROOM.value(), "Potted Blue Mushroom");
        builder.add(ModBlocks.POTTED_ORANGE_MUSHROOM.value(), "Potted Orange Mushroom");
        builder.add(ModBlocks.POTTED_PURPLE_MUSHROOM.value(), "Potted Purple Mushroom");
        builder.add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(), "Blue Mushroom Block");
        builder.add(ModBlocks.ORANGE_MUSHROOM_BLOCK.value(), "Orange Mushroom Block");
        builder.add(ModBlocks.PURPLE_MUSHROOM_BLOCK.value(), "Purple Mushroom Block");
        builder.add(ModBlocks.BLUE_MUSHROOM_STEM.value(), "Blue Mushroom Stem");
        builder.add(ModBlocks.ORANGE_MUSHROOM_STEM.value(), "Orange Mushroom Stem");
        builder.add(ModBlocks.PURPLE_MUSHROOM_STEM.value(), "Purple Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), "Stripped Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), "Stripped Blue Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(), "Stripped Orange Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(), "Stripped Purple Mushroom Stem");
        builder.add(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value(), "Stripped Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value(), "Stripped Blue Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value(), "Stripped Orange Mushroom Hyphae");
        builder.add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value(), "Stripped Purple Mushroom Hyphae");
    }
}
