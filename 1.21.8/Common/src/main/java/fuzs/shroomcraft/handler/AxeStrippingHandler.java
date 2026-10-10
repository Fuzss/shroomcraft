package fuzs.shroomcraft.handler;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.api.event.v1.core.EventResultHolder;
import fuzs.puzzleslib.api.item.v2.ItemHelper;
import fuzs.puzzleslib.api.util.v1.InteractionResultHelper;
import fuzs.shroomcraft.init.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

public class AxeStrippingHandler {
    static final Map<Holder<Block>, Holder<Block>> STRIPPABLES = ImmutableMap.of(Blocks.MUSHROOM_STEM.builtInRegistryHolder(),
            ModBlocks.STRIPPED_MUSHROOM_STEM,
            ModBlocks.BLUE_MUSHROOM_STEM,
            ModBlocks.STRIPPED_BLUE_MUSHROOM_STEM,
            ModBlocks.ORANGE_MUSHROOM_STEM,
            ModBlocks.STRIPPED_ORANGE_MUSHROOM_STEM,
            ModBlocks.PURPLE_MUSHROOM_STEM,
            ModBlocks.STRIPPED_PURPLE_MUSHROOM_STEM);

    public static EventResultHolder<InteractionResult> onUseBlock(Player player, Level level, InteractionHand interactionHand, BlockHitResult hitResult) {
        // cannot use vanilla stripping handling, as both blocks are expected to have the axis property, which stem blocks do not have
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (itemInHand.getItem() instanceof AxeItem && !playerHasShieldUseIntent(player, interactionHand)) {
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = level.getBlockState(blockPos);
            Holder<Block> holder = STRIPPABLES.get(blockState.getBlock().builtInRegistryHolder());
            if (holder != null) {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, itemInHand);
                }
                level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                BlockState newBlockState = holder.value().defaultBlockState();
                level.setBlock(blockPos, newBlockState, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, newBlockState));
                ItemHelper.hurtAndBreak(itemInHand, 1, player, interactionHand);
                return EventResultHolder.interrupt(InteractionResultHelper.SUCCESS);
            }
        }

        return EventResultHolder.pass();
    }

    private static boolean playerHasShieldUseIntent(Player player, InteractionHand interactionHand) {
        return interactionHand == InteractionHand.MAIN_HAND && player.getOffhandItem().is(Items.SHIELD) &&
                !player.isSecondaryUseActive();
    }
}
