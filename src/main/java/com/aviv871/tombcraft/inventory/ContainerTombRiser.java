package com.aviv871.tombcraft.inventory;

import com.aviv871.tombcraft.tileentity.TombRiserTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerTombRiser extends Container
{
    private TombRiserTileEntity tombRiserTileEntity;

    public ContainerTombRiser(InventoryPlayer inventory, TombRiserTileEntity tombRiserTileEntity)
    {
        this.tombRiserTileEntity = tombRiserTileEntity;
        bindPlayerInventory(inventory);
    }


    /*
    Add slots to our GUI.
    The id's are for the slotnumbers.
    For the rest, the i * 18 and j * 18 is always the same.
    The other numbers can change, depending on your gui.
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int id = 0;
        int id2 = 0;

        for(int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, id, i * 18 + 8, 189)); //Adds player hotbar
            id++;
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryPlayer, id ,j * 18 + 8, i * 18 + 131 )); //Adds player inventory
                id++;
            }
        }

        addSlotToContainer(new SoltTombRiser(this.tombRiserTileEntity, id2, 36, 58)); //Adds input
        addSlotToContainer(new SoltTombRiser(this.tombRiserTileEntity, id2, 123, 58)); //Adds output
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return false;
    }
}
