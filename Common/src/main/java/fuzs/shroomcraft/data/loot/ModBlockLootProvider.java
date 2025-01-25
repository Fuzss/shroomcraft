package fuzs.shroomcraft.data.loot;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        ModBlockFamilies.getAllFamilies().forEach((BlockFamily blockFamily) -> {
            blockFamily.getVariants().forEach((BlockFamily.Variant variant, Block block) -> {
                if (variant == BlockFamily.Variant.SLAB) {
                    this.add(block, this::createSlabItemTable);
                } else if (variant != BlockFamily.Variant.WALL_SIGN) {
                    this.dropSelf(block);
                }
            });
        });
        this.dropSelf(ModBlocks.BLUE_MUSHROOM.value());
        this.dropSelf(ModBlocks.ORANGE_MUSHROOM.value());
        this.dropSelf(ModBlocks.PURPLE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_BLUE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_ORANGE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_PURPLE_MUSHROOM.value());
        this.dropWhenSilkTouch(ModBlocks.BLUE_MUSHROOM_BLOCK.value());
        this.dropWhenSilkTouch(ModBlocks.ORANGE_MUSHROOM_BLOCK.value());
        this.dropWhenSilkTouch(ModBlocks.PURPLE_MUSHROOM_BLOCK.value());
        this.dropWhenSilkTouch(ModBlocks.BLUE_MUSHROOM_STEM.value());
        this.dropWhenSilkTouch(ModBlocks.ORANGE_MUSHROOM_STEM.value());
        this.dropWhenSilkTouch(ModBlocks.PURPLE_MUSHROOM_STEM.value());
        this.dropSelf(ModBlocks.STRIPPED_MUSHROOM_STEM.value());
        this.dropSelf(ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM.value());
        this.dropSelf(ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM.value());
        this.dropSelf(ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM.value());
        this.dropSelf(ModBlocks.STRIPPED_MUSHROOM_HYPHAE.value());
        this.dropSelf(ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        this.dropSelf(ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        this.dropSelf(ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
        this.dropSelf(ModBlocks.SHROOMWOOD_PLANKS.value());
        this.dropSelf(ModBlocks.BLUE_SHROOMWOOD_PLANKS.value());
        this.dropSelf(ModBlocks.ORANGE_SHROOMWOOD_PLANKS.value());
        this.dropSelf(ModBlocks.PURPLE_SHROOMWOOD_PLANKS.value());
    }
}
