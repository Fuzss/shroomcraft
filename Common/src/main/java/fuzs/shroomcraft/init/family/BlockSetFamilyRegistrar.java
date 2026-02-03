package fuzs.shroomcraft.init.family;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlockSetFamilyRegistrar implements BlockSetFamily {
    public final Map<BlockSetVariant, Holder.Reference<Block>> blockVariants = new LinkedHashMap<>();
    public final Map<BlockSetVariant, Holder.Reference<Item>> itemVariants = new LinkedHashMap<>();
    public final Map<BlockSetVariant, Holder.Reference<EntityType<?>>> entityVariants = new LinkedHashMap<>();
    public final Holder.Reference<Block> baseBlock;
    public final BlockSetType blockSetType;
    public final WoodType woodType;

    private BlockSetFamilyRegistrar(Holder.Reference<Block> baseBlock, BlockSetType blockSetType, WoodType woodType) {
        this.baseBlock = baseBlock;
        this.blockSetType = blockSetType;
        this.woodType = woodType;
    }

    public static Builder builder(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        BlockSetType blockSetType = new BlockSetType(registries.makeKey(basePath).toString());
        WoodType woodType = new WoodType(registries.makeKey(basePath).toString(), blockSetType);
        return new Builder(new BlockSetFamilyRegistrar(baseBlock, blockSetType, woodType), registries, basePath);
    }

    public static Builder any(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).generateFor(BlockSetVariant.STAIRS)
                .generateFor(BlockSetVariant.SLAB)
                .generateFor(BlockSetVariant.WALL);
    }

    public static Builder metal(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).generateFor(BlockSetVariant.STAIRS)
                .generateFor(BlockSetVariant.SLAB)
                .generateFor(BlockSetVariant.DOOR)
                .generateFor(BlockSetVariant.TRAPDOOR)
                .generateFor(BlockSetVariant.PRESSURE_PLATE);
    }

    public static Builder wooden(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).generateFor(BlockSetVariant.STAIRS)
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

    @Override
    public Holder.Reference<Block> getBaseBlock() {
        return this.baseBlock;
    }

    @Override
    public BlockSetType getBlockSetType() {
        return this.blockSetType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public Map<BlockSetVariant, Holder.Reference<Block>> getBlockVariants() {
        return Collections.unmodifiableMap(this.blockVariants);
    }

    @Override
    public Map<BlockSetVariant, Holder.Reference<Item>> getItemVariants() {
        return Collections.unmodifiableMap(this.itemVariants);
    }

    @Override
    public Map<BlockSetVariant, Holder.Reference<EntityType<?>>> getEntityVariants() {
        return Collections.unmodifiableMap(this.entityVariants);
    }

    public static final class Builder {
        final BlockSetFamilyRegistrar familyRegistrar;
        final RegistryManager registries;
        final String basePath;

        Builder(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            this.familyRegistrar = familyRegistrar;
            this.registries = registries;
            this.basePath = basePath;
        }

        public BlockSetFamily getFamily() {
            return this.familyRegistrar;
        }

        public Builder generateFor(BlockSetVariant blockSetVariant) {
            blockSetVariant.generateFor(this.familyRegistrar, this.registries, this.basePath);
            return this;
        }
    }
}
