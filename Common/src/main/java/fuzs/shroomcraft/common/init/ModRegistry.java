package fuzs.shroomcraft.common.init;

import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import fuzs.shroomcraft.common.Shroomcraft;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Shroomcraft.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(() -> new ItemStack(
            ModItems.ORANGE_MUSHROOM), generator -> {
        return (CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) -> {
            // Set this up manually to get a proper order for all the block families.
            output.accept(Items.BROWN_MUSHROOM);
            output.accept(Items.RED_MUSHROOM);
            output.accept(ModItems.MUSHROOM_SPROUTS.value());
            output.accept(Items.BROWN_MUSHROOM_BLOCK);
            output.accept(Items.RED_MUSHROOM_BLOCK);
            output.accept(Items.MUSHROOM_STEM);
            output.accept(ModItems.STRIPPED_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_MUSHROOM_HYPHAE.value());
            output.accept(ModItems.SHROOMWOOD_PLANKS.value());
            ModBlockFamilies.SHROOMWOOD.getItemVariants().values().forEach((Holder.Reference<Item> holder) -> {
                output.accept(holder.value());
            });

            output.accept(ModItems.BLUE_MUSHROOM.value());
            output.accept(ModItems.BLUE_MUSHROOM_SPROUTS.value());
            output.accept(ModItems.BLUE_MUSHROOM_BLOCK.value());
            output.accept(ModItems.BLUE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_BLUE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_BLUE_MUSHROOM_HYPHAE.value());
            output.accept(ModItems.BLUE_SHROOMWOOD_PLANKS.value());
            ModBlockFamilies.BLUE_SHROOMWOOD.getItemVariants()
                    .values()
                    .forEach((Holder.Reference<Item> holder) -> {
                        output.accept(holder.value());
                    });

            output.accept(ModItems.ORANGE_MUSHROOM.value());
            output.accept(ModItems.ORANGE_MUSHROOM_SPROUTS.value());
            output.accept(ModItems.ORANGE_MUSHROOM_BLOCK.value());
            output.accept(ModItems.ORANGE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_ORANGE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_ORANGE_MUSHROOM_HYPHAE.value());
            output.accept(ModItems.ORANGE_SHROOMWOOD_PLANKS.value());
            ModBlockFamilies.ORANGE_SHROOMWOOD.getItemVariants()
                    .values()
                    .forEach((Holder.Reference<Item> holder) -> {
                        output.accept(holder.value());
                    });

            output.accept(ModItems.PURPLE_MUSHROOM.value());
            output.accept(ModItems.PURPLE_MUSHROOM_SPROUTS.value());
            output.accept(ModItems.PURPLE_MUSHROOM_BLOCK.value());
            output.accept(ModItems.PURPLE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_PURPLE_MUSHROOM_STEM.value());
            output.accept(ModItems.STRIPPED_PURPLE_MUSHROOM_HYPHAE.value());
            output.accept(ModItems.PURPLE_SHROOMWOOD_PLANKS.value());
            ModBlockFamilies.PURPLE_SHROOMWOOD.getItemVariants()
                    .values()
                    .forEach((Holder.Reference<Item> holder) -> {
                        output.accept(holder.value());
                    });

            output.accept(ModItems.MYCELIAL_GROWTH.value());
            output.accept(ModItems.BROWN_SHROOMSPORES.value());
            output.accept(ModItems.RED_SHROOMSPORES.value());
            output.accept(ModItems.BLUE_SHROOMSPORES.value());
            output.accept(ModItems.ORANGE_SHROOMSPORES.value());
            output.accept(ModItems.PURPLE_SHROOMSPORES.value());
            output.accept(ModItems.BLUE_SHROOMBOMB.value());
            output.accept(ModItems.ORANGE_SHROOMBOMB.value());
            output.accept(ModItems.PURPLE_SHROOMBOMB.value());
            output.accept(ModItems.SHROOMFIN.value());
            output.accept(ModItems.COOKED_SHROOMFIN.value());
            output.accept(ModItems.SHROOMFIN_BUCKET.value());
            output.accept(Items.MOOSHROOM_SPAWN_EGG);
            output.accept(ModItems.CLUCKSHROOM_SPAWN_EGG.value());
            output.accept(ModItems.SHROOMFIN_SPAWN_EGG.value());

            // Make sure nothing is missing.
            generator.accept(parameters, output);
        };
    });

    public static void bootstrap() {
        ModBlockFamilies.bootstrap();
        ModBlocks.bootstrap();
        ModDataComponentTypes.bootstrap();
        ModEntityDataSerializers.bootstrap();
        ModEntityTypes.bootstrap();
        ModFeatureTypes.bootstrap();
        ModItems.bootstrap();
        ModRecipeSerializers.bootstrap();
    }
}
