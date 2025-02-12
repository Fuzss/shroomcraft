package fuzs.shroomcraft.client.init;

import fuzs.puzzleslib.api.client.init.v1.ModelLayerFactory;
import fuzs.shroomcraft.Shroomcraft;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    static final ModelLayerFactory MODEL_LAYERS = ModelLayerFactory.from(Shroomcraft.MOD_ID);
    public static final ModelLayerLocation SHROOMWOOD_BOAT = MODEL_LAYERS.registerModelLayer("boat/shroomwood");
    public static final ModelLayerLocation SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.registerModelLayer(
            "chest_boat/shroomwood");
    public static final ModelLayerLocation BLUE_SHROOMWOOD_BOAT = MODEL_LAYERS.registerModelLayer("boat/blue_shroomwood");
    public static final ModelLayerLocation BLUE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.registerModelLayer(
            "chest_boat/blue_shroomwood");
    public static final ModelLayerLocation ORANGE_SHROOMWOOD_BOAT = MODEL_LAYERS.registerModelLayer(
            "boat/orange_shroomwood");
    public static final ModelLayerLocation ORANGE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.registerModelLayer(
            "chest_boat/orange_shroomwood");
    public static final ModelLayerLocation PURPLE_SHROOMWOOD_BOAT = MODEL_LAYERS.registerModelLayer(
            "boat/purple_shroomwood");
    public static final ModelLayerLocation PURPLE_SHROOMWOOD_CHEST_BOAT = MODEL_LAYERS.registerModelLayer(
            "chest_boat/purple_shroomwood");
    public static final ModelLayerLocation MOOSHROOM = MODEL_LAYERS.registerModelLayer("mooshroom");
    public static final ModelLayerLocation MOOSHROOM_BABY = MODEL_LAYERS.registerModelLayer("mooshroom_baby");
    public static final ModelLayerLocation SHROOMFIN = MODEL_LAYERS.registerModelLayer("shroomfin");
    public static final ModelLayerLocation CLUCKSHROOM = MODEL_LAYERS.registerModelLayer("cluckshroom");
    public static final ModelLayerLocation CLUCKSHROOM_BABY = MODEL_LAYERS.registerModelLayer("cluckshroom_baby");
}
