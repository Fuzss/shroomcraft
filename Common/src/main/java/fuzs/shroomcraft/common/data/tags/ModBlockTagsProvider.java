package fuzs.shroomcraft.common.data.tags;

import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModBlocks;
import fuzs.shroomcraft.common.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends AbstractTagProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.SHROOMWOOD_PLANKS,
                        ModBlocks.BLUE_SHROOMWOOD_PLANKS,
                        ModBlocks.ORANGE_SHROOMWOOD_PLANKS,
                        ModBlocks.PURPLE_SHROOMWOOD_PLANKS);
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getBlockVariants(), VARIANT_WOODEN_BLOCK_TAGS);
        });
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BLUE_MUSHROOM, ModBlocks.ORANGE_MUSHROOM, ModBlocks.PURPLE_MUSHROOM)
                .add(ModBlocks.BLUE_MUSHROOM_BLOCK,
                        ModBlocks.ORANGE_MUSHROOM_BLOCK,
                        ModBlocks.PURPLE_MUSHROOM_BLOCK,
                        ModBlocks.BLUE_MUSHROOM_STEM,
                        ModBlocks.ORANGE_MUSHROOM_STEM,
                        ModBlocks.PURPLE_MUSHROOM_STEM);
        this.tag(BlockItemTags.LOGS_THAT_BURN.block())
                .addTag(ModTags.SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModTags.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG);
        this.tag(ModTags.SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_MUSHROOM_STEM, ModBlocks.STRIPPED_MUSHROOM_HYPHAE);
        this.tag(ModTags.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM, ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE);
        this.tag(ModTags.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM, ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE);
        this.tag(ModTags.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM, ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE);
        this.tag(ModTags.SUPPORTS_MUSHROOM_SPROUTS_BLOCK_TAG).addTag(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
        this.tag(ModTags.SUPPORTS_TINY_MUSHROOM_BLOCK_TAG).addTag(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
        this.tag(BlockTags.CROPS)
                .add(ModBlocks.TINY_BROWN_MUSHROOM,
                        ModBlocks.TINY_RED_MUSHROOM,
                        ModBlocks.TINY_BLUE_MUSHROOM,
                        ModBlocks.TINY_ORANGE_MUSHROOM,
                        ModBlocks.TINY_PURPLE_MUSHROOM);
        this.tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_BLUE_MUSHROOM, ModBlocks.POTTED_ORANGE_MUSHROOM, ModBlocks.POTTED_PURPLE_MUSHROOM)
                .add(ModBlocks.POTTED_MUSHROOM_SPROUTS,
                        ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS,
                        ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS,
                        ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS);
        this.tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(ModBlocks.BLUE_MUSHROOM, ModBlocks.ORANGE_MUSHROOM, ModBlocks.PURPLE_MUSHROOM)
                .add(ModBlocks.MUSHROOM_SPROUTS,
                        ModBlocks.BLUE_MUSHROOM_SPROUTS,
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS,
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS);
        this.tag(BlockTags.REPLACEABLE_BY_TREES)
                .add(ModBlocks.MUSHROOM_SPROUTS,
                        ModBlocks.BLUE_MUSHROOM_SPROUTS,
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS,
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS);
        this.tag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
                .add(ModBlocks.MUSHROOM_SPROUTS,
                        ModBlocks.BLUE_MUSHROOM_SPROUTS,
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS,
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS);
        this.tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.MUSHROOM_SPROUTS,
                        ModBlocks.BLUE_MUSHROOM_SPROUTS,
                        ModBlocks.ORANGE_MUSHROOM_SPROUTS,
                        ModBlocks.PURPLE_MUSHROOM_SPROUTS)
                .add(ModBlocks.BLUE_MUSHROOM, ModBlocks.ORANGE_MUSHROOM, ModBlocks.PURPLE_MUSHROOM);
        this.tag(ModTags.HUGE_PURPLE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)
                .addTag(BlockTags.SUBSTRATE_OVERWORLD)
                .add(BlockItemIds.MYCELIUM.block(),
                        BlockItemIds.PODZOL.block(),
                        BlockItemIds.CRIMSON_NYLIUM.block(),
                        BlockItemIds.WARPED_NYLIUM.block());
        this.tag(ModTags.HUGE_ORANGE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)
                .addTag(BlockTags.SUBSTRATE_OVERWORLD)
                .add(BlockItemIds.MYCELIUM.block(),
                        BlockItemIds.PODZOL.block(),
                        BlockItemIds.CRIMSON_NYLIUM.block(),
                        BlockItemIds.WARPED_NYLIUM.block());
        this.tag(ModTags.HUGE_BLUE_MUSHROOM_CAN_PLACE_ON_BLOCK_TAG)
                .addTag(BlockTags.SUBSTRATE_OVERWORLD)
                .add(BlockItemIds.MYCELIUM.block(),
                        BlockItemIds.PODZOL.block(),
                        BlockItemIds.CRIMSON_NYLIUM.block(),
                        BlockItemIds.WARPED_NYLIUM.block());
    }
}
