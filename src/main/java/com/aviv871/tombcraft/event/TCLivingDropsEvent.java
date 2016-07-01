package com.aviv871.tombcraft.event;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.init.ModItems;
import com.aviv871.tombcraft.utility.SoulsData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TCLivingDropsEvent
{
    public static double rand;

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event)
    {
        if (event.getSource().getDamageType().equals("player"))
        {
            //The essence
            if(event.getSource().getEntity() instanceof EntityPlayer)
            {
                if (event.getEntity() instanceof EntityAnimal)
                {
                    SoulsData.increaseSouls((EntityPlayer) (event.getSource().getEntity()), 1);
                }
                else if (event.getEntity() instanceof EntityPlayer)
                {
                    int playerSouls = SoulsData.getSouls((EntityPlayer)event.getEntity());
                    SoulsData.setSouls((EntityPlayer)event.getEntity(), 0);
                    SoulsData.increaseSouls((EntityPlayer)(event.getSource().getEntity()), playerSouls);
                }
                else
                {
                    SoulsData.increaseSouls((EntityPlayer) (event.getSource().getEntity()), 2);
                }
            }

            //The item
            rand = Math.random();

            if (!(event.getEntityLiving() instanceof EntityAnimal))
            {
                if(event.getEntityLiving().isPotionActive(TombCraft.potionTC))
                {
                    if (rand < 0.50d)
                    {
                        event.getEntityLiving().dropItem(ModItems.soul, 1);
                    }
                }
            }
        }
    }
}
