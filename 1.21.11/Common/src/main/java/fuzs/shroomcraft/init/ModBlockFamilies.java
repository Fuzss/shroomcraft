package fuzs.shroomcraft.init;

import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetFamilyRegistrar;
import net.minecraft.data.BlockFamily;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockSetFamily SHROOMWOOD_FAMILY = BlockSetFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.SHROOMWOOD_PLANKS,
            "shroomwood").getFamily();
    public static final BlockSetFamily BLUE_SHROOMWOOD_FAMILY = BlockSetFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.BLUE_SHROOMWOOD_PLANKS,
            "blue_shroomwood").getFamily();
    public static final BlockSetFamily ORANGE_SHROOMWOOD_FAMILY = BlockSetFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.ORANGE_SHROOMWOOD_PLANKS,
            "orange_shroomwood").getFamily();
    public static final BlockSetFamily PURPLE_SHROOMWOOD_FAMILY = BlockSetFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.PURPLE_SHROOMWOOD_PLANKS,
            "purple_shroomwood").getFamily();

    public static void bootstrap() {
        // NO-OP
    }

    public static Stream<BlockSetFamily> getAllBlockSetFamilies() {
        return Stream.of(SHROOMWOOD_FAMILY, BLUE_SHROOMWOOD_FAMILY, ORANGE_SHROOMWOOD_FAMILY, PURPLE_SHROOMWOOD_FAMILY);
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return getAllBlockSetFamilies().map(BlockSetFamilyRegistrar.class::cast)
                .map(BlockSetFamilyRegistrar::getWoodenVanillaFamily);
    }
}
