package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.CluckshroomVariants;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.init.ModTags;
import fuzs.shroomcraft.world.entity.animal.MobBlockVariant;
import net.minecraft.core.HolderLookup;

public class ModCluckshroomVariantTagProvider extends AbstractTagProvider<MobBlockVariant> {

    public ModCluckshroomVariantTagProvider(DataProviderContext context) {
        super(ModRegistry.CLUCKSHROOM_VARIANT_REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ModTags.DEFAULT_SPAWNS_CLUCKSHROOM_VARIANT_TAG)
                .add(CluckshroomVariants.RED_CLUCKSHROOM_VARIANT,
                        CluckshroomVariants.BROWN_CLUCKSHROOM_VARIANT,
                        CluckshroomVariants.BLUE_CLUCKSHROOM_VARIANT,
                        CluckshroomVariants.ORANGE_CLUCKSHROOM_VARIANT,
                        CluckshroomVariants.PURPLE_CLUCKSHROOM_VARIANT);
        this.tag(ModTags.NETHER_SPAWNS_CLUCKSHROOM_VARIANT_TAG)
                .add(CluckshroomVariants.CRIMSON_CLUCKSHROOM_VARIANT, CluckshroomVariants.WARPED_CLUCKSHROOM_VARIANT);
    }
}
