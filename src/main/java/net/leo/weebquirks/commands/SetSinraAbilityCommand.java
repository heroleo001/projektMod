package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class SetSinraAbilityCommand {
    public SetSinraAbilityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("ability").then(Commands.literal("shinra").executes((command) -> {
            return setSinraAbility(command.getSource());
        })));
    }

    private int setSinraAbility(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;

        if (!hasAbility){
            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{ 3 });
            player.getPersistentData().putBoolean(WeebQuirks.MOD_ID + "fireActive", false);
            source.sendFeedback(new StringTextComponent("Ability set to Sinra"), true);

            player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2147483646, 1, false, false));
            WeebQuirks.setSinraPlayerEntity(player);


            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("This player already has an Ability!"), true);
            WeebQuirks.createLog("Unable to set Ability. Player already has an ability.");
            return -1;
        }
    }
}
