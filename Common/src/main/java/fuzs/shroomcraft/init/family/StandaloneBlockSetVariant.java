package fuzs.shroomcraft.init.family;

import net.minecraft.data.BlockFamily;

public non-sealed abstract class StandaloneBlockSetVariant implements BlockSetVariant {
    private final String name;

    public StandaloneBlockSetVariant(String name) {
        this.name = name;
    }

    public StandaloneBlockSetVariant(BlockFamily.Variant variant) {
        this(variant.getRecipeGroup());
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
