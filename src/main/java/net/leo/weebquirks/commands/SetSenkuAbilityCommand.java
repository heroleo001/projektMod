package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.leo.weebquirks.item.ModItems;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.system.CallbackI;

public class SetSenkuAbilityCommand {
    public SetSenkuAbilityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("ability").then(Commands.literal("senku").executes((command) -> {
            return setSenkuAbility(command.getSource());
        })));
    }

    private int setSenkuAbility(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;
        System.out.println(hasAbility);

        if (!hasAbility){
            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{ 4 });
            source.sendFeedback(new StringTextComponent("Ability set to Senku"), true);
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.UPGRADER.get()));
            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("Ability could not be set!"), true);
            return -1;
        }
    }
}
