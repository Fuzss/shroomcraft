package fuzs.shroomcraft.init;

import net.minecraft.data.BlockFamily;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockFamilyRegistrar SHROOMWOOD_FAMILY = BlockFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.SHROOMWOOD_PLANKS,
            "shroomwood",
            ModRegistry.SHROOMWOOD_WOOD_TYPE).getFamily();
    public static final BlockFamilyRegistrar BLUE_SHROOMWOOD_FAMILY = BlockFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.BLUE_SHROOMWOOD_PLANKS,
            "blue_shroomwood",
            ModRegistry.BLUE_SHROOMWOOD_WOOD_TYPE).getFamily();
    public static final BlockFamilyRegistrar ORANGE_SHROOMWOOD_FAMILY = BlockFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.ORANGE_SHROOMWOOD_PLANKS,
            "orange_shroomwood",
            ModRegistry.ORANGE_SHROOMWOOD_WOOD_TYPE).getFamily();
    public static final BlockFamilyRegistrar PURPLE_SHROOMWOOD_FAMILY = BlockFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.PURPLE_SHROOMWOOD_PLANKS,
            "purple_shroomwood",
            ModRegistry.PURPLE_SHROOMWOOD_WOOD_TYPE).getFamily();

    public static void bootstrap() {
        // NO-OP
    }

    public static Stream<BlockFamilyRegistrar> getAllFamilyRegistrars() {
        return Stream.of(SHROOMWOOD_FAMILY, BLUE_SHROOMWOOD_FAMILY, ORANGE_SHROOMWOOD_FAMILY, PURPLE_SHROOMWOOD_FAMILY);
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return getAllFamilyRegistrars().map(BlockFamilyRegistrar::getWoodenFamily);
    }
}
