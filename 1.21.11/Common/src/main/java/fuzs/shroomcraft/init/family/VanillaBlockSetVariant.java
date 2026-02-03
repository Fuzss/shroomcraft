package fuzs.shroomcraft.init.family;

import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

non-sealed abstract class VanillaBlockSetVariant implements BlockSetVariant {
    private final String name;
    final BiConsumer<BlockFamily.Builder, Block> variantBuilder;

    public VanillaBlockSetVariant(String name, BiConsumer<BlockFamily.Builder, Block> variantBuilder) {
        this.name = name;
        this.variantBuilder = variantBuilder;
    }

    public VanillaBlockSetVariant(BlockFamily.Variant variant, BiConsumer<BlockFamily.Builder, Block> variantBuilder) {
        this(variant.getRecipeGroup(), variantBuilder);
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
