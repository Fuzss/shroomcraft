package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {

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
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getBlockVariants(), VARIANT_WOODEN_BLOCK_TAGS);
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
