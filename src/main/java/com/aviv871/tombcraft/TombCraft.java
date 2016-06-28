package com.aviv871.tombcraft;

import com.aviv871.tombcraft.client.handler.KeyInputEventHandler;
import com.aviv871.tombcraft.handler.ConfigurationHandler;
import com.aviv871.tombcraft.init.ModItems;
import com.aviv871.tombcraft.init.ModRecipes;
import com.aviv871.tombcraft.proxy.IProxy;
import com.aviv871.tombcraft.reference.Reference;
import com.aviv871.tombcraft.utility.LogHelper;
import com.aviv871.tombcraft.world.WorldGeneratorTombCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod(modid= Reference.MOD_ID, name= Reference.MOD_NAME, version= Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class TombCraft
{
    @Mod.Instance(Reference.MOD_ID)
    public static TombCraft instance;

    @SidedProxy(modId = Reference.MOD_ID, clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static Potion potionTC;

    public static CreativeTabs tabTombCraft = new CreativeTabTombCraft();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                LogHelper.error("Sever error, please report this to Aviv871");
                LogHelper.error(e);
            }
        }

        proxy.registerKeyBinding();
        proxy.registerBlocks();
        proxy.registerItems();
        proxy.registerEvents();
        proxy.registerWorldGenerator();
        proxy.registerConfiguration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        potionTC = (new PotionTC(false, 32)).setIconIndex(0, 0).setPotionName("potion.potionTC"); //TODO: Rewrite

        //ModHooks.register(); //TODO: Rewrite

        proxy.registerRecipes();
        proxy.registerTileEntities();
        proxy.initRenderingAndTextures();
        proxy.registerGuiHandler();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public static class CreativeTabTombCraft extends CreativeTabs {

        public CreativeTabTombCraft() {
            super(Reference.MOD_ID);
        }

        @Override
        public Item getTabIconItem() {
            return ModItems.theTouchofDeath;
        }

    }
}
