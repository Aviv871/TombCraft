package com.aviv871.tombcraft.handler;

import java.io.File;

import com.aviv871.tombcraft.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static final String title = "TombCraft";

    public static final String categoryGeneral = "General";
    public static final String categoryIntegration = "Integration";
    public static final String categoryMechanics = "Mechanics";
    public static final String categoryCrafting = "Crafting";

    public static boolean testValue = false;

    public static void register(File configFile)
    {
        if (configuration == null) {
            configuration = new Configuration(configFile);
        }
        loadConfiguration();

        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
    }

    private static void loadConfiguration()
    {
        testValue = configuration.getBoolean("Config Value", categoryGeneral, false, "This is an example configuration value");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
