package fuzs.shroomcraft.client.init;

import fuzs.puzzleslib.api.client.init.v1.ModelLayerFactory;
import fuzs.shroomcraft.Shroomcraft;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    static final ModelLayerFactory MODEL_LAYERS = ModelLayerFactory.from(Shroomcraft.MOD_ID);
    public static final ModelLayerLocation SHROOMWOOD_BOAT = MODEL_LAYERS.register("boat/shroomwood");
    public static final ModelLayerLocation SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.register("chest_boat/shroomwood");
    public static final ModelLayerLocation BLUE_SHROOMWOOD_BOAT = MODEL_LAYERS.register("boat/blue_shroomwood");
    public static final ModelLayerLocation BLUE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.register(
            "chest_boat/blue_shroomwood");
    public static final ModelLayerLocation ORANGE_SHROOMWOOD_BOAT = MODEL_LAYERS.register("boat/orange_shroomwood");
    public static final ModelLayerLocation ORANGE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.register(
            "chest_boat/orange_shroomwood");
    public static final ModelLayerLocation PURPLE_SHROOMWOOD_BOAT = MODEL_LAYERS.register("boat/purple_shroomwood");
    public static final ModelLayerLocation PURPLE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.register(
            "chest_boat/purple_shroomwood");
    public static final ModelLayerLocation MOOSHROOM = MODEL_LAYERS.register("mooshroom");
    public static final ModelLayerLocation MOOSHROOM_BABY = MODEL_LAYERS.register("mooshroom_baby");
    public static final ModelLayerLocation SHROOMFIN = MODEL_LAYERS.register("shroomfin");
    public static final ModelLayerLocation CLUCKSHROOM = MODEL_LAYERS.register("cluckshroom");
    public static final ModelLayerLocation CLUCKSHROOM_BABY = MODEL_LAYERS.register("cluckshroom_baby");
}
