package fuzs.shroomcraft.common.data.tags;

import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModItems;
import fuzs.shroomcraft.common.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ItemTags.PLANKS)
                .add(ModItems.SHROOMWOOD_PLANKS,
                        ModItems.BLUE_SHROOMWOOD_PLANKS,
                        ModItems.ORANGE_SHROOMWOOD_PLANKS,
                        ModItems.PURPLE_SHROOMWOOD_PLANKS);
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getItemVariants(), VARIANT_WOODEN_ITEM_TAGS);
        });
        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG);
        this.tag(ModTags.SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_MUSHROOM_STEM, ModItems.STRIPPED_MUSHROOM_HYPHAE);
        this.tag(ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_BLUE_MUSHROOM_STEM, ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE);
        this.tag(ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_ORANGE_MUSHROOM_STEM, ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE);
        this.tag(ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_PURPLE_MUSHROOM_STEM, ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE);
        this.tag(ItemTags.FISHES).add(ModItems.SHROOMFIN, ModItems.COOKED_SHROOMFIN);
        this.tag(ItemTags.WOLF_FOOD).add(ModItems.SHROOMFIN, ModItems.COOKED_SHROOMFIN);
        this.tag(ModTags.MUSHROOMS_ITEM_TAG)
                .add(BlockItemIds.BROWN_MUSHROOM.item(),
                        BlockItemIds.RED_MUSHROOM.item(),
                        BlockItemIds.CRIMSON_FUNGUS.item(),
                        BlockItemIds.WARPED_FUNGUS.item())
                .add(ModItems.BLUE_MUSHROOM, ModItems.ORANGE_MUSHROOM, ModItems.PURPLE_MUSHROOM);
        this.tag("c:buckets/entity_water").add(ModItems.SHROOMFIN_BUCKET);
    }
}
