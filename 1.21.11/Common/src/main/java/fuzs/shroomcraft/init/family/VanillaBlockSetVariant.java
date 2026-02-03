package fuzs.shroomcraft.init.family;

import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

non-sealed abstract class VanillaBlockSetVariant implements BlockSetVariant {
    private final BlockFamily.Variant variant;
    final BiConsumer<BlockFamily.Builder, Block> variantBuilder;

    public VanillaBlockSetVariant(BlockFamily.Variant variant, BiConsumer<BlockFamily.Builder, Block> variantBuilder) {
        this.variant = variant;
        this.variantBuilder = variantBuilder;
    }

    @Override
    public String getSerializedName() {
        return this.variant.getRecipeGroup();
    }
}
