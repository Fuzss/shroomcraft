package fuzs.shroomcraft.neoforge;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import fuzs.shroomcraft.common.Shroomcraft;
import fuzs.shroomcraft.common.data.ModRecipeProvider;
import fuzs.shroomcraft.common.data.loot.ModBlockLootProvider;
import fuzs.shroomcraft.common.data.loot.ModEntityLootProvider;
import fuzs.shroomcraft.common.data.loot.ModShearingLootProvider;
import fuzs.shroomcraft.common.data.tags.ModBlockTagsProvider;
import fuzs.shroomcraft.common.data.tags.ModCluckshroomVariantTagsProvider;
import fuzs.shroomcraft.common.data.tags.ModEntityTypeTagsProvider;
import fuzs.shroomcraft.common.data.tags.ModItemTagsProvider;
import fuzs.shroomcraft.common.init.CluckshroomVariants;
import fuzs.shroomcraft.common.init.ModFeatures;
import fuzs.shroomcraft.common.init.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(Shroomcraft.MOD_ID)
public class ShroomcraftNeoForge {

    public ShroomcraftNeoForge() {
        ModConstructor.construct(Shroomcraft.MOD_ID, Shroomcraft::new);
        DataProviderBuilder.of(Shroomcraft.MOD_ID)
                .addWorldBootstrap(CluckshroomVariants.REGISTRY_KEY, CluckshroomVariants::bootstrap)
                .addWorldBootstrap(Registries.FEATURE, ModFeatures::bootstrap)
                .addWorldBootstrap(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .addProvider(ModBlockTagsProvider::new,
                        ModItemTagsProvider::new,
                        ModEntityTypeTagsProvider::new,
                        ModCluckshroomVariantTagsProvider::new)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addLootProvider(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                .addLootProvider(ModShearingLootProvider::new, LootContextParamSets.SHEARING)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}
