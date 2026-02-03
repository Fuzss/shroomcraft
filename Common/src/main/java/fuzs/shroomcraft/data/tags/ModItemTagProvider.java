package fuzs.shroomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModItems;
import fuzs.shroomcraft.init.ModTags;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class ModItemTagProvider extends AbstractTagProvider<Item> {
    public static final Map<BlockSetVariant, TagKey<Item>> VARIANT_ITEM_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Item>>builder()
            .put(BlockSetVariant.BUTTON, ItemTags.BUTTONS)
            .put(BlockSetVariant.DOOR, ItemTags.DOORS)
            .put(BlockSetVariant.FENCE, ItemTags.FENCES)
            .put(BlockSetVariant.FENCE_GATE, ItemTags.FENCE_GATES)
            .put(BlockSetVariant.SLAB, ItemTags.SLABS)
            .put(BlockSetVariant.STAIRS, ItemTags.STAIRS)
            .put(BlockSetVariant.TRAPDOOR, ItemTags.TRAPDOORS)
            .put(BlockSetVariant.WALL, ItemTags.WALLS)
            .put(BlockSetVariant.SIGN, ItemTags.SIGNS)
            .put(BlockSetVariant.HANGING_SIGN, ItemTags.HANGING_SIGNS)
            .put(BlockSetVariant.BOAT, ItemTags.BOATS)
            .put(BlockSetVariant.CHEST_BOAT, ItemTags.CHEST_BOATS)
            .build();
    public static final Map<BlockSetVariant, TagKey<Item>> VARIANT_STONE_ITEM_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Item>>builder()
            .putAll(VARIANT_ITEM_TAGS)
            .put(BlockSetVariant.BUTTON, ItemTags.STONE_BUTTONS)
            .buildKeepingLast();
    public static final Map<BlockSetVariant, TagKey<Item>> VARIANT_WOODEN_ITEM_TAGS = ImmutableMap.<BlockSetVariant, TagKey<Item>>builder()
            .putAll(VARIANT_ITEM_TAGS)
            .put(BlockSetVariant.BUTTON, ItemTags.WOODEN_BUTTONS)
            .put(BlockSetVariant.DOOR, ItemTags.WOODEN_DOORS)
            .put(BlockSetVariant.FENCE, ItemTags.WOODEN_FENCES)
            .put(BlockSetVariant.SLAB, ItemTags.WOODEN_SLABS)
            .put(BlockSetVariant.STAIRS, ItemTags.WOODEN_STAIRS)
            .put(BlockSetVariant.PRESSURE_PLATE, ItemTags.WOODEN_PRESSURE_PLATES)
            .put(BlockSetVariant.TRAPDOOR, ItemTags.WOODEN_TRAPDOORS)
            .put(BlockSetVariant.SHELF, ItemTags.WOODEN_SHELVES)
            .buildKeepingLast();

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

    public final void generateFor(Map<BlockSetVariant, Holder.Reference<Item>> variantTypes, Map<BlockSetVariant, TagKey<Item>> variantTags) {
        variantTypes.forEach((BlockSetVariant variant, Holder.Reference<Item> holder) -> {
            TagKey<Item> tagKey = variantTags.get(variant);
            if (tagKey != null) {
                this.tag(tagKey).add(holder);
            }
        });
    }
}
