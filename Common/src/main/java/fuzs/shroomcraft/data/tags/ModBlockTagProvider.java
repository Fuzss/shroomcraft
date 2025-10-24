package fuzs.shroomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .put(BlockFamily.Variant.BUTTON, BlockTags.BUTTONS)
            .put(BlockFamily.Variant.DOOR, BlockTags.DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockTags.FENCES)
            .put(BlockFamily.Variant.FENCE, BlockTags.FENCES)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockTags.FENCE_GATES)
            .put(BlockFamily.Variant.FENCE_GATE, BlockTags.FENCE_GATES)
            .put(BlockFamily.Variant.SIGN, BlockTags.STANDING_SIGNS)
            .put(BlockFamily.Variant.SLAB, BlockTags.SLABS)
            .put(BlockFamily.Variant.STAIRS, BlockTags.STAIRS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.PRESSURE_PLATES)
            .put(BlockFamily.Variant.TRAPDOOR, BlockTags.TRAPDOORS)
            .put(BlockFamily.Variant.WALL, BlockTags.WALLS)
            .put(BlockFamily.Variant.WALL_SIGN, BlockTags.WALL_SIGNS)
            .build();
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_STONE_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, BlockTags.STONE_BUTTONS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.STONE_PRESSURE_PLATES)
            .buildKeepingLast();
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_WOODEN_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, BlockTags.WOODEN_BUTTONS)
            .put(BlockFamily.Variant.DOOR, BlockTags.WOODEN_DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.FENCE, BlockTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.SLAB, BlockTags.WOODEN_SLABS)
            .put(BlockFamily.Variant.STAIRS, BlockTags.WOODEN_STAIRS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.WOODEN_PRESSURE_PLATES)
            .put(BlockFamily.Variant.TRAPDOOR, BlockTags.WOODEN_TRAPDOORS)
            .buildKeepingLast();

    public ModBlockTagProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.SHROOMWOOD_PLANKS.value(),
                        ModBlocks.BLUE_SHROOMWOOD_PLANKS.value(),
                        ModBlocks.ORANGE_SHROOMWOOD_PLANKS.value(),
                        ModBlocks.PURPLE_SHROOMWOOD_PLANKS.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            for (Map.Entry<BlockFamily.Variant, TagKey<Block>> entry : VARIANT_WOODEN_TAGS.entrySet()) {
                Holder.Reference<Block> block = registrar.getBlock(entry.getKey());
                if (block != null) {
                    this.tag(entry.getValue()).add(block);
                }
            }
        });
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            if (registrar.hangingSignBlock() != null) {
                this.tag(BlockTags.CEILING_HANGING_SIGNS).add(registrar.hangingSignBlock());
            }

            if (registrar.wallHangingSignBlock() != null) {
                this.tag(BlockTags.WALL_HANGING_SIGNS).add(registrar.wallHangingSignBlock());
            }

            if (registrar.shelfBlock() != null) {
                this.tag(BlockTags.WOODEN_SHELVES).add(registrar.shelfBlock());
            }
        });
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BLUE_MUSHROOM.value(),
                        ModBlocks.ORANGE_MUSHROOM.value(),
                        ModBlocks.PURPLE_MUSHROOM.value())
                .add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(),
                        ModBlocks.ORANGE_MUSHROOM_BLOCK.value(),
                        ModBlocks.PURPLE_MUSHROOM_BLOCK.value(),
                        ModBlocks.BLUE_MUSHROOM_STEM.value(),
                        ModBlocks.ORANGE_MUSHROOM_STEM.value(),
                        ModBlocks.PURPLE_MUSHROOM_STEM.value());
        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG);
        this.tag(ModTags.SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(),
                        ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(),
                        ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
        this.tag(BlockTags.CROPS)
                .add(ModBlocks.TINY_BROWN_MUSHROOM.value(),
                        ModBlocks.TINY_RED_MUSHROOM.value(),
                        ModBlocks.TINY_BLUE_MUSHROOM.value(),
                        ModBlocks.TINY_ORANGE_MUSHROOM.value(),
                        ModBlocks.TINY_PURPLE_MUSHROOM.value());
        this.tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_BLUE_MUSHROOM.value(),
                        ModBlocks.POTTED_ORANGE_MUSHROOM.value(),
                        ModBlocks.POTTED_PURPLE_MUSHROOM.value())
                .add(ModBlocks.POTTED_MUSHROOM_SPROUTS.value(),
                        ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value());
        this.tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(ModBlocks.BLUE_MUSHROOM.value(),
                        ModBlocks.ORANGE_MUSHROOM.value(),
                        ModBlocks.PURPLE_MUSHROOM.value())
                .add(ModBlocks.MUSHROOM_SPROUTS.value(),
                        ModBlocks.BLUE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS.value());
        this.tag(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlocks.MUSHROOM_SPROUTS.value(),
                        ModBlocks.BLUE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS.value());
        this.tag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
                .add(ModBlocks.MUSHROOM_SPROUTS.value(),
                        ModBlocks.BLUE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS.value());
        this.tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.MUSHROOM_SPROUTS.value(),
                        ModBlocks.BLUE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(),
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS.value())
                .add(ModBlocks.BLUE_MUSHROOM.value(),
                        ModBlocks.ORANGE_MUSHROOM.value(),
                        ModBlocks.PURPLE_MUSHROOM.value());
    }
}
