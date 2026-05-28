package fuzs.shroomcraft.world.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.level.Level;

/**
 * A custom potion item implementation which creates lingering potions when thrown as vanilla is hardcoded to check for
 * {@link net.minecraft.world.item.Items#LINGERING_POTION}.
 */
public class ShroombombItem extends ThrowablePotionItem {

    public ShroombombItem(Properties properties) {
        super(properties);
    }

    /**
     * @see ThrowablePotionItem#use(Level, Player, InteractionHand)
     * @see LingeringPotionItem#use(Level, Player, InteractionHand)
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        level.playSound(null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.LINGERING_POTION_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, 10);
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (!level.isClientSide) {
            // Only replace the potion entity to set it as a lingering potion.
            ThrownPotion thrownPotion = new LingeringThrownPotion(level, player, itemStack);
            thrownPotion.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.5F, 1.0F);
            level.addFreshEntity(thrownPotion);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack itemStack, Direction direction) {
        return new LingeringThrownPotion(level, pos.x(), pos.y(), pos.z(), itemStack);
    }

    private static class LingeringThrownPotion extends ThrownPotion {

        public LingeringThrownPotion(Level level, LivingEntity owner, ItemStack item) {
            super(level, owner);
            this.setItem(item);
        }

        public LingeringThrownPotion(Level level, double x, double y, double z, ItemStack item) {
            super(level, x, y, z);
            this.setItem(item);
        }

        @Override
        protected boolean isLingering() {
            // We can override this without introducing a new entity type, as the relevant method is only called on the server.
            // Unfortunately, this custom entity will default back to the vanilla entity across reloads though, but this is good enough for now.
            return true;
        }
    }
}
