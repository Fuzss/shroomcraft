package fuzs.shroomcraft.common.data.tags;

import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import fuzs.shroomcraft.common.init.CluckshroomVariants;
import fuzs.shroomcraft.common.init.ModTags;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.HolderLookup;

public class ModCluckshroomVariantTagsProvider extends AbstractTagsProvider<MobVariant> {

    public ModCluckshroomVariantTagsProvider(DataProviderContext context) {
        super(CluckshroomVariants.REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ModTags.CluckshroomVariants.DEFAULT_SPAWNS)
                .add(CluckshroomVariants.RED,
                        CluckshroomVariants.BROWN,
                        CluckshroomVariants.BLUE,
                        CluckshroomVariants.ORANGE,
                        CluckshroomVariants.PURPLE);
        this.tag(ModTags.CluckshroomVariants.NETHER_SPAWNS)
                .add(CluckshroomVariants.CRIMSON, CluckshroomVariants.WARPED);
    }
}
