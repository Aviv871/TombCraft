package com.aviv871.tombcraft.inventory;

import com.aviv871.tombcraft.tileentity.TileEntityRelicLab;
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

public class ContainerRelicLab extends Container
{
    private TileEntityRelicLab relicLab;

    public int lastBurnTime;
    public int lastCurrectItemBurnTime;
    public int lastCookTime;

    public ContainerRelicLab(InventoryPlayer inventory, TileEntityRelicLab tileEntityRelicLab)
    {
        this.relicLab = tileEntityRelicLab;
        registerSlots(inventory, tileEntityRelicLab);
    }

    private void registerSlots(InventoryPlayer inventory, TileEntityRelicLab tileEntityRelicLab)
    {
        this.addSlotToContainer(new Slot(tileEntityRelicLab, 0, 56, 35));
        this.addSlotToContainer(new Slot(tileEntityRelicLab, 1, 22, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntityRelicLab, 2, 116, 35));

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
        listener.sendProgressBarUpdate(this, 0, this.relicLab.cookTime);
        listener.sendProgressBarUpdate(this, 1, this.relicLab.burnTime);
        listener.sendProgressBarUpdate(this, 2, this.relicLab.currectItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.lastCookTime != this.relicLab.cookTime)
            {
                listener.sendProgressBarUpdate(this, 0, this.relicLab.cookTime);
            }

            if(this.lastBurnTime != this.relicLab.burnTime)
            {
                listener.sendProgressBarUpdate(this, 1, this.relicLab.burnTime);
            }

            if(this.lastCurrectItemBurnTime != this.relicLab.currectItemBurnTime)
            {
                listener.sendProgressBarUpdate(this, 2, this.relicLab.currectItemBurnTime);
            }
        }

        this.lastCookTime = this.relicLab.cookTime;
        this.lastBurnTime = this.relicLab.burnTime;
        this.lastCurrectItemBurnTime = this.relicLab.currectItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0) {
            this.relicLab.cookTime = newValue;
        }

        if (slot == 1) {
            this.relicLab.burnTime = newValue;
        }

        if (slot == 2) {
            this.relicLab.currectItemBurnTime = newValue;
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
