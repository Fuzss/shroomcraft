package fuzs.shroomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModTags;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetFamilyRegistrar;
import fuzs.shroomcraft.init.family.BlockSetVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {
    public static final Map<BlockSetVariant, TagKey<Block>> VARIANT_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Block>>builder()
            .put(BlockSetVariant.BUTTON, BlockTags.BUTTONS)
            .put(BlockSetVariant.DOOR, BlockTags.DOORS)
            .put(BlockSetVariant.FENCE, BlockTags.FENCES)
            .put(BlockSetVariant.FENCE_GATE, BlockTags.FENCE_GATES)
            .put(BlockSetVariant.SIGN, BlockTags.STANDING_SIGNS)
            .put(BlockSetVariant.SLAB, BlockTags.SLABS)
            .put(BlockSetVariant.STAIRS, BlockTags.STAIRS)
            .put(BlockSetVariant.PRESSURE_PLATE, BlockTags.PRESSURE_PLATES)
            .put(BlockSetVariant.TRAPDOOR, BlockTags.TRAPDOORS)
            .put(BlockSetVariant.WALL, BlockTags.WALLS)
            .put(BlockSetVariant.WALL_SIGN, BlockTags.WALL_SIGNS)
            .put(BlockSetVariant.HANGING_SIGN, BlockTags.CEILING_HANGING_SIGNS)
            .put(BlockSetVariant.WALL_HANGING_SIGN, BlockTags.WALL_HANGING_SIGNS)
            .build();
    public static final Map<BlockSetVariant, TagKey<Block>> VARIANT_STONE_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockSetVariant.BUTTON, BlockTags.STONE_BUTTONS)
            .put(BlockSetVariant.PRESSURE_PLATE, BlockTags.STONE_PRESSURE_PLATES)
            .buildKeepingLast();
    public static final Map<BlockSetVariant, TagKey<Block>> VARIANT_WOODEN_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockSetVariant.BUTTON, BlockTags.WOODEN_BUTTONS)
            .put(BlockSetVariant.DOOR, BlockTags.WOODEN_DOORS)
            .put(BlockSetVariant.FENCE, BlockTags.WOODEN_FENCES)
            .put(BlockSetVariant.SLAB, BlockTags.WOODEN_SLABS)
            .put(BlockSetVariant.STAIRS, BlockTags.WOODEN_STAIRS)
            .put(BlockSetVariant.PRESSURE_PLATE, BlockTags.WOODEN_PRESSURE_PLATES)
            .put(BlockSetVariant.TRAPDOOR, BlockTags.WOODEN_TRAPDOORS)
            .put(BlockSetVariant.SHELF, BlockTags.WOODEN_SHELVES)
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
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockSetFamily registrar) -> {
            for (Map.Entry<BlockSetVariant, TagKey<Block>> entry : VARIANT_WOODEN_TAGS.entrySet()) {
                Holder.Reference<Block> block = registrar.getBlock(entry.getKey());
                if (block != null) {
                    this.tag(entry.getValue()).add(block);
                }
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
