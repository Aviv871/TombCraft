package com.aviv871.tombcraft.inventory;

import com.aviv871.tombcraft.tileentity.TileEntityOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerOven extends Container
{
    private TileEntityOven oven;

    public int lastBurnTime;
    public int lastCurrectItemBurnTime;
    public int lastCookTime;

    public ContainerOven(InventoryPlayer inventory, TileEntityOven tileEntityOven)
    {
        this.oven = tileEntityOven;
        registerSlots(inventory, tileEntityOven);
    }

    private void registerSlots(InventoryPlayer inventory, TileEntityOven tileEntityOven)
    {
        this.addSlotToContainer(new Slot(tileEntityOven, 0, 56, 35));
        this.addSlotToContainer(new Slot(tileEntityOven, 1, 22, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntityOven, 2, 116, 35));

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 8 + j*18, 84 + i*18));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i*18, 142));
        }
    }

    public void addCraftingToCrafters (IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.oven.cookTime);
        listener.sendProgressBarUpdate(this, 1, this.oven.burnTime);
        listener.sendProgressBarUpdate(this, 2, this.oven.currectItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.lastCookTime != this.oven.cookTime)
            {
                listener.sendProgressBarUpdate(this, 0, this.oven.cookTime);
            }

            if(this.lastBurnTime != this.oven.burnTime)
            {
                listener.sendProgressBarUpdate(this, 1, this.oven.burnTime);
            }

            if(this.lastCurrectItemBurnTime != this.oven.currectItemBurnTime)
            {
                listener.sendProgressBarUpdate(this, 2, this.oven.currectItemBurnTime);
            }
        }

        this.lastCookTime = this.oven.cookTime;
        this.lastBurnTime = this.oven.burnTime;
        this.lastCurrectItemBurnTime = this.oven.currectItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0) {
            this.oven.cookTime = newValue;
        }

        if (slot == 1) {
            this.oven.burnTime = newValue;
        }

        if (slot == 2) {
            this.oven.currectItemBurnTime = newValue;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 1 && p_82846_2_ != 0)
            {
                if (FurnaceRecipes.instance().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 3 && p_82846_2_ < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 30 && p_82846_2_ < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}
