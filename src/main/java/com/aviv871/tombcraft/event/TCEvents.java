package com.aviv871.tombcraft.event;

import net.minecraftforge.common.MinecraftForge;

public class TCEvents
{
    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(new TCLivingDropsEvent());
        MinecraftForge.EVENT_BUS.register(new TCCreateToolEvent());
    }
}
