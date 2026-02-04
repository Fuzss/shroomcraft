package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.family.BlockSetFamily;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockSetFamily SHROOMWOOD_FAMILY = BlockSetFamily.wooden(ModRegistry.REGISTRIES,
            ModBlocks.SHROOMWOOD_PLANKS,
            "shroomwood");
    public static final BlockSetFamily BLUE_SHROOMWOOD_FAMILY = BlockSetFamily.wooden(ModRegistry.REGISTRIES,
            ModBlocks.BLUE_SHROOMWOOD_PLANKS,
            "blue_shroomwood");
    public static final BlockSetFamily ORANGE_SHROOMWOOD_FAMILY = BlockSetFamily.wooden(ModRegistry.REGISTRIES,
            ModBlocks.ORANGE_SHROOMWOOD_PLANKS,
            "orange_shroomwood");
    public static final BlockSetFamily PURPLE_SHROOMWOOD_FAMILY = BlockSetFamily.wooden(ModRegistry.REGISTRIES,
            ModBlocks.PURPLE_SHROOMWOOD_PLANKS,
            "purple_shroomwood");

    public static void bootstrap() {
        // NO-OP
    }

    public static Stream<BlockSetFamily> getAllBlockSetFamilies() {
        return Stream.of(SHROOMWOOD_FAMILY, BLUE_SHROOMWOOD_FAMILY, ORANGE_SHROOMWOOD_FAMILY, PURPLE_SHROOMWOOD_FAMILY);
    }
}
