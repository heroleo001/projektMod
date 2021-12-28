package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class EraseAbilityCommand {
    public EraseAbilityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("ability").then(Commands.literal("erase_ability").executes((command) -> {
            return eraseAbility(command.getSource());
        })));
    }


    //LIST  Abilities:  Simon=1,  Eren=2, Shinra=3, Senku=4, Korosensei=5

    private int eraseAbility(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;
        System.out.println("1");
        if(hasAbility){
            switch (player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability")[0]){
                case 1:
                    WeebQuirks.setSimonPlayerSet(false);
                    WeebQuirks.setSimonPlayerEntity(null);
                    WeebQuirks.drillMode = WeebQuirks.HAS_NO_DRILL;
                    break;
                case 2:
                    WeebQuirks.setErenPlayerSet(false);
                    WeebQuirks.setErenPlayerEntity(null);
                    break;
                case 3:
                    WeebQuirks.setSinraPlayerSet(false);
                    break;
                case 4:
                    WeebQuirks.setSenkuPlayerSet(false);
                    WeebQuirks.setSenkuPlayerEntity(null);
                    break;
                case 5:
                    WeebQuirks.setKorosenseiPlayerEntity(null);
                    WeebQuirks.setKorosenseiPlayerSet(false);
                    break;
                case 6:
                    WeebQuirks.setOnepunchmanPlayerEntity(null);
                    WeebQuirks.setOnepunchmanPlayerSet(false);
                    break;
            }

            System.out.println("2");

            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{  });
            source.sendFeedback(new StringTextComponent("Ability erased."), true);

            System.out.println("3");

            /*
            if (player.inventory.hasItemStack(WeebQuirks.drill)){
                player.inventory.removeStackFromSlot(player.inventory.getSlotFor(WeebQuirks.drill));
            }
            */
            System.out.println("4");

            player.removePotionEffect(Effects.FIRE_RESISTANCE);
            player.removePotionEffect(Effects.HEALTH_BOOST);
            player.removePotionEffect(Effects.STRENGTH);
            player.removePotionEffect(Effects.SPEED);
            player.getPersistentData().putBoolean(WeebQuirks.MOD_ID + "flameActive", false);
            WeebQuirks.createLog("Ability Erased.");
            System.out.println("5");
            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("The ability can not be erased"), true);
            WeebQuirks.createLog("Ability could not be erased Erased.");
            return -1;
        }
    }
}
