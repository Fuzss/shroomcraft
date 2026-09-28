package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.entity.animal.MobBlockVariant;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

public class CluckshroomVariants {
    public static final ResourceKey<Registry<MobBlockVariant>> REGISTRY_KEY = ResourceKey.createRegistryKey(Shroomcraft.id(
            "cluckshroom_variant"));
    public static final ResourceKey<MobBlockVariant> RED = register("red");
    public static final ResourceKey<MobBlockVariant> BROWN = register("brown");
    public static final ResourceKey<MobBlockVariant> CRIMSON = register("crimson");
    public static final ResourceKey<MobBlockVariant> WARPED = register("warped");
    public static final ResourceKey<MobBlockVariant> BLUE = register("blue");
    public static final ResourceKey<MobBlockVariant> ORANGE = register("orange");
    public static final ResourceKey<MobBlockVariant> PURPLE = register("purple");

    private static ResourceKey<MobBlockVariant> register(String name) {
        return ResourceKey.create(REGISTRY_KEY, Shroomcraft.id(name));
    }

    public static void bootstrap(BootstrapContext<MobBlockVariant> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        context.register(RED, new MobBlockVariant(ModEntityTypes.CLUCKSHROOM, RED, Blocks.RED_MUSHROOM));
        context.register(BROWN,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM, BROWN, Blocks.BROWN_MUSHROOM));
        context.register(CRIMSON,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM,
                        CRIMSON,
                        Blocks.CRIMSON_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST)));
        context.register(WARPED,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM,
                        WARPED,
                        Blocks.WARPED_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.WARPED_FOREST)));
        context.register(BLUE,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM, BLUE, ModBlocks.BLUE_MUSHROOM.value()));
        context.register(ORANGE,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM, ORANGE, ModBlocks.ORANGE_MUSHROOM.value()));
        context.register(PURPLE,
                new MobBlockVariant(ModEntityTypes.CLUCKSHROOM, PURPLE, ModBlocks.PURPLE_MUSHROOM.value()));
    }
}
