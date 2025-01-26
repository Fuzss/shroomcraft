package fuzs.shroomcraft;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.AddBlockEntityTypeBlocksCallback;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.shroomcraft.handler.AxeStrippingHandler;
import fuzs.shroomcraft.init.BlockFamilyRegistrar;
import fuzs.shroomcraft.init.ModBlockFamilies;
import fuzs.shroomcraft.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Shroomcraft implements ModConstructor {
    public static final String MOD_ID = "shroomcraft";
    public static final String MOD_NAME = "Shroomcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandler();
    }

    private static void registerEventHandler() {
        PlayerInteractEvents.USE_BLOCK.register(AxeStrippingHandler::onUseBlock);
        AddBlockEntityTypeBlocksCallback.EVENT.register((BiConsumer<BlockEntityType<?>, Block> consumer) -> {
            ModBlockFamilies.getAllFamilyRegistrars()
                    .mapMulti((BlockFamilyRegistrar registrar, Consumer<Holder.Reference<Block>> blockConsumer) -> {
                        blockConsumer.accept(registrar.getBlock(BlockFamily.Variant.SIGN));
                        blockConsumer.accept(registrar.getBlock(BlockFamily.Variant.WALL_SIGN));
                    })
                    .filter(Objects::nonNull)
                    .map(Holder::value)
                    .forEach((Block block) -> {
                        consumer.accept(BlockEntityType.SIGN, block);
                    });
        });
    }

    @Override
    public void onCommonSetup() {
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            BlockSetType.register(registrar.getBlockSetType());
            WoodType.register(registrar.getWoodType());
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
