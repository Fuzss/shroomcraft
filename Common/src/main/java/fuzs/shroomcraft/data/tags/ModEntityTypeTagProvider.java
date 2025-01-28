package fuzs.shroomcraft.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTagProvider extends AbstractTagProvider<EntityType<?>> {

    public ModEntityTypeTagProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.add(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.add(EntityTypeTags.AQUATIC).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.add(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.add(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value());
    }
}
