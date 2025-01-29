package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import fuzs.shroomcraft.world.entity.animal.Shroomfin;
import fuzs.shroomcraft.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
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
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class ModRegistry {
    public static final ResourceKey<Registry<MobBlockVariant>> CLUCKSHROOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Shroomcraft.id("cluckshroom_variant"));
    public static final ResourceKey<Registry<MobBlockVariant>> CLUCKBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Shroomcraft.id("cluckbloom_variant"));
    public static final ResourceKey<Registry<MobBlockVariant>> MOOBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Shroomcraft.id("moobloom_variant"));

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
    public static final Holder.Reference<EntityType<Cluckshroom>> CLUCKSHROOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "cluckshroom",
            () -> EntityType.Builder.of(Cluckshroom::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.ORANGE_MUSHROOM);
    public static final Holder.Reference<EntityDataSerializer<ModMushroomCow.ColorVariant>> MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "mushroom_variant",
            () -> EntityDataSerializer.forValueType(ModMushroomCow.ColorVariant.STREAM_CODEC));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobBlockVariant>>> CLUCKSHROOM_VARIANT_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "cluckshroom_variant",
            () -> EntityDataSerializer.forValueType(MobBlockVariant.streamCodec(CLUCKSHROOM_VARIANT_REGISTRY_KEY)));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobBlockVariant>>> CLUCKBLOOM_VARIANT_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "cluckbloom_variant",
            () -> EntityDataSerializer.forValueType(MobBlockVariant.streamCodec(CLUCKBLOOM_VARIANT_REGISTRY_KEY)));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobBlockVariant>>> MOOBLOOM_VARIANT_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "moobloom_variant",
            () -> EntityDataSerializer.forValueType(MobBlockVariant.streamCodec(MOOBLOOM_VARIANT_REGISTRY_KEY)));
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
    public static final ResourceKey<MobBlockVariant> RED_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "red");
    public static final ResourceKey<MobBlockVariant> BROWN_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "brown");
    public static final ResourceKey<MobBlockVariant> CRIMSON_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "crimson");
    public static final ResourceKey<MobBlockVariant> WARPED_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "warped");
    public static final ResourceKey<MobBlockVariant> BLUE_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "blue");
    public static final ResourceKey<MobBlockVariant> ORANGE_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "orange");
    public static final ResourceKey<MobBlockVariant> PURPLE_CLUCKSHROOM_VARIANT = REGISTRIES.makeResourceKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "purple");

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
    public static final TagKey<MobBlockVariant> DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG = TAGS.registerTagKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "default_spawns");
    public static final TagKey<MobBlockVariant> NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG = TAGS.registerTagKey(
            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "nether_spawns");

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
