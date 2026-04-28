package fuzs.shroomcraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A shapeless crafting recipe implementation that does not allow for multiples of the same input.
 * <p>
 * Intended for crafting mushroom stew from the {@code minecraft:mushrooms} item tag, while forcing different kinds of
 * mushroom to be used.
 */
public class DistinctShapelessRecipe extends ShapelessRecipe {
    public static final MapCodec<DistinctShapelessRecipe> MAP_CODEC = ShapelessRecipe.MAP_CODEC.xmap(
            DistinctShapelessRecipe::new,
            Function.identity());
    public static final StreamCodec<RegistryFriendlyByteBuf, DistinctShapelessRecipe> STREAM_CODEC = ShapelessRecipe.STREAM_CODEC.map(
            DistinctShapelessRecipe::new,
            Function.identity());
    public static final RecipeSerializer<DistinctShapelessRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC,
            STREAM_CODEC);

    public DistinctShapelessRecipe(ShapelessRecipe shapelessRecipe) {
        this(shapelessRecipe.commonInfo, shapelessRecipe.bookInfo, shapelessRecipe.result, shapelessRecipe.ingredients);
    }

    public DistinctShapelessRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients) {
        super(commonInfo, bookInfo, result, ingredients);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (RecipeSerializer<?>) ModRegistry.DISTINCT_SHAPELESS_RECIPE_SERIALIZER.value();
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.items().stream().filter(Predicate.not(ItemStack::isEmpty)).map(ItemStack::getItem).distinct().count()
                != input.items().stream().filter(Predicate.not(ItemStack::isEmpty)).count()) {
            return false;
        } else {
            return super.matches(input, level);
        }
    }
}
