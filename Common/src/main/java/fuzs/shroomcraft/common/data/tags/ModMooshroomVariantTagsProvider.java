package fuzs.shroomcraft.common.data.tags;

import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import fuzs.shroomcraft.common.init.ModTags;
import fuzs.shroomcraft.common.init.MooshroomVariants;
import fuzs.shroomcraft.common.world.entity.animal.MobVariant;
import net.minecraft.core.HolderLookup;

public class ModMooshroomVariantTagsProvider extends AbstractTagsProvider<MobVariant> {

    public ModMooshroomVariantTagsProvider(DataProviderContext context) {
        super(MooshroomVariants.REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ModTags.MooshroomVariants.DEFAULT_SPAWNS)
                .add(MooshroomVariants.BLUE, MooshroomVariants.ORANGE, MooshroomVariants.PURPLE);
        this.tag(ModTags.MooshroomVariants.NETHER_SPAWNS).add(MooshroomVariants.CRIMSON, MooshroomVariants.WARPED);
    }
}
