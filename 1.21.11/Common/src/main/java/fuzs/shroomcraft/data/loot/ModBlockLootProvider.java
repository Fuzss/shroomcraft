package fuzs.shroomcraft.data.loot;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetVariant;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.BiConsumer;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {
    public static final Map<BlockSetVariant, BiConsumer<AbstractLootProvider.Blocks, Block>> VARIANT_PROVIDERS = ImmutableMap.<BlockSetVariant, BiConsumer<AbstractLootProvider.Blocks, Block>>builder()
            .put(BlockSetVariant.STAIRS, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.SLAB, (AbstractLootProvider.Blocks provider, Block block) -> {
                provider.add(block, provider::createSlabItemTable);
            })
            .put(BlockSetVariant.WALL, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.FENCE, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.FENCE_GATE, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.DOOR, (AbstractLootProvider.Blocks provider, Block block) -> {
                provider.add(block, provider::createDoorTable);
            })
            .put(BlockSetVariant.TRAPDOOR, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.BUTTON, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.PRESSURE_PLATE, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.SIGN, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.HANGING_SIGN, BlockLootSubProvider::dropSelf)
            .put(BlockSetVariant.SHELF, BlockLootSubProvider::dropSelf)
            .build();

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily, VARIANT_PROVIDERS);
        });
        this.dropSelf(ModBlocks.BLUE_MUSHROOM.value());
        this.dropSelf(ModBlocks.ORANGE_MUSHROOM.value());
        this.dropSelf(ModBlocks.PURPLE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_BLUE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_ORANGE_MUSHROOM.value());
        this.dropPottedContents(ModBlocks.POTTED_PURPLE_MUSHROOM.value());
        this.add(ModBlocks.BLUE_MUSHROOM_BLOCK.value(),
                (Block block) -> this.createMushroomBlockDrop(block, ModBlocks.BLUE_MUSHROOM_BLOCK.value()));
        this.add(ModBlocks.ORANGE_MUSHROOM_BLOCK.value(),
                (Block block) -> this.createMushroomBlockDrop(block, ModBlocks.ORANGE_MUSHROOM_BLOCK.value()));
        this.add(ModBlocks.PURPLE_MUSHROOM_BLOCK.value(),
                (Block block) -> this.createMushroomBlockDrop(block, ModBlocks.PURPLE_MUSHROOM_BLOCK.value()));
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
        this.add(ModBlocks.MYCELIAL_GROWTH.value(),
                (Block block) -> this.createMultifaceBlockDrops(block, this.hasShears()));
        this.add(ModBlocks.MUSHROOM_SPROUTS.value(), this::createShearsOnlyDrop);
        this.add(ModBlocks.BLUE_MUSHROOM_SPROUTS.value(), this::createShearsOnlyDrop);
        this.add(ModBlocks.ORANGE_MUSHROOM_SPROUTS.value(), this::createShearsOnlyDrop);
        this.add(ModBlocks.PURPLE_MUSHROOM_SPROUTS.value(), this::createShearsOnlyDrop);
        this.dropPottedContents(ModBlocks.POTTED_MUSHROOM_SPROUTS.value());
        this.dropPottedContents(ModBlocks.POTTED_BLUE_MUSHROOM_SPROUTS.value());
        this.dropPottedContents(ModBlocks.POTTED_ORANGE_MUSHROOM_SPROUTS.value());
        this.dropPottedContents(ModBlocks.POTTED_PURPLE_MUSHROOM_SPROUTS.value());
        this.dropSelf(ModBlocks.TINY_BROWN_MUSHROOM.value());
        this.dropSelf(ModBlocks.TINY_RED_MUSHROOM.value());
        this.dropSelf(ModBlocks.TINY_BLUE_MUSHROOM.value());
        this.dropSelf(ModBlocks.TINY_ORANGE_MUSHROOM.value());
        this.dropSelf(ModBlocks.TINY_PURPLE_MUSHROOM.value());
    }

    public final void generateFor(BlockSetFamily blockSetFamily, Map<BlockSetVariant, BiConsumer<AbstractLootProvider.Blocks, Block>> variantProviders) {
        blockSetFamily.getBlockVariants().forEach((BlockSetVariant variant, Holder.Reference<Block> block) -> {
            BiConsumer<AbstractLootProvider.Blocks, Block> provider = variantProviders.get(variant);
            if (provider != null) {
                provider.accept(this, block.value());
            }
        });
    }
}
