package fuzs.shroomcraft.common.data.loot;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.BiConsumer;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {
    public static final Map<BlockSetVariant, BiConsumer<AbstractLootProvider.Blocks, Block>> VARIANT_PROVIDERS = ImmutableMap.<BlockSetVariant, BiConsumer<AbstractLootProvider.Blocks, Block>>builder()
            .putAll(AbstractLootProvider.Blocks.VARIANT_PROVIDERS)
            .put(BlockSetVariant.LOG, BlockLootSubProvider::dropWhenSilkTouch)
            .buildKeepingLast();

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
}
