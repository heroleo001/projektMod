package net.leo.weebquirks.events;

import net.leo.weebquirks.WeebQuirks;
import net.leo.weebquirks.commands.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = WeebQuirks.MOD_ID)
public class ModEvents {



    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new SetSimonAbilityCommand(event.getDispatcher());
        new EraseAbilityCommand(event.getDispatcher());
        new SetErenAbilityCommand(event.getDispatcher());
        new SetSinraAbilityCommand(event.getDispatcher());
        new SetSenkuAbilityCommand(event.getDispatcher());
        new SetKorosenseiAbilityCommand(event.getDispatcher());
        new SetOnepunchmanAbilityCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if (!event.getOriginal().getEntityWorld().isRemote){
            event.getPlayer().getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    event.getOriginal().getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability"));
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event){
        if (WeebQuirks.sinraPlayerSet){
            ServerPlayerEntity player = WeebQuirks.sinraPlayerEntity;
            player.setFire(0);
            if (WeebQuirks.sinraPlayerEntity.getPersistentData().getBoolean(WeebQuirks.MOD_ID + "flameActive")){
                BlockPos pos = player.getPosition();
                World world = player.getEntityWorld();

                if (world.getBlockState(pos).getBlock() != Blocks.WATER && world.getBlockState(pos).getBlock() != Blocks.LAVA){
                    world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                }
            }
            player.setFire(0);
        } else {
            WeebQuirks.setSinraPlayerSet(false);
        }

        if (WeebQuirks.sinraPlayerSet){
            WeebQuirks.sinraPlayerEntity.setFire(0);
        }
    }
}
