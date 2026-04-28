package fuzs.shroomcraft.common.world.entity.animal;

import fuzs.shroomcraft.common.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fish.Cod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Shroomfin extends Cod {

    public Shroomfin(EntityType<? extends Cod> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.SHROOMFIN_BUCKET.value());
    }
}
