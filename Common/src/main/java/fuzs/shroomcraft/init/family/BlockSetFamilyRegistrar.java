package fuzs.shroomcraft.init.family;

import com.google.common.collect.ImmutableSet;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public final class BlockSetFamilyRegistrar implements BlockSetFamily {
    static final Set<BlockSetVariant> FLAMMABLE_VARIANTS = ImmutableSet.of(BlockSetVariant.FENCE,
            BlockSetVariant.FENCE_GATE,
            BlockSetVariant.STAIRS,
            BlockSetVariant.SLAB);

    final Map<BlockSetVariant, Holder.Reference<Block>> blockVariants = new LinkedHashMap<>();
    final Map<BlockSetVariant, Holder.Reference<Item>> itemVariants = new LinkedHashMap<>();
    final Map<BlockSetVariant, Holder.Reference<EntityType<?>>> entityVariants = new LinkedHashMap<>();
    final Holder.Reference<Block> baseBlock;
    final BlockSetType blockSetType;
    final WoodType woodType;

    private BlockSetFamilyRegistrar(Holder.Reference<Block> baseBlock, BlockSetType blockSetType, WoodType woodType) {
        this.baseBlock = baseBlock;
        this.blockSetType = blockSetType;
        this.woodType = woodType;
    }

    public static Writable builder(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        BlockSetType blockSetType = new BlockSetType(registries.makeKey(basePath).toString());
        WoodType woodType = new WoodType(registries.makeKey(basePath).toString(), blockSetType);
        return new Writable(new BlockSetFamilyRegistrar(baseBlock, blockSetType, woodType), registries, basePath);
    }

    public static Writable any(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).generateFor(BlockSetVariant.STAIRS)
                .generateFor(BlockSetVariant.SLAB)
                .generateFor(BlockSetVariant.WALL);
    }

    public static Writable metal(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
        return builder(registries, baseBlock, basePath).generateFor(BlockSetVariant.STAIRS)
                .generateFor(BlockSetVariant.SLAB)
                .generateFor(BlockSetVariant.DOOR)
                .generateFor(BlockSetVariant.TRAPDOOR)
                .generateFor(BlockSetVariant.PRESSURE_PLATE);
    }

    public static Writable wooden(RegistryManager registries, Holder.Reference<Block> baseBlock, String basePath) {
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

    /**
     * Shelf is missing from this, must be handled separately.
     */
    public void forEachFlammableVariant(Consumer<Holder.Reference<Block>> consumer) {
        this.blockVariants.forEach((BlockSetVariant variant, Holder.Reference<Block> holder) -> {
            if (FLAMMABLE_VARIANTS.contains(variant)) {
                consumer.accept(holder);
            }
        });
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

    public BlockFamily.Builder getVanillaFamily() {
        BlockFamily.Builder builder = new BlockFamily.Builder(this.baseBlock.value());
        for (Map.Entry<BlockSetVariant, Holder.Reference<Block>> entry : this.blockVariants.entrySet()) {
            if (entry.getKey() instanceof VanillaBlockSetVariant variant) {
                variant.variantBuilder.accept(builder, entry.getValue().value());
            }
        }

        if (this.blockVariants.containsKey(BlockSetVariant.SIGN)
                && this.blockVariants.containsKey(BlockSetVariant.WALL_SIGN)) {
            builder.sign(this.getBlock(BlockSetVariant.SIGN).value(), this.getBlock(BlockSetVariant.WALL_SIGN).value());
        }

        return builder;
    }

    public BlockFamily getWoodenVanillaFamily() {
        return this.getVanillaFamily().recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    }

    public static final class Writable {
        final BlockSetFamilyRegistrar familyRegistrar;
        final RegistryManager registries;
        final String basePath;

        Writable(BlockSetFamilyRegistrar familyRegistrar, RegistryManager registries, String basePath) {
            this.familyRegistrar = familyRegistrar;
            this.registries = registries;
            this.basePath = basePath;
        }

        public BlockSetFamily getFamily() {
            return this.familyRegistrar;
        }

        public Writable generateFor(BlockSetVariant blockSetVariant) {
            blockSetVariant.generateFor(this.familyRegistrar, this.registries, this.basePath);
            return this;
        }
    }
}
