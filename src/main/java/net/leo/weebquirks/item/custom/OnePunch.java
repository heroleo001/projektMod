package net.leo.weebquirks.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class OnePunch extends SwordItem {
    public long useTime = 0;
    public OnePunch(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (System.currentTimeMillis() - useTime >= (60*1000*3)) {
            if (target.getArmorCoverPercentage() < 7.5) {
                target.setHealth(0);
            } else {
                target.setHealth(4);
            }
            useTime = System.currentTimeMillis();
        }
        return super.hitEntity(stack, target, attacker);
    }
}
