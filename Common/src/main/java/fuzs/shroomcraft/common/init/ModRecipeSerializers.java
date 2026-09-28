package fuzs.shroomcraft.common.init;

import fuzs.shroomcraft.common.world.item.crafting.DistinctShapelessRecipe;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {
    public static final Holder.Reference<RecipeSerializer<DistinctShapelessRecipe>> DISTINCT_SHAPELESS_RECIPE = ModRegistry.REGISTRIES.register(
            Registries.RECIPE_SERIALIZER,
            "crafting_shapeless_distinct",
            () -> DistinctShapelessRecipe.SERIALIZER);

    public static void bootstrap() {
        // NO-OP
    }
}
