package net.leo.weebquirks.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends ArrowEntity {


    public ExplosiveArrowEntity(World worldIn, LivingEntity shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        World world = this.world;
        BlockPos pos = this.getPosition();

        world.setBlockState(pos, Blocks.TNT.getDefaultState());
        world.setBlockState(pos.up(), Blocks.REDSTONE_TORCH.getDefaultState());


        super.onImpact(result);
    }
}
