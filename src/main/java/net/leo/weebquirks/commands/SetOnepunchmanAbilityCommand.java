package net.leo.weebquirks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.leo.weebquirks.WeebQuirks;
import net.leo.weebquirks.item.ModItemTier;
import net.leo.weebquirks.item.ModItems;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class SetOnepunchmanAbilityCommand {
    public SetOnepunchmanAbilityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("ability").then(Commands.literal("one_punch_man").executes((command) -> {
            return setOnepunchmanAbility(command.getSource());
        })));
    }

    private int setOnepunchmanAbility(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasAbility = player.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability").length != 0;
        System.out.println(hasAbility + " player ability");

        if (!hasAbility) {
            player.getPersistentData().putIntArray(WeebQuirks.MOD_ID + "ability",
                    new int[]{ 6 });
            WeebQuirks.setOnepunchmanPlayerEntity(player);
            source.sendFeedback(new StringTextComponent("ability set to Onepunchman"), true);
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.ONE_PUNCH_SWORD.get()));

            return 1;
        } else {
            source.sendFeedback(new StringTextComponent("player already has an ability"), true);
            return -1;
        }
    }
}
