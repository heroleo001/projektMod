package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class SetKorosenseiAbilityCommand {
    public SetKorosenseiAbilityCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("ability").then(Commands.literal("korosensei").executes((command) -> {
            return setKorosenseiAbility(command.getSource());
        })));
    }

    private int setKorosenseiAbility(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;
        System.out.println(hasAbility + " player abilitzy");

        if (!hasAbility) {
            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{ 5 });
            WeebQuirks.setKorosenseiPlayerEntity(player);
            source.sendFeedback(new StringTextComponent("ability set to Korosensei"), true);
            player.getPersistentData().putBoolean(WeebQuirks.MOD_ID + "speedActive", false);

            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("player already has an ability"), true);
            return -1;
        }
    }
}
