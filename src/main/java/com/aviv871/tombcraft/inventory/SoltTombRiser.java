package com.aviv871.tombcraft.inventory;

import com.aviv871.tombcraft.init.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SoltTombRiser extends Slot
{
    public SoltTombRiser(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x ,y);
    }

    /*
    In this case, you can only place this item
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItem() == ModItems.scrollofForgottenKnowledge;
    }
}
