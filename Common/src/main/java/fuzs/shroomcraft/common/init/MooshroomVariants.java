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

public class MooshroomVariants {
    public static final ResourceKey<Registry<MobVariant>> REGISTRY_KEY = ResourceKey.createRegistryKey(Shroomcraft.id(
            "mooshroom_variant"));
    public static final ResourceKey<MobVariant> BLUE = register("blue");
    public static final ResourceKey<MobVariant> ORANGE = register("orange");
    public static final ResourceKey<MobVariant> PURPLE = register("purple");
    public static final ResourceKey<MobVariant> CRIMSON = register("crimson");
    public static final ResourceKey<MobVariant> WARPED = register("warped");

    private static ResourceKey<MobVariant> register(String name) {
        return ResourceKey.create(REGISTRY_KEY, Shroomcraft.id(name));
    }

    public static void bootstrap(BootstrapContext<MobVariant> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        context.register(BLUE,
                MobVariant.create(ModEntityTypes.MOOSHROOM, BLUE.identifier().getPath(), ModBlocks.BLUE_MUSHROOM.value()));
        context.register(ORANGE,
                MobVariant.create(ModEntityTypes.MOOSHROOM,
                        ORANGE.identifier().getPath(),
                        ModBlocks.ORANGE_MUSHROOM.value()));
        context.register(PURPLE,
                MobVariant.create(ModEntityTypes.MOOSHROOM,
                        PURPLE.identifier().getPath(),
                        ModBlocks.PURPLE_MUSHROOM.value()));
        context.register(CRIMSON,
                MobVariant.create(ModEntityTypes.MOOSHROOM,
                        CRIMSON.identifier().getPath(),
                        Blocks.CRIMSON_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST)));
        context.register(WARPED,
                MobVariant.create(ModEntityTypes.MOOSHROOM,
                        WARPED.identifier().getPath(),
                        Blocks.WARPED_FUNGUS,
                        biomeLookup.getOrThrow(Biomes.WARPED_FOREST)));
    }
}
