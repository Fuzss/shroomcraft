package fuzs.shroomcraft.data;

import fuzs.puzzleslib.api.data.v2.AbstractDatapackRegistriesProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.shroomcraft.init.ModBlocks;
import fuzs.shroomcraft.init.ModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModDatapackRegistriesProvider extends AbstractDatapackRegistriesProvider {

    public ModDatapackRegistriesProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBootstrap(RegistryBoostrapConsumer consumer) {
        consumer.add(Registries.CONFIGURED_FEATURE, ModDatapackRegistriesProvider::bootstrapConfiguredFeatures);
    }

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context,
                ModFeatures.HUGE_BLUE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_BLUE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.BLUE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context,
                ModFeatures.HUGE_ORANGE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_ORANGE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.ORANGE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
        FeatureUtils.register(context,
                ModFeatures.HUGE_PURPLE_MUSHROOM_CONFIGURED_FEATURE,
                ModFeatures.HUGE_PURPLE_MUSHROOM_FEATURE.value(),
                new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_BLOCK.value()
                        .defaultBlockState()
                        .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        BlockStateProvider.simple(ModBlocks.PURPLE_MUSHROOM_STEM.value()
                                .defaultBlockState()
                                .setValue(HugeMushroomBlock.UP, Boolean.FALSE)
                                .setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)),
                        3));
    }
}
