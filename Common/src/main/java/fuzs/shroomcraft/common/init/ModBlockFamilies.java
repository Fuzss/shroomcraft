package fuzs.shroomcraft.common.init;

import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockSetFamily SHROOMWOOD_FAMILY = wooden(ModRegistry.REGISTRIES,
            ModBlocks.SHROOMWOOD_PLANKS,
            "shroomwood").registerBlock(BlockSetVariant.STRIPPED_LOG, ModBlocks.STRIPPED_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.STRIPPED_LOG, ModItems.STRIPPED_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_WOOD, ModBlocks.STRIPPED_MUSHROOM_HYPHAE)
            .registerItem(BlockSetVariant.STRIPPED_WOOD, ModItems.STRIPPED_MUSHROOM_HYPHAE);
    public static final BlockSetFamily BLUE_SHROOMWOOD_FAMILY = wooden(ModRegistry.REGISTRIES,
            ModBlocks.BLUE_SHROOMWOOD_PLANKS,
            "blue_shroomwood").registerBlock(BlockSetVariant.LOG, ModBlocks.BLUE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.LOG, ModItems.BLUE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_LOG, ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.STRIPPED_LOG, ModItems.STRIPPED_BLUE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_WOOD, ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE)
            .registerItem(BlockSetVariant.STRIPPED_WOOD, ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE);
    public static final BlockSetFamily ORANGE_SHROOMWOOD_FAMILY = wooden(ModRegistry.REGISTRIES,
            ModBlocks.ORANGE_SHROOMWOOD_PLANKS,
            "orange_shroomwood").registerBlock(BlockSetVariant.LOG, ModBlocks.ORANGE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.LOG, ModItems.ORANGE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_LOG, ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.STRIPPED_LOG, ModItems.STRIPPED_ORANGE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_WOOD, ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE)
            .registerItem(BlockSetVariant.STRIPPED_WOOD, ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE);
    public static final BlockSetFamily PURPLE_SHROOMWOOD_FAMILY = wooden(ModRegistry.REGISTRIES,
            ModBlocks.PURPLE_SHROOMWOOD_PLANKS,
            "purple_shroomwood").registerBlock(BlockSetVariant.LOG, ModBlocks.PURPLE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.LOG, ModItems.PURPLE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_LOG, ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM)
            .registerItem(BlockSetVariant.STRIPPED_LOG, ModItems.STRIPPED_PURPLE_MUSHROOM_STEM)
            .registerBlock(BlockSetVariant.STRIPPED_WOOD, ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE)
            .registerItem(BlockSetVariant.STRIPPED_WOOD, ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE);

    public static void bootstrap() {
        // NO-OP
    }

    /**
     * @see BlockSetFamily#wooden(RegistryManager, Holder.Reference, String)
     */
    private static BlockSetFamily.Writable wooden(RegistryManager registries, Holder.Reference<Block> baseBlock, String baseName) {
        return BlockSetFamily.base(registries, baseBlock, baseName)
                .configureBlockFamily((BlockFamily.Builder blockFamily) -> {
                    blockFamily.recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks");
                })
                .generateFor(BlockSetVariant.STAIRS)
                .generateFor(BlockSetVariant.SLAB)
                .generateFor(BlockSetVariant.FENCE)
                .generateFor(BlockSetVariant.FENCE_GATE)
                .generateFor(BlockSetVariant.DOOR)
                .generateFor(BlockSetVariant.TRAPDOOR)
                .generateFor(BlockSetVariant.PRESSURE_PLATE)
                .generateFor(BlockSetVariant.BUTTON)
                .generateFor(BlockSetVariant.SIGN)
                .generateFor(BlockSetVariant.HANGING_SIGN)
                .generateFor(BlockSetVariant.SHELF)
                .generateFor(BlockSetVariant.BOAT)
                .generateFor(BlockSetVariant.CHEST_BOAT);
    }

    public static Stream<BlockSetFamily> getAllBlockSetFamilies() {
        return Stream.of(SHROOMWOOD_FAMILY, BLUE_SHROOMWOOD_FAMILY, ORANGE_SHROOMWOOD_FAMILY, PURPLE_SHROOMWOOD_FAMILY);
    }
}
