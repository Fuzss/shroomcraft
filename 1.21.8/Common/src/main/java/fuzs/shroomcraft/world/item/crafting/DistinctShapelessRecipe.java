package fuzs.shroomcraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A shapeless crafting recipe implementation that does not allow for multiples of the same input.
 * <p>
 * Intended for crafting mushroom stew from a mushrooms item tag, while forcing different kinds of mushroom to be used.
 */
public class DistinctShapelessRecipe extends ShapelessRecipe {

    public DistinctShapelessRecipe(ShapelessRecipe shapelessRecipe) {
        super(shapelessRecipe.group(),
                shapelessRecipe.category(),
                shapelessRecipe.assemble(CraftingInput.EMPTY, RegistryAccess.EMPTY),
                shapelessRecipe.ingredients);
    }

    public DistinctShapelessRecipe(String group, CraftingBookCategory category, ItemStack result, List<Ingredient> ingredients) {
        super(group, category, result, ingredients);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (RecipeSerializer<?>) ModRegistry.DISTINCT_SHAPELESS_RECIPE_SERIALIZER.value();
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.items()
                .stream()
                .filter(Predicate.not(ItemStack::isEmpty))
                .map(ItemStack::getItem)
                .distinct()
                .count() != input.items().stream().filter(Predicate.not(ItemStack::isEmpty)).count()) {
            return false;
        } else {
            return super.matches(input, level);
        }
    }

    public static class Serializer implements RecipeSerializer<DistinctShapelessRecipe> {
        private static final MapCodec<DistinctShapelessRecipe> CODEC = RecipeSerializer.SHAPELESS_RECIPE.codec()
                .xmap(DistinctShapelessRecipe::new, Function.identity());
        public static final StreamCodec<RegistryFriendlyByteBuf, DistinctShapelessRecipe> STREAM_CODEC = RecipeSerializer.SHAPELESS_RECIPE.streamCodec()
                .map(DistinctShapelessRecipe::new, Function.identity());

        @Override
        public MapCodec<DistinctShapelessRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DistinctShapelessRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
