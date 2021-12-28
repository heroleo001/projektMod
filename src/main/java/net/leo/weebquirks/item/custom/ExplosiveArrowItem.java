package net.leo.weebquirks.item.custom;

import net.leo.weebquirks.entity.custom.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(worldIn, shooter);
        arrowEntity.setPotionEffect(stack);
        return arrowEntity;
    }
}
