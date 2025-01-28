package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import fuzs.shroomcraft.world.entity.animal.Shroomfin;
import fuzs.shroomcraft.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<EntityType<ModMushroomCow>> MOOSHROOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "mooshroom",
            () -> EntityType.Builder.of(ModMushroomCow::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10));
    public static final Holder.Reference<EntityType<Shroomfin>> SHROOMFIN_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "shroomfin",
            () -> EntityType.Builder.of(Shroomfin::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.4F)
                    .eyeHeight(0.26F)
                    .clientTrackingRange(4));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.ORANGE_MUSHROOM);
    public static final Holder.Reference<EntityDataSerializer<ModMushroomCow.ColorVariant>> MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "mushroom_variant",
            () -> EntityDataSerializer.forValueType(ModMushroomCow.ColorVariant.STREAM_CODEC));
    public static final Holder.Reference<RecipeSerializer<DistinctShapelessRecipe>> DISTINCT_SHAPELESS_RECIPE_SERIALIZER = REGISTRIES.register(
            Registries.RECIPE_SERIALIZER,
            "crafting_shapeless_distinct",
            DistinctShapelessRecipe.Serializer::new);
    public static final EntitySubPredicates.EntityVariantPredicateType<ModMushroomCow.ColorVariant> MOOSHROOM_ENTITY_SUB_PREDICATE = EntitySubPredicates.EntityVariantPredicateType.create(
            ModMushroomCow.ColorVariant.CODEC,
            entity -> entity instanceof ModMushroomCow modMushroomCow ? Optional.of(modMushroomCow.getColorVariant()) :
                    Optional.empty());

    public static final ResourceKey<LootTable> SHEAR_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom");
    public static final ResourceKey<LootTable> SHEAR_BLUE_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom/blue");
    public static final ResourceKey<LootTable> SHEAR_ORANGE_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom/orange");
    public static final ResourceKey<LootTable> SHEAR_PURPLE_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom/purple");
    public static final ResourceKey<LootTable> SHEAR_CRIMSON_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom/crismon");
    public static final ResourceKey<LootTable> SHEAR_WARPED_MOOSHROOM_LOOT_TABLE = REGISTRIES.registerLootTable(
            "shearing/mooshroom/warped");

    static final TagFactory TAGS = TagFactory.make(Shroomcraft.MOD_ID);
    public static final TagKey<Block> SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("shroomwood_logs");
    public static final TagKey<Block> BLUE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("blue_shroomwood_logs");
    public static final TagKey<Block> ORANGE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("orange_shroomwood_logs");
    public static final TagKey<Block> PURPLE_SHROOMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("purple_shroomwood_logs");
    public static final TagKey<Item> SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("shroomwood_logs");
    public static final TagKey<Item> BLUE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("blue_shroomwood_logs");
    public static final TagKey<Item> ORANGE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("orange_shroomwood_logs");
    public static final TagKey<Item> PURPLE_SHROOMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("purple_shroomwood_logs");
    public static final TagKey<Item> MUSHROOMS_ITEM_TAG = TAGS.registerItemTag("mushrooms");

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModBlockFamilies.bootstrap();
        ModFeatures.bootstrap();
        // does not matter for which variant the predicated is created, we only want the codec
        REGISTRIES.register(Registries.ENTITY_SUB_PREDICATE_TYPE,
                "mooshroom",
                () -> MOOSHROOM_ENTITY_SUB_PREDICATE.createPredicate(ModMushroomCow.ColorVariant.BLUE).codec());
    }
}
