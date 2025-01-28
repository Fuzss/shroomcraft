package fuzs.shroomcraft.world.item.crafting;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
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
