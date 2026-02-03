package fuzs.shroomcraft.init.family;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.core.v1.context.GameplayContentContext;
import net.minecraft.core.Holder;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.joml.Vector2i;
import org.joml.Vector2ic;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface BlockSetFamily {
    Map<BlockSetVariant, Holder<BlockEntityType<?>>> VARIANT_BLOCK_ENTITY_TYPE = ImmutableMap.of(BlockSetVariant.SIGN,
            BuiltInRegistries.BLOCK_ENTITY_TYPE.wrapAsHolder(BlockEntityType.SIGN),
            BlockSetVariant.WALL_SIGN,
            BuiltInRegistries.BLOCK_ENTITY_TYPE.wrapAsHolder(BlockEntityType.SIGN),
            BlockSetVariant.HANGING_SIGN,
            BuiltInRegistries.BLOCK_ENTITY_TYPE.wrapAsHolder(BlockEntityType.HANGING_SIGN),
            BlockSetVariant.WALL_HANGING_SIGN,
            BuiltInRegistries.BLOCK_ENTITY_TYPE.wrapAsHolder(BlockEntityType.HANGING_SIGN),
            BlockSetVariant.SHELF,
            BuiltInRegistries.BLOCK_ENTITY_TYPE.wrapAsHolder(BlockEntityType.SHELF));
    Map<BlockSetVariant, Vector2ic> VARIANT_WOODEN_FLAMMABLE = ImmutableMap.of(BlockSetVariant.STAIRS,
            new Vector2i(5, 20),
            BlockSetVariant.SLAB,
            new Vector2i(5, 20),
            BlockSetVariant.FENCE,
            new Vector2i(5, 20),
            BlockSetVariant.FENCE_GATE,
            new Vector2i(5, 20),
            BlockSetVariant.SHELF,
            new Vector2i(30, 20));
    @SuppressWarnings("unchecked")
    Map<BlockSetVariant, Function<BlockSetFamilyRegistrar, DispenseItemBehavior>> VARIANT_DISPENSE_BEHAVIOR = ImmutableMap.of(
            BlockSetVariant.BOAT,
            (BlockSetFamilyRegistrar familyRegistrar) -> {
                return new BoatDispenseItemBehavior((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                        BlockSetVariant.BOAT).value());
            },
            BlockSetVariant.CHEST_BOAT,
            (BlockSetFamilyRegistrar familyRegistrar) -> {
                return new BoatDispenseItemBehavior((EntityType<? extends AbstractBoat>) familyRegistrar.entityVariants.get(
                        BlockSetVariant.CHEST_BOAT).value());
            });

    Holder.Reference<Block> getBaseBlock();

    BlockSetType getBlockSetType();

    WoodType getWoodType();

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

    default BlockFamily.Builder getVanillaFamily() {
        BlockFamily.Builder builder = new BlockFamily.Builder(this.getBaseBlock().value());
        this.getBlockVariants().forEach((BlockSetVariant variant, Holder.Reference<Block> holder) -> {
            if (variant instanceof VanillaBlockSetVariant vanillaVariant) {
                vanillaVariant.variantBuilder.accept(builder, holder.value());
            }
        });

        if (this.getBlockVariants().containsKey(BlockSetVariant.SIGN) && this.getBlockVariants()
                .containsKey(BlockSetVariant.WALL_SIGN)) {
            builder.sign(this.getBlock(BlockSetVariant.SIGN).value(), this.getBlock(BlockSetVariant.WALL_SIGN).value());
        }

        return builder;
    }

    default BlockFamily getWoodenVanillaFamily() {
        return this.getVanillaFamily().recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    }

    default void generateFor(BiConsumer<BlockEntityType<?>, Block> consumer, Map<BlockSetVariant, Holder<BlockEntityType<?>>> variants) {
        this.getBlockVariants().forEach((BlockSetVariant variant, Holder.Reference<Block> holder) -> {
            Holder<BlockEntityType<?>> blockEntity = variants.get(variant);
            if (blockEntity != null) {
                consumer.accept(blockEntity.value(), holder.value());
            }
        });
    }

    default void generateFor(GameplayContentContext context, Map<BlockSetVariant, Vector2ic> variants) {
        this.getBlockVariants().forEach((BlockSetVariant variant, Holder.Reference<Block> holder) -> {
            Vector2ic flammable = variants.get(variant);
            if (flammable != null) {
                context.registerFlammable(holder, flammable.x(), flammable.y());
            }
        });
    }

    default void generateFor(Map<BlockSetVariant, Function<BlockSetFamilyRegistrar, DispenseItemBehavior>> variants) {
        this.getBlockVariants().forEach((BlockSetVariant variant, Holder.Reference<Block> holder) -> {
            Function<BlockSetFamilyRegistrar, DispenseItemBehavior> flammable = variants.get(variant);
            if (flammable != null) {
                DispenserBlock.registerBehavior(this.getItem(variant).value(),
                        flammable.apply((BlockSetFamilyRegistrar) this));
            }
        });
    }
}
