package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.reference.Blocks;
import com.aviv871.tombcraft.tileentity.TileEntityGhostSeat;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class Ghost_Seat extends BlockTombCraft implements ITileEntityProvider
{
    public Ghost_Seat(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        BlockPos newPosition = pos.offset(side);
        IBlockState newBlockState = blockAccess.getBlockState(newPosition);
        return blockState != newBlockState && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isVisuallyOpaque()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityGhostSeat();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileEntityGhostSeat tile = (TileEntityGhostSeat) worldIn.getTileEntity(pos);

        if(heldItem == null) {
            if(tile.getStackInSlot(0) != null) {
                if(addItemStackToInventoryIgnoreCreative(tile.getStackInSlot(0), playerIn.inventory)) {
                    tile.removeStackFromSlot(0);
                } else {
                    return false;
                }
            }
        } else {
            if(tile.getStackInSlot(0) == null) {
                ItemStack itemStack = heldItem.copy();
                itemStack.stackSize = 1;
                tile.setInventorySlotContents(0, itemStack);
                heldItem.stackSize--;
            } else {
                if(addItemStackToInventoryIgnoreCreative(tile.getStackInSlot(0), playerIn.inventory)) {
                    tile.removeStackFromSlot(0);
                    ItemStack itemStack = heldItem.copy();
                    itemStack.stackSize = 1;
                    tile.setInventorySlotContents(0, itemStack);
                    heldItem.stackSize--;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean addItemStackToInventoryIgnoreCreative(@Nullable final ItemStack itemStackIn, InventoryPlayer inventoryPlayer)
    {
        if (itemStackIn != null && itemStackIn.stackSize != 0 && itemStackIn.getItem() != null)
        {
            try
            {
                if (itemStackIn.isItemDamaged())
                {
                    int j = inventoryPlayer.getFirstEmptyStack();

                    if (j >= 0)
                    {
                        inventoryPlayer.mainInventory[j] = ItemStack.copyItemStack(itemStackIn);
                        inventoryPlayer.mainInventory[j].animationsToGo = 5;
                        itemStackIn.stackSize = 0;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    int i;

                    while (true)
                    {
                        i = itemStackIn.stackSize;
                        itemStackIn.stackSize = storePartialItemStack(itemStackIn, inventoryPlayer);

                        if (itemStackIn.stackSize <= 0 || itemStackIn.stackSize >= i)
                        {
                            break;
                        }
                    }

                    return itemStackIn.stackSize < i;
                }
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
                crashreportcategory.addCrashSection("Item ID", Integer.valueOf(Item.getIdFromItem(itemStackIn.getItem())));
                crashreportcategory.addCrashSection("Item data", Integer.valueOf(itemStackIn.getMetadata()));
                crashreportcategory.setDetail("Item name", new ICrashReportDetail<String>()
                {
                    public String call() throws Exception
                    {
                        return itemStackIn.getDisplayName();
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return false;
        }
    }

    private int storePartialItemStack(ItemStack itemStackIn, InventoryPlayer inventoryPlayer)
    {
        Item item = itemStackIn.getItem();
        int i = itemStackIn.stackSize;
        int j = storeItemStack(itemStackIn, inventoryPlayer);

        if (j == -1)
        {
            j = inventoryPlayer.getFirstEmptyStack();
        }

        if (j == -1)
        {
            return i;
        }
        else
        {
            ItemStack itemstack = inventoryPlayer.getStackInSlot(j);

            if (itemstack == null)
            {
                itemstack = itemStackIn.copy(); // Forge: Replace Item clone above to preserve item capabilities when picking the item up.
                itemstack.stackSize = 0;
                inventoryPlayer.setInventorySlotContents(j, itemstack);
            }

            int k = i;

            if (i > itemstack.getMaxStackSize() - itemstack.stackSize)
            {
                k = itemstack.getMaxStackSize() - itemstack.stackSize;
            }

            if (k > inventoryPlayer.getInventoryStackLimit() - itemstack.stackSize)
            {
                k = inventoryPlayer.getInventoryStackLimit() - itemstack.stackSize;
            }

            if (k == 0)
            {
                return i;
            }
            else
            {
                i = i - k;
                itemstack.stackSize += k;
                itemstack.animationsToGo = 5;
                return i;
            }
        }
    }

    private int storeItemStack(ItemStack itemStackIn, InventoryPlayer inventoryPlayer)
    {
        if (canMergeStacks(inventoryPlayer.getStackInSlot(inventoryPlayer.currentItem), itemStackIn, inventoryPlayer))
        {
            return inventoryPlayer.currentItem;
        }
        else if (canMergeStacks(inventoryPlayer.getStackInSlot(40), itemStackIn, inventoryPlayer))
        {
            return 40;
        }
        else
        {
            for (int i = 0; i < inventoryPlayer.mainInventory.length; ++i)
            {
                if (canMergeStacks(inventoryPlayer.mainInventory[i], itemStackIn, inventoryPlayer))
                {
                    return i;
                }
            }

            return -1;
        }
    }

    private boolean canMergeStacks(@Nullable ItemStack stack1, ItemStack stack2, InventoryPlayer inventoryPlayer)
    {
        return stack1 != null && stackEqualExact(stack1, stack2) && stack1.isStackable() && stack1.stackSize < stack1.getMaxStackSize() && stack1.stackSize < inventoryPlayer.getInventoryStackLimit();
    }

    private boolean stackEqualExact(ItemStack stack1, ItemStack stack2)
    {
        return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }
}
