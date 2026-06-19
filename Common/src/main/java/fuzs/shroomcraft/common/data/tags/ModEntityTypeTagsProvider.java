package fuzs.shroomcraft.common.data.tags;

import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.shroomcraft.common.init.ModBlockFamilies;
import fuzs.shroomcraft.common.init.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTagsProvider extends AbstractTagProvider<EntityType<?>> {

    public ModEntityTypeTagsProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getEntityVariants(), VARIANT_ENTITY_TYPE_TAGS);
        });
        this.tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(ModEntityTypes.SHROOMFIN_ENTITY_TYPE);
        this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntityTypes.SHROOMFIN_ENTITY_TYPE);
        this.tag(EntityTypeTags.AQUATIC).add(ModEntityTypes.SHROOMFIN_ENTITY_TYPE);
        this.tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(ModEntityTypes.SHROOMFIN_ENTITY_TYPE);
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntityTypes.CLUCKSHROOM_ENTITY_TYPE);
    }
}
