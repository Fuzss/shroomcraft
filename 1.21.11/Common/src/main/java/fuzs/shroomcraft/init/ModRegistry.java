package fuzs.shroomcraft.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.shroomcraft.Shroomcraft;
import fuzs.shroomcraft.world.entity.animal.Cluckshroom;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import fuzs.shroomcraft.world.entity.animal.ModMushroomCow;
import fuzs.shroomcraft.world.entity.animal.Shroomfin;
import fuzs.shroomcraft.world.item.crafting.DistinctShapelessRecipe;
import fuzs.shroomcraft.world.level.levelgen.feature.HugeBlueMushroomFeature;
import fuzs.shroomcraft.world.level.levelgen.feature.HugeOrangeMushroomFeature;
import fuzs.shroomcraft.world.level.levelgen.feature.HugePurpleMushroomFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.phys.Vec3;

public class ModRegistry {
    public static final ResourceKey<Registry<MobBlockVariant>> CLUCKSHROOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Shroomcraft.id("cluckshroom_variant"));
    public static final RegistrySetBuilder REGISTRY_SET_BUILDER = new RegistrySetBuilder().add(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
                    CluckshroomVariants::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<DataComponentType<ModMushroomCow.ColorVariant>> MOOSHROOM_VARIANT_DATA_COMPONENT_TYPE = REGISTRIES.registerDataComponentType(
            "mooshroom/variant",
            (DataComponentType.Builder<ModMushroomCow.ColorVariant> builder) -> builder.persistent(ModMushroomCow.ColorVariant.CODEC)
                    .networkSynchronized(ModMushroomCow.ColorVariant.STREAM_CODEC));
    public static final Holder.Reference<DataComponentType<EitherHolder<MobBlockVariant>>> MOB_BLOCK_VARIANT_DATA_COMPONENT_TYPE = REGISTRIES.registerDataComponentType(
            "mob_block_variant",
            (DataComponentType.Builder<EitherHolder<MobBlockVariant>> builder) -> builder.persistent(EitherHolder.codec(
                            CLUCKSHROOM_VARIANT_REGISTRY_KEY,
                            MobBlockVariant.codec(CLUCKSHROOM_VARIANT_REGISTRY_KEY)))
                    .networkSynchronized(EitherHolder.streamCodec(CLUCKSHROOM_VARIANT_REGISTRY_KEY,
                            MobBlockVariant.streamCodec(CLUCKSHROOM_VARIANT_REGISTRY_KEY))));
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
    public static final Holder.Reference<EntityDataSerializer<ModMushroomCow.ColorVariant>> MUSHROOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "mushroom_variant",
            () -> EntityDataSerializer.forValueType(ModMushroomCow.ColorVariant.STREAM_CODEC));
    public static final Holder.Reference<EntityDataSerializer<Holder<MobBlockVariant>>> CLUCKSHROOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "cluckshroom_variant",
            () -> EntityDataSerializer.forValueType(MobBlockVariant.streamCodec(CLUCKSHROOM_VARIANT_REGISTRY_KEY)));
    public static final Holder.Reference<RecipeSerializer<DistinctShapelessRecipe>> DISTINCT_SHAPELESS_RECIPE_SERIALIZER = REGISTRIES.register(
            Registries.RECIPE_SERIALIZER,
            "crafting_shapeless_distinct",
            DistinctShapelessRecipe.Serializer::new);
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_PURPLE_MUSHROOM_FEATURE = REGISTRIES.register(
            Registries.FEATURE,
            "huge_purple_mushroom",
            () -> new HugePurpleMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_ORANGE_MUSHROOM_FEATURE = REGISTRIES.register(
            Registries.FEATURE,
            "huge_orange_mushroom",
            () -> new HugeOrangeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final Holder.Reference<Feature<HugeMushroomFeatureConfiguration>> HUGE_BLUE_MUSHROOM_FEATURE = REGISTRIES.register(
            Registries.FEATURE,
            "huge_red_mushroom",
            () -> new HugeBlueMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(() -> new ItemStack(
                    ModItems.ORANGE_MUSHROOM),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                // set this up manually to get a proper order for all the block families
                output.accept(Items.BROWN_MUSHROOM);
                output.accept(Items.RED_MUSHROOM);
                output.accept(ModItems.MUSHROOM_SPROUTS.value());
                output.accept(Items.BROWN_MUSHROOM_BLOCK);
                output.accept(Items.RED_MUSHROOM_BLOCK);
                output.accept(Items.MUSHROOM_STEM);
                output.accept(ModItems.STRIPPED_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_MUSHROOM_HYPHAE.value());
                output.accept(ModItems.SHROOMWOOD_PLANKS.value());
                ModBlockFamilies.SHROOMWOOD_FAMILY.getItemVariants()
                        .values()
                        .forEach((Holder.Reference<Item> holder) -> {
                            output.accept(holder.value());
                        });

                output.accept(ModItems.BLUE_MUSHROOM.value());
                output.accept(ModItems.BLUE_MUSHROOM_SPROUTS.value());
                output.accept(ModItems.BLUE_MUSHROOM_BLOCK.value());
                output.accept(ModItems.BLUE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_BLUE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
                output.accept(ModItems.BLUE_SHROOMWOOD_PLANKS.value());
                ModBlockFamilies.BLUE_SHROOMWOOD_FAMILY.getItemVariants()
                        .values()
                        .forEach((Holder.Reference<Item> holder) -> {
                            output.accept(holder.value());
                        });

                output.accept(ModItems.ORANGE_MUSHROOM.value());
                output.accept(ModItems.ORANGE_MUSHROOM_SPROUTS.value());
                output.accept(ModItems.ORANGE_MUSHROOM_BLOCK.value());
                output.accept(ModItems.ORANGE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_ORANGE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
                output.accept(ModItems.ORANGE_SHROOMWOOD_PLANKS.value());
                ModBlockFamilies.ORANGE_SHROOMWOOD_FAMILY.getItemVariants()
                        .values()
                        .forEach((Holder.Reference<Item> holder) -> {
                            output.accept(holder.value());
                        });

                output.accept(ModItems.PURPLE_MUSHROOM.value());
                output.accept(ModItems.PURPLE_MUSHROOM_SPROUTS.value());
                output.accept(ModItems.PURPLE_MUSHROOM_BLOCK.value());
                output.accept(ModItems.PURPLE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_PURPLE_MUSHROOM_STEM.value());
                output.accept(ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
                output.accept(ModItems.PURPLE_SHROOMWOOD_PLANKS.value());
                ModBlockFamilies.PURPLE_SHROOMWOOD_FAMILY.getItemVariants()
                        .values()
                        .forEach((Holder.Reference<Item> holder) -> {
                            output.accept(holder.value());
                        });

                output.accept(ModItems.MYCELIAL_GROWTH.value());
                output.accept(ModItems.BROWN_SHROOMSPORES.value());
                output.accept(ModItems.RED_SHROOMSPORES.value());
                output.accept(ModItems.BLUE_SHROOMSPORES.value());
                output.accept(ModItems.ORANGE_SHROOMSPORES.value());
                output.accept(ModItems.PURPLE_SHROOMSPORES.value());
                output.accept(ModItems.BLUE_SHROOMBOMB.value());
                output.accept(ModItems.ORANGE_SHROOMBOMB.value());
                output.accept(ModItems.PURPLE_SHROOMBOMB.value());
                output.accept(ModItems.SHROOMFIN.value());
                output.accept(ModItems.COOKED_SHROOMFIN.value());
                output.accept(ModItems.SHROOMFIN_BUCKET.value());
                output.accept(Items.MOOSHROOM_SPAWN_EGG);
                output.accept(ModItems.SHROOMFIN_SPAWN_EGG.value());
                output.accept(ModItems.CLUCKSHROOM_SPAWN_EGG.value());
            });

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModBlockFamilies.bootstrap();
        ModTags.bootstrap();
        ModLootTables.bootstrap();
    }
}
