package com.aviv871.tombcraft.handler;

import com.aviv871.tombcraft.client.gui.GuiRelicLab;
import com.aviv871.tombcraft.client.gui.GuiTombRiser;
import com.aviv871.tombcraft.inventory.ContainerRelicLab;
import com.aviv871.tombcraft.inventory.ContainerTombRiser;
import com.aviv871.tombcraft.reference.GuiId;
import com.aviv871.tombcraft.tileentity.TileEntityRelicLab;
import com.aviv871.tombcraft.tileentity.TileEntityTombRiser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));

        if (entityPlayer != null)
        {
            if (id == GuiId.TOMB_RISER.ordinal())
            {
                if (entity instanceof TileEntityTombRiser)
                {
                    return new ContainerTombRiser(entityPlayer.inventory, (TileEntityTombRiser) entity);
                }
            }

            if(id == GuiId.RELIC_LAB.ordinal())
            {
                if (entity instanceof TileEntityRelicLab)
                {
                    return new ContainerRelicLab(entityPlayer.inventory, (TileEntityRelicLab) entity);
                }
                return null;
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));

        if (entityPlayer != null)
        {
            if (id == GuiId.TOMB_RISER.ordinal())
            {
                if (entity instanceof TileEntityTombRiser)
                {
                    return new GuiTombRiser(entityPlayer.inventory, (TileEntityTombRiser) entity);
                }
            }

            if(id == GuiId.RELIC_LAB.ordinal())
            {
                if (entity instanceof TileEntityRelicLab)
                {
                    return new GuiRelicLab(entityPlayer.inventory, (TileEntityRelicLab) entity);
                }
                return null;
            }
        }

        return null;
    }

}
