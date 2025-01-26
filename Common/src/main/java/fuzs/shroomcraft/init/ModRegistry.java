package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.ORANGE_MUSHROOM);

    public static final BlockSetType SHROOMWOOD_BLOCK_SET_TYPE = new BlockSetType("shroomwood");
    public static final BlockSetType BLUE_SHROOMWOOD_BLOCK_SET_TYPE = new BlockSetType("blue_shroomwood");
    public static final BlockSetType ORANGE_SHROOMWOOD_BLOCK_SET_TYPE = new BlockSetType("orange_shroomwood");
    public static final BlockSetType PURPLE_SHROOMWOOD_BLOCK_SET_TYPE = new BlockSetType("purple_shroomwood");
    public static final WoodType SHROOMWOOD_WOOD_TYPE = new WoodType("shroomwood", SHROOMWOOD_BLOCK_SET_TYPE);
    public static final WoodType BLUE_SHROOMWOOD_WOOD_TYPE = new WoodType("blue_shroomwood",
            BLUE_SHROOMWOOD_BLOCK_SET_TYPE);
    public static final WoodType ORANGE_SHROOMWOOD_WOOD_TYPE = new WoodType("orange_shroomwood",
            ORANGE_SHROOMWOOD_BLOCK_SET_TYPE);
    public static final WoodType PURPLE_SHROOMWOOD_WOOD_TYPE = new WoodType("purple_shroomwood",
            PURPLE_SHROOMWOOD_BLOCK_SET_TYPE);

    static final TagFactory TAGS = TagFactory.make(Shroomcraft.MOD_ID);
    public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("shroomwood_logs");
    public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("blue_shroomwood_logs");
    public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("orange_shroomwood_logs");
    public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("purple_shroomwood_logs");
    public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("shroomwood_logs");
    public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("blue_shroomwood_logs");
    public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("orange_shroomwood_logs");
    public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("purple_shroomwood_logs");

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModBlockFamilies.bootstrap();
        ModFeatures.bootstrap();
    }
}
