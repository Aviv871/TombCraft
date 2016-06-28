package com.aviv871.tombcraft.handler;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SoundHandler
{
    public static void onEntityPlay(String name, World world, Entity entity, float volume, float pitch)
    {
        //world.playSoundAtEntity(entity, (Reference.MOD_ID.toLowerCase() + ":" + name), (float)volume, (float)pitch); //ERROR
    }
}
