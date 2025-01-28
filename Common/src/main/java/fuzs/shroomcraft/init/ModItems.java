package fuzs.shroomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;

public class ModItems {
    public static final FoodProperties SHROOMFIN_FOOD_PROPERTIES = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1F)
            .build();
    public static final FoodProperties COOKED_SHROOMFIN_FOOD_PROPERTIES = new FoodProperties.Builder().nutrition(5)
            .saturationModifier(0.6F)
            .build();

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
    public static final Holder.Reference<Item> SHROOMFIN = ModRegistry.REGISTRIES.registerItem("shroomfin",
            () -> new Item.Properties().food(SHROOMFIN_FOOD_PROPERTIES));
    public static final Holder.Reference<Item> COOKED_SHROOMFIN = ModRegistry.REGISTRIES.registerItem("cooked_shroomfin",
            () -> new Item.Properties().food(COOKED_SHROOMFIN_FOOD_PROPERTIES));
    public static final Holder.Reference<Item> SHROOMFIN_BUCKET = ModRegistry.REGISTRIES.registerItem("shroomfin_bucket",
            (Item.Properties properties) -> new MobBucketItem(ModRegistry.SHROOMFIN_ENTITY_TYPE.value(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    properties),
            () -> new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY));
    public static final Holder.Reference<Item> SHROOMFIN_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModRegistry.SHROOMFIN_ENTITY_TYPE,
            -8360594,
            -3368584);

    public static void bootstrap() {
        // NO-OP
    }
}
