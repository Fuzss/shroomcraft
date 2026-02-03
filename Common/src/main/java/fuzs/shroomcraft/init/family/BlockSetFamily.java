package fuzs.shroomcraft.init.family;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;

public interface BlockSetFamily {
    Map<BlockSetVariant, Holder.Reference<Block>> getBlockVariants();

    Map<BlockSetVariant, Holder.Reference<Item>> getItemVariants();

    Map<BlockSetVariant, Holder.Reference<EntityType<?>>> getEntityVariants();

    default Holder.Reference<Block> getBlock(BlockSetVariant variant) {
        return this.getBlockVariants().get(variant);
    }

    default Holder.Reference<Item> getItem(BlockSetVariant variant) {
        return this.getItemVariants().get(variant);
    }

    default Holder.Reference<EntityType<?>> getEntityType(BlockSetVariant variant) {
        return this.getEntityVariants().get(variant);
    }

    Holder.Reference<Block> getBaseBlock();

    BlockSetType getBlockSetType();

    WoodType getWoodType();
}
