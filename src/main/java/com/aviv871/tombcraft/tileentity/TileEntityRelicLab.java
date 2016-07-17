package com.aviv871.tombcraft.tileentity;

import com.aviv871.tombcraft.block.Relic_Lab;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityRelicLab extends TileEntity implements ISidedInventory, ITickable
{
    private String localizedName;

    private static final int[] slots_up = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_side = new int[]{1};

    private ItemStack[] slots = new ItemStack[3];


    public int ovenSpeed = 150;

    /** The number of ticks that the furnace will keep burning */
    public int burnTime;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currectItemBurnTime;

    /** The number of ticks that the current item has been cooking for */
    public int cookTime;

    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.localizedName : "container.relicLab";
    }

    public boolean hasCustomInventoryName()
    {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getTileEntity(new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ())) != this ? false : entityPlayer.getDistanceSq((double)this.getPos().getX() + 0.5D, (double)this.getPos().getY() + 0.5D, (double)this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot1, ItemStack itemStack)
    {
        return slot1 == 2 ? false : (slot1 == 1 ? isItemFuel(itemStack) : true) ;
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

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return getItemBurnTime(itemStack) > 0;
    }

    private static int getItemBurnTime(ItemStack itemStack)
    {
        if(itemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = itemStack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.COAL_BLOCK) return 14400;
                if (block == Blocks.SAPLING) return 100;
            }

            if (item == Items.COAL) return 1600;
            if (item == Items.STICK) return 100;
            if (item == Items.LAVA_BUCKET) return 20000;
            if (item == Items.BLAZE_ROD) return 2400;
        }

        return GameRegistry.getFuelValue(itemStack);
    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }


    public void update()
    {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if(this.isBurning())
        {
            this.burnTime--;
        }

        if(!this.worldObj.isRemote)
        {
            if(this.burnTime == 0 && this.canSmelt())
            {
                this.currectItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);

                if(this.isBurning())
                {
                    flag1 = true;

                    if(this.slots[1] != null)
                    {
                        this.slots[1].stackSize--;

                        if(this.slots[1].stackSize == 0)
                        {
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
            if(this.isBurning() && this.canSmelt())
            {
                this.cookTime++;

                if(this.cookTime == this.ovenSpeed)
                {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.cookTime = 0;
            }

            if(flag != this.isBurning())
            {
                flag1 = true;
                //Relic_Lab.updateOvenBlockState(this.isBurning(), this.worldObj, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()); //ERROR
                Relic_Lab.setState(this.isBurning() ,this.worldObj, this.getPos());
            }
        }

        if(flag1)
        {
            this.markDirty();
        }
    }

    public boolean canSmelt()
    {
        if(this.slots[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemStack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);

            if(itemStack == null)
            {
                return false;
            }

            if(this.slots[2] == null)
            {
                return true;
            }

            if(!this.slots[2].isItemEqual(itemStack))
            {
                return false;
            }

            int result = this.slots[2].stackSize + itemStack.stackSize;

            return (result <= getInventoryStackLimit() && result <= itemStack.getMaxStackSize());
        }
    }

    public void smeltItem()
    {
        if(this.canSmelt())
        {
            ItemStack itemStack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);

            if(this.slots[2] == null)
            {
                this.slots[2] = itemStack.copy();
            }
            else if(this.slots[2].isItemEqual(itemStack))
            {
                this.slots[2].stackSize += itemStack.stackSize;
            }

            this.slots[0].stackSize--;

            if(this.slots[0].stackSize <= 0)
            {
                this.slots[0] = null;
            }
        }
    }

    public int getSizeInventory()
    {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int r)
    {
        return this.slots[r];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int var1)
    {
        if(this.slots[var1] != null)
        {
            ItemStack itemStack = this.slots[var1];
            this.slots[var1] = null;
            return itemStack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack)
    {
        this.slots[i] = itemStack;

        if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, EnumFacing direction) {
        return this.isItemValidForSlot(i, itemStack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, EnumFacing direction) {
        return i != 1 || itemStack.getItem() == Items.BUCKET;
    }

    public int getBurnTimeRemainingScale(int i)
    {
        if(this.currectItemBurnTime == 0)
        {
            this.currectItemBurnTime = this.ovenSpeed;
        }

        return this.burnTime * i / this.currectItemBurnTime;
    }

    public int getCookProgressScaled(int i)
    {
        return this.cookTime * i / this.ovenSpeed;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for(int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if(b >= 0 && b < this.slots.length)
            {
                this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.burnTime = (int) nbt.getShort("BurnTime");
        this.cookTime = (int) nbt.getShort("CookTime");
        this.currectItemBurnTime = (int) nbt.getShort("CurrectItemBurnTime");

        if (nbt.hasKey("CustomName"))
        {
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short)this.burnTime);
        nbt.setShort("CookTime", (short)this.cookTime);
        nbt.setShort("CurrectItemBurnTime", (short)this.currectItemBurnTime);

        NBTTagList list = new NBTTagList();

        for(int i = 0; i < this.slots.length; i++)
        {
            if(this.slots[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if(this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.localizedName);
        }
        return nbt;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
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
        return new TextComponentString(this.localizedName);
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
