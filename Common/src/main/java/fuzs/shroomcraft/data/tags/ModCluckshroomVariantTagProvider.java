package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.core.HolderLookup;

public class ModCluckshroomVariantTagProvider extends AbstractTagProvider<MobBlockVariant> {

    public ModCluckshroomVariantTagProvider(DataProviderContext context) {
        super(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ModRegistry.DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG)
                .add(ModRegistry.RED_CLUCKSHROOM_VARIANT,
                        ModRegistry.BROWN_CLUCKSHROOM_VARIANT,
                        ModRegistry.BLUE_CLUCKSHROOM_VARIANT,
                        ModRegistry.ORANGE_CLUCKSHROOM_VARIANT,
                        ModRegistry.PURPLE_CLUCKSHROOM_VARIANT);
        this.tag(ModRegistry.NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG)
                .add(ModRegistry.CRIMSON_CLUCKSHROOM_VARIANT, ModRegistry.WARPED_CLUCKSHROOM_VARIANT);
    }
}
