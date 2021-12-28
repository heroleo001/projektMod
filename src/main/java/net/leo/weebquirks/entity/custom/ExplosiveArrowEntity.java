package net.leo.weebquirks.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends ArrowEntity {
    private boolean exploded = false;

    public ExplosiveArrowEntity(World worldIn, LivingEntity shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!exploded) {
            World world = this.world;
            BlockPos pos = this.getPosition();
            BlockState enlitedTNT = Blocks.TNT.getDefaultState();
            enlitedTNT.catchFire(world, pos, null, null);

            world.setBlockState(pos, enlitedTNT);

            super.onImpact(result);
            exploded =  true;
        }
    }
}
