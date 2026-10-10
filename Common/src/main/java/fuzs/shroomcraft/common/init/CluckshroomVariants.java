package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

public class CluckshroomVariants {
    public static final ResourceKey<Registry<MobVariant>> REGISTRY_KEY = ResourceKey.createRegistryKey(Shroomcraft.id(
            "cluckshroom_variant"));
    public static final ResourceKey<MobVariant> RED = register("red");
    public static final ResourceKey<MobVariant> BROWN = register("brown");
    public static final ResourceKey<MobVariant> CRIMSON = register("crimson");
    public static final ResourceKey<MobVariant> WARPED = register("warped");
    public static final ResourceKey<MobVariant> BLUE = register("blue");
    public static final ResourceKey<MobVariant> ORANGE = register("orange");
    public static final ResourceKey<MobVariant> PURPLE = register("purple");

    private static ResourceKey<MobVariant> register(String name) {
        return ResourceKey.create(REGISTRY_KEY, Shroomcraft.id(name));
    }

    public static void bootstrap(BootstrapContext<MobVariant> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        context.register(RED,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM, RED.identifier().getPath(), Blocks.RED_MUSHROOM));
        context.register(BROWN,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM, BROWN.identifier().getPath(), Blocks.BROWN_MUSHROOM));
        context.register(CRIMSON,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM,
                        CRIMSON.identifier().getPath(),
                        Blocks.CRIMSON_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST)));
        context.register(WARPED,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM,
                        WARPED.identifier().getPath(),
                        Blocks.WARPED_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.WARPED_FOREST)));
        context.register(BLUE,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM,
                        BLUE.identifier().getPath(),
                        ModBlocks.BLUE_MUSHROOM.value()));
        context.register(ORANGE,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM,
                        ORANGE.identifier().getPath(),
                        ModBlocks.ORANGE_MUSHROOM.value()));
        context.register(PURPLE,
                MobVariant.create(ModEntityTypes.CLUCKSHROOM,
                        PURPLE.identifier().getPath(),
                        ModBlocks.PURPLE_MUSHROOM.value()));
    }
}
