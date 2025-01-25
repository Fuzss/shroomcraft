package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModRegistry;
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
        this.add(BlockTags.LOGS_THAT_BURN)
                .addTag(ModRegistry.SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModRegistry.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModRegistry.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG,
                        ModRegistry.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG);
        this.add(ModRegistry.SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_MUSHROOM_STEM.value(), ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        this.add(ModRegistry.BLUE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value(), ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        this.add(ModRegistry.ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value(),
                        ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        this.add(ModRegistry.PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value(),
                        ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
    }
}
