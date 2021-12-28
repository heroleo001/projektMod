package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.leo.weebquirks.item.ModItems;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.text.StringTextComponent;

public class SetSimonAbilityCommand {
    public SetSimonAbilityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("ability").then(Commands.literal("simon").executes((command) -> {
            return setSimonAbility(command.getSource());
        })));
    }
    private int setSimonAbility(CommandSource source) throws CommandSyntaxException{
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;

        if (!hasAbility){
            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{ 1 });
                    //LIST  Abilities:  Simon=1,  Eren=2, Sinra=3
            source.sendFeedback(new StringTextComponent("Ability set to Simon"), true);
            WeebQuirks.createLog("Ability set");
            ItemStack drill = new ItemStack(ModItems.TECHNIUM_DRILL.get());
            player.inventory.addItemStackToInventory(drill);
            WeebQuirks.setDrill(drill);
            WeebQuirks.drillMode = WeebQuirks.HAS_DRILL_ITEM;

            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("This player already has an Ability!"), true);
            WeebQuirks.createLog("Unable to set Ability. Player already has an ability.");

            return -1;
        }
    }
}
