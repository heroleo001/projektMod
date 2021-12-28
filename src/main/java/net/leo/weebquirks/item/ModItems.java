package net.leo.weebquirks.item;

import net.leo.weebquirks.WeebQuirks;
import net.leo.weebquirks.item.custom.*;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeebQuirks.MOD_ID);

    public static final RegistryObject<Item> TECHNIUMINGOT = ITEMS.register("techniumingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));

    public static final RegistryObject<Item> UPGRADER = ITEMS.register("upgrader",
            () -> new Upgrader(new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP).maxDamage(-1)));

    public static final RegistryObject<Item> TECHNIUM_HAMMER = ITEMS.register("technium_hammer",
            () -> new HammerItem(ItemTier.IRON, 0, -1f,
                    new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));

    public static final RegistryObject<Item> TECHNIUM_DRILL = ITEMS.register("technium_drill",
            () -> new DrillItem(ModItemTier.TECHNIUM, 0, -1f,
                    new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));

    public static final RegistryObject<Item> TECHNIUM_CATAN = ITEMS.register("technium_catan",
            () -> new SwordItem(ItemTier.IRON, 10, 10000f,
                    new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));

    public static final RegistryObject<Item> ONE_PUNCH_SWORD = ITEMS.register("one_punch_sword",
            () -> new OnePunch(ModItemTier.ONEPUNCH, 5, 10f,
                    new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));

    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties().group(ModItemGroup.WEEBQUIRKS_GROUP)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
