package fuzs.shroomcraft.init;

import net.minecraft.data.BlockFamily;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockFamily SHROOMWOOD_FAMILY = ModBlocks.SHROOMWOOD_FAMILY.getWoodenFamily().getFamily();
    public static final BlockFamily BLUE_SHROOMWOOD_FAMILY = ModBlocks.BLUE_SHROOMWOOD_FAMILY.getWoodenFamily()
            .getFamily();
    public static final BlockFamily ORANGE_SHROOMWOOD_FAMILY = ModBlocks.ORANGE_SHROOMWOOD_FAMILY.getWoodenFamily()
            .getFamily();
    public static final BlockFamily PURPLE_SHROOMWOOD_FAMILY = ModBlocks.PURPLE_SHROOMWOOD_FAMILY.getWoodenFamily()
            .getFamily();

    public static Stream<BlockFamily> getAllFamilies() {
        return Stream.of(SHROOMWOOD_FAMILY, BLUE_SHROOMWOOD_FAMILY, ORANGE_SHROOMWOOD_FAMILY, PURPLE_SHROOMWOOD_FAMILY);
    }
}
