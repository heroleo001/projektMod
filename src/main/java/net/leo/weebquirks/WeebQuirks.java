package net.leo.weebquirks;

import net.leo.weebquirks.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WeebQuirks.MOD_ID)
public class WeebQuirks
{

    public static final String MOD_ID = "weebquirks";

    public static final KeyBinding titan = new KeyBinding("key.titan", KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_F24, "key.categories.weebquirks");
    public static final KeyBinding flameActivate = new KeyBinding("key.flameActivate", KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_F25, "key.categories.weebquirks");
    public static final KeyBinding activateSpeed = new KeyBinding("key.activateSpeed", KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_F23, "key.categories.weebquirks");

    //LIST  Abilities:  Simon=1,  Eren=2, Sinra=3, Senku=4, korosensei=5, One_punch_man=6
    public static ServerPlayerEntity erenPlayerEntity;
    public static boolean erenplayerSet = false;

    public static ServerPlayerEntity sinraPlayerEntity;
    public static boolean sinraPlayerSet = false;

    public static ServerPlayerEntity simonPlayerEntity;
    public static boolean simonPlayerSet = false;

    public static ServerPlayerEntity senkuPlayerEntity;
    public static boolean senkuPlayerSet = false;

    public static ServerPlayerEntity korosenseiPlayerEntity;
    public static boolean korosenseiPlayerSet = false;

    public static ServerPlayerEntity onepunchmanPlayerEntity;
    public static boolean onepunchmanPlayerSet;


    public static ItemStack drill;
    public static int HAS_NO_DRILL = 0;
    public static int HAS_DRILL_ITEM = 1;
    public static int drillMode;

    public static long erenTitanTime = 0;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public WeebQuirks() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(titan);
        ClientRegistry.registerKeyBinding(flameActivate);
        ClientRegistry.registerKeyBinding(activateSpeed);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("weebqirks", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    public static void createLog(String message){
        LOGGER.info(message);
    }


    // Eren
    public static void setErenPlayerEntity(ServerPlayerEntity player){
        erenPlayerEntity = player;
        erenplayerSet = true;
    }
    public static void setErenPlayerSet(boolean bool){
        erenplayerSet = bool;
    }

    // Simon
    public static void setSimonPlayerEntity(ServerPlayerEntity player){
        simonPlayerEntity = player;
        simonPlayerSet = true;
    }
    public static void setDrill(ItemStack drillItemStack){
        drill = drillItemStack;
    }
    public static void setSimonPlayerSet(boolean bool){
        simonPlayerSet = bool;
    }

    // Sinra
    public static void setSinraPlayerEntity(ServerPlayerEntity player){
        sinraPlayerEntity = player;
        sinraPlayerSet = true;
    }
    public static void setSinraPlayerSet(boolean bool){
        sinraPlayerSet = bool;
    }

    // Senku
    public static void setSenkuPlayerEntity(ServerPlayerEntity player){
        senkuPlayerEntity = player;
        senkuPlayerSet = true;
    }
    public static void setSenkuPlayerSet(boolean bool){
        senkuPlayerSet = bool;
    }

    // Korosensei
    public static void setKorosenseiPlayerEntity(ServerPlayerEntity player){
        korosenseiPlayerEntity = player;
        korosenseiPlayerSet = true;
    }
    public static void setKorosenseiPlayerSet(boolean bool){
        korosenseiPlayerSet = bool;
    }

    // One punch man
    public static void setOnepunchmanPlayerEntity(ServerPlayerEntity player){
        onepunchmanPlayerEntity = player;
        onepunchmanPlayerSet = true;
    }
    public static void setOnepunchmanPlayerSet(boolean bool){
        onepunchmanPlayerSet = bool;
    }



    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        if (WeebQuirks.titan.isPressed()) {
            System.out.println("key pressed");

            if (erenplayerSet) {

                int[] abilityType = erenPlayerEntity.getPersistentData().getIntArray(WeebQuirks.MOD_ID + "ability");
                if (abilityType[0] == 2){

                    if (System.currentTimeMillis() - erenTitanTime >= (180 * 1000 * 2)) {
                        erenPlayerEntity.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 3600, 14));
                        erenPlayerEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3600, 2));
                        erenPlayerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200, 2));
                        erenPlayerEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 200, 5));
                        erenPlayerEntity.setHealth(80);

                        for (int i = 0; i < 5; ++i){
                            LightningBoltEntity lightningBolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, erenPlayerEntity.world);
                            lightningBolt.moveToBlockPosAndAngles(erenPlayerEntity.getPosition(), 0.0F, 0.0F);
                            erenPlayerEntity.getServerWorld().addEntity(lightningBolt);
                        }

                        erenTitanTime = System.currentTimeMillis();
                    }
                }
            }
        }

        if (WeebQuirks.flameActivate.isPressed()){
            System.out.println("key pressed");
            if (sinraPlayerSet){
                sinraPlayerEntity.getPersistentData().putBoolean(WeebQuirks.MOD_ID + "flameActive",
                        !sinraPlayerEntity.getPersistentData().getBoolean(WeebQuirks.MOD_ID + "flameActive"));
            }
        }

        if (WeebQuirks.activateSpeed.isPressed()) {
            LOGGER.info("key pressed");
            if (korosenseiPlayerSet){
                korosenseiPlayerEntity.getPersistentData().putBoolean(WeebQuirks.MOD_ID + "speedActive",
                        !korosenseiPlayerEntity.getPersistentData().getBoolean(WeebQuirks.MOD_ID + "speedActive"));

                if (korosenseiPlayerEntity.getPersistentData().getBoolean(WeebQuirks.MOD_ID + "speedActive")){
                    korosenseiPlayerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 2147483646, 10, false, false));
                    korosenseiPlayerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 2147483646, 2, false, false));
                    korosenseiPlayerEntity.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 2147483646, 1, false, false));
                } else {
                    korosenseiPlayerEntity.removePotionEffect(Effects.SPEED);
                    korosenseiPlayerEntity.removePotionEffect(Effects.JUMP_BOOST);
                    korosenseiPlayerEntity.removePotionEffect(Effects.DOLPHINS_GRACE);
                }
            }
        }
    }
}
