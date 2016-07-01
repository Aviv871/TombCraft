package com.aviv871.tombcraft.utility;

import net.minecraft.entity.player.EntityPlayer;

public class SoulsData
{
    public static final String SOULS_DATA_ID ="tombcraft_souls";
    public static final int SOULS_DEFAULT = 0;

    public static int getSouls(EntityPlayer entityPlayer)
    {
        if(!entityPlayer.getEntityData().hasKey(SOULS_DATA_ID))
        {
            entityPlayer.getEntityData().setInteger(SOULS_DATA_ID, SOULS_DEFAULT);
        }

        return entityPlayer.getEntityData().getInteger(SOULS_DATA_ID);
    }

    public static void setSouls(EntityPlayer entityPlayer, int souls)
    {
        entityPlayer.getEntityData().setInteger(SOULS_DATA_ID, souls);
    }

    public static void increaseSouls(EntityPlayer entityPlayer, int amount)
    {
        int currentSouls = getSouls(entityPlayer);
        currentSouls += amount;
        setSouls(entityPlayer, currentSouls);
    }

    public static void decreaseSouls(EntityPlayer entityPlayer, int amount)
    {
        int currentSouls = getSouls(entityPlayer);
        currentSouls -= amount;
        setSouls(entityPlayer, currentSouls);
    }
}
