package fuzs.shroomcraft.world.item.crafting;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.level.Level;

public class ShroombombItem extends LingeringPotionItem {

    public ShroombombItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        // copied from item super
        return stack.getComponents().getOrDefault(DataComponents.ITEM_NAME, CommonComponents.EMPTY);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        // copied from multiple super methods for replacing the spawned entity
        level.playSound(null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.LINGERING_POTION_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation((ServerLevel serverLevelX, LivingEntity owner, ItemStack itemStack) -> new ThrownPotion(
                    serverLevelX,
                    owner,
                    itemStack) {
                @Override
                protected boolean isLingering() {
                    return true;
                }
            }, serverLevel, itemInHand, player, -20.0F, 0.5F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemInHand.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        // we can override this without introducing a new entity type, as the relevant method is only called on the server
        return new ThrownPotion(level, pos.x(), pos.y(), pos.z(), stack) {
            @Override
            protected boolean isLingering() {
                return true;
            }
        };
    }
}
