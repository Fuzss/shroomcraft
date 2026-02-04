package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemTagProvider extends AbstractTagProvider<Item> {

    public ModItemTagProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ItemTags.PLANKS)
                .add(ModItems.SHROOMWOOD_PLANKS.value(),
                        ModItems.BLUE_SHROOMWOOD_PLANKS.value(),
                        ModItems.ORANGE_SHROOMWOOD_PLANKS.value(),
                        ModItems.PURPLE_SHROOMWOOD_PLANKS.value());
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getItemVariants(), VARIANT_WOODEN_ITEM_TAGS);
        });
        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG,
                        ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG);
        this.tag(ModTags.SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_MUSHROOM_STEM.value(), ModItems.STRIPPED_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.BLUE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_BLUE_MUSHROOM_STEM.value(), ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.ORANGE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_ORANGE_MUSHROOM_STEM.value(), ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
        this.tag(ModTags.PURPLE_SHROOMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STRIPPED_PURPLE_MUSHROOM_STEM.value(), ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
        this.tag(ItemTags.FISHES).add(ModItems.SHROOMFIN.value(), ModItems.COOKED_SHROOMFIN.value());
        this.tag(ItemTags.WOLF_FOOD).add(ModItems.SHROOMFIN.value(), ModItems.COOKED_SHROOMFIN.value());
        this.tag(ModTags.MUSHROOMS_ITEM_TAG)
                .add(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS)
                .add(ModItems.BLUE_MUSHROOM.value(),
                        ModItems.ORANGE_MUSHROOM.value(),
                        ModItems.PURPLE_MUSHROOM.value());
        this.tag("c:buckets/entity_water").add(ModItems.SHROOMFIN_BUCKET.value());
    }
}
