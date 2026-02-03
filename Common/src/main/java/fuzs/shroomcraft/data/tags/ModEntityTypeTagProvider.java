package fuzs.shroomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModRegistry;
import fuzs.shroomcraft.init.family.BlockSetFamily;
import fuzs.shroomcraft.init.family.BlockSetVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.Map;

public class ModEntityTypeTagProvider extends AbstractTagProvider<EntityType<?>> {
    public static final Map<BlockSetVariant, TagKey<EntityType<?>>> VARIANT_ENTITY_TYPE_TAGS = ImmutableMap.<BlockSetVariant, TagKey<EntityType<?>>>builder()
            .put(BlockSetVariant.BOAT, EntityTypeTags.BOAT)
            .put(BlockSetVariant.CHEST_BOAT, TagFactory.COMMON.registerEntityTypeTag("boats"))
            .build();

    public ModEntityTypeTagProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        ModBlockFamilies.getAllBlockSetFamilies().forEach((BlockSetFamily blockSetFamily) -> {
            this.generateFor(blockSetFamily.getEntityVariants(), VARIANT_ENTITY_TYPE_TAGS);
        });
        this.tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.tag(EntityTypeTags.AQUATIC).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(ModRegistry.SHROOMFIN_ENTITY_TYPE.value());
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModRegistry.CLUCKSHROOM_ENTITY_TYPE.value());
    }

    public final void generateFor(Map<BlockSetVariant, Holder.Reference<EntityType<?>>> variantTypes, Map<BlockSetVariant, TagKey<EntityType<?>>> variantTags) {
        variantTypes.forEach((BlockSetVariant variant, Holder.Reference<EntityType<?>> holder) -> {
            TagKey<EntityType<?>> tagKey = variantTags.get(variant);
            if (tagKey != null) {
                this.tag(tagKey).add(holder);
            }
        });
    }
}
