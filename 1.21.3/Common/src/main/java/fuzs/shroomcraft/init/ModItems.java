package fuzs.shroomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Holder.Reference<Item> BLUE_MUSHROOM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BLUE_MUSHROOM);
    public static final Holder.Reference<Item> ORANGE_MUSHROOM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.ORANGE_MUSHROOM);
    public static final Holder.Reference<Item> PURPLE_MUSHROOM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PURPLE_MUSHROOM);
    public static final Holder.Reference<Item> BLUE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BLUE_MUSHROOM_BLOCK);
    public static final Holder.Reference<Item> ORANGE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.ORANGE_MUSHROOM_BLOCK);
    public static final Holder.Reference<Item> PURPLE_MUSHROOM_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.PURPLE_MUSHROOM_BLOCK);
    public static final Holder.Reference<Item> BLUE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BLUE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.ORANGE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PURPLE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> STRIPPED_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_MUSHROOM_STEM);
    public static final Holder.Reference<Item> STRIPPED_BLUE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> STRIPPED_ORANGE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> STRIPPED_PURPLE_MUSHROOM_STEM = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM);
    public static final Holder.Reference<Item> STRIPPED_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_MUSHROOM_HYPHAE);
    public static final Holder.Reference<Item> STRIPPED_BLUE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_BLUE_MUSHROOM_HYPHAE);
    public static final Holder.Reference<Item> STRIPPED_ORANGE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_ORANGE_MUSHROOM_HYPHAE);
    public static final Holder.Reference<Item> STRIPPED_PURPLE_MUSHROOM_HYPHAE = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_PURPLE_MUSHROOM_HYPHAE);
    public static final Holder.Reference<Item> SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.SHROOMWOOD_PLANKS);
    public static final Holder.Reference<Item> BLUE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.BLUE_SHROOMWOOD_PLANKS);
    public static final Holder.Reference<Item> ORANGE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.ORANGE_SHROOMWOOD_PLANKS);
    public static final Holder.Reference<Item> PURPLE_SHROOMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.PURPLE_SHROOMWOOD_PLANKS);
    public static final Holder.Reference<Item> MYCELIAL_GROWTH = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.MYCELIAL_GROWTH);
    public static final Holder.Reference<Item> MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.MUSHROOM_SPROUTS);
    public static final Holder.Reference<Item> BLUE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.BLUE_MUSHROOM_SPROUTS);
    public static final Holder.Reference<Item> ORANGE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.ORANGE_MUSHROOM_SPROUTS);
    public static final Holder.Reference<Item> PURPLE_MUSHROOM_SPROUTS = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.PURPLE_MUSHROOM_SPROUTS);
    public static final Holder.Reference<Item> BROWN_SHROOMSPORES = ModRegistry.REGISTRIES.registerItem(
            "brown_shroomspores",
            (Item.Properties properties) -> new BlockItem(ModBlocks.TINY_BROWN_MUSHROOM.value(), properties),
            () -> new Item.Properties().useItemDescriptionPrefix());
    public static final Holder.Reference<Item> RED_SHROOMSPORES = ModRegistry.REGISTRIES.registerItem("red_shroomspores",
            (Item.Properties properties) -> new BlockItem(ModBlocks.TINY_RED_MUSHROOM.value(), properties),
            () -> new Item.Properties().useItemDescriptionPrefix());
    public static final Holder.Reference<Item> BLUE_SHROOMSPORES = ModRegistry.REGISTRIES.registerItem(
            "blue_shroomspores",
            (Item.Properties properties) -> new BlockItem(ModBlocks.TINY_BLUE_MUSHROOM.value(), properties),
            () -> new Item.Properties().useItemDescriptionPrefix());
    public static final Holder.Reference<Item> ORANGE_SHROOMSPORES = ModRegistry.REGISTRIES.registerItem(
            "orange_shroomspores",
            (Item.Properties properties) -> new BlockItem(ModBlocks.TINY_ORANGE_MUSHROOM.value(), properties),
            () -> new Item.Properties().useItemDescriptionPrefix());
    public static final Holder.Reference<Item> PURPLE_SHROOMSPORES = ModRegistry.REGISTRIES.registerItem(
            "purple_shroomspores",
            (Item.Properties properties) -> new BlockItem(ModBlocks.TINY_PURPLE_MUSHROOM.value(), properties),
            () -> new Item.Properties().useItemDescriptionPrefix());

    public static void bootstrap() {
        // NO-OP
    }
}
