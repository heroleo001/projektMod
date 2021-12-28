package net.leo.weebquirks.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup WEEBQUIRKS_GROUP = new ItemGroup("weebquirksgroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TECHNIUMINGOT.get());
        }
    };
}
