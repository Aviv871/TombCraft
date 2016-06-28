package com.aviv871.tombcraft.handler;

import com.aviv871.tombcraft.client.gui.GuiOven;
import com.aviv871.tombcraft.client.gui.GuiTombRiser;
import com.aviv871.tombcraft.inventory.ContainerOven;
import com.aviv871.tombcraft.inventory.ContainerTombRiser;
import com.aviv871.tombcraft.reference.GuiId;
import com.aviv871.tombcraft.tileentity.TileEntityOven;
import com.aviv871.tombcraft.tileentity.TombRiserTileEntity;
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
                if (entity instanceof TombRiserTileEntity)
                {
                    return new ContainerTombRiser(entityPlayer.inventory, (TombRiserTileEntity) entity);
                }
            }

            if(id == GuiId.OVEN.ordinal())
            {
                if (entity instanceof TileEntityOven)
                {
                    return new ContainerOven(entityPlayer.inventory, (TileEntityOven) entity);
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
                if (entity instanceof TombRiserTileEntity)
                {
                    return new GuiTombRiser(entityPlayer.inventory, (TombRiserTileEntity) entity);
                }
            }

            if(id == GuiId.OVEN.ordinal())
            {
                if (entity instanceof TileEntityOven)
                {
                    return new GuiOven(entityPlayer.inventory, (TileEntityOven) entity);
                }
                return null;
            }
        }

        return null;
    }

}
