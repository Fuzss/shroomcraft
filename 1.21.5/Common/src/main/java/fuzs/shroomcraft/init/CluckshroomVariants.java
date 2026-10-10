package fuzs.shroomcraft.init;

import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

public class CluckshroomVariants {
    public static final ResourceKey<MobBlockVariant> RED_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "red");
    public static final ResourceKey<MobBlockVariant> BROWN_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "brown");
    public static final ResourceKey<MobBlockVariant> CRIMSON_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "crimson");
    public static final ResourceKey<MobBlockVariant> WARPED_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "warped");
    public static final ResourceKey<MobBlockVariant> BLUE_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "blue");
    public static final ResourceKey<MobBlockVariant> ORANGE_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "orange");
    public static final ResourceKey<MobBlockVariant> PURPLE_CLUCKSHROOM_VARIANT = ModRegistry.REGISTRIES.makeResourceKey(
            ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY,
            "purple");

    public static void bootstrap(BootstrapContext<MobBlockVariant> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        context.register(RED_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE, RED_CLUCKSHROOM_VARIANT, Blocks.RED_MUSHROOM));
        context.register(BROWN_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        BROWN_CLUCKSHROOM_VARIANT,
                        Blocks.BROWN_MUSHROOM));
        context.register(CRIMSON_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        CRIMSON_CLUCKSHROOM_VARIANT,
                        Blocks.CRIMSON_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST)));
        context.register(WARPED_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        WARPED_CLUCKSHROOM_VARIANT,
                        Blocks.WARPED_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.WARPED_FOREST)));
        context.register(BLUE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        BLUE_CLUCKSHROOM_VARIANT,
                        ModBlocks.BLUE_MUSHROOM.value()));
        context.register(ORANGE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        ORANGE_CLUCKSHROOM_VARIANT,
                        ModBlocks.ORANGE_MUSHROOM.value()));
        context.register(PURPLE_CLUCKSHROOM_VARIANT,
                new MobBlockVariant(ModRegistry.CLUCKSHROOM_ENTITY_TYPE,
                        PURPLE_CLUCKSHROOM_VARIANT,
                        ModBlocks.PURPLE_MUSHROOM.value()));
    }
}
