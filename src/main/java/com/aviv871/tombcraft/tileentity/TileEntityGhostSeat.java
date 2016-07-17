package com.aviv871.tombcraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityGhostSeat extends TileEntity implements ISidedInventory
{
    private ItemStack slot;

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.slot;
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemStack = getStackInSlot(index);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= count)
            {
                setInventorySlotContents(index, null);
            }
            else
            {
                itemStack = itemStack.splitStack(count);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(index, null);
                }
            }
        }

        return itemStack;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        if(this.slot != null)
        {
            ItemStack itemStack = this.slot;
            this.slot = null;
            return itemStack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        this.slot = stack;

        if(stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ())) != this ? false : entityPlayer.getDistanceSq((double)this.getPos().getX() + 0.5D, (double)this.getPos().getY() + 0.5D, (double)this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
        // TODO: change..
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);

        NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(0);
        this.slot = ItemStack.loadItemStackFromNBT(compound);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("Slot", (byte) 0);
        this.slot.writeToNBT(compound);
        list.appendTag(compound);
        nbt.setTag("Items", list);

        return nbt;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        if(direction == EnumFacing.UP) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(direction == EnumFacing.DOWN) {
            return true;
        }
        return false;
    }

    @Override
    @Nonnull
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tag = new NBTTagCompound();

        this.writeToNBT(tag);
        return tag;
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.getNbtCompound();
        this.readFromNBT(tag);
    }
}
