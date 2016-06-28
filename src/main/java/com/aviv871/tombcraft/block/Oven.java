package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.init.ModBlocks;
import com.aviv871.tombcraft.reference.Blocks;
import com.aviv871.tombcraft.reference.GuiId;
import com.aviv871.tombcraft.tileentity.TileEntityOven;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

import javax.annotation.Nullable;

public class Oven extends BlockContainer
{
    private final boolean isActive;

    private static boolean keepInventory;

    public Oven(Blocks block, Material material, float hardness, float resistance, boolean isActive)
    {
        super(material);
        this.setCreativeTab(TombCraft.tabTombCraft);
        this.setRegistryName(block.getRegistryName());
        this.setUnlocalizedName(block.getUnlocalizedName());
        this.setHardness(hardness);
        this.setResistance(resistance);

        this.isActive = isActive;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            FMLNetworkHandler.openGui(playerIn, TombCraft.instance, GuiId.OVEN.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityOven();
    }

    /*public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack)
    {
        int l = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

        if(l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if(l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if(l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if(l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }



        if(itemstack.hasDisplayName())
        {
            ((TileEntityOven)world.getTileEntity(new BlockPos(x,y,z))).setGuiDisplayName(itemstack.getDisplayName());
        }
    } */

    /*private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);

            byte b0 = 3;

            if (b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }

            if (b2.func_149730_j() && !b1.func_149730_j()) {
                b0 = 2;
            }

            if (b3.func_149730_j() && !b4.func_149730_j()) {
                b0 = 5;
            }

            if (b4.func_149730_j() && !b3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, x, b0, 2);
        }
    } */


    /*public static void updateOvenBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) //TODO: REWRITE
    {
        //int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileEntity = worldObj.getTileEntity(new BlockPos(xCoord, yCoord, zCoord));
        keepInventory = true;

        if(active)
        {
            worldObj.setBlockState(new BlockPos(xCoord, yCoord, zCoord), ModBlocks.ovenActive.getDefaultState());
        }
        else
        {
            worldObj.setBlockState(new BlockPos(xCoord, yCoord, zCoord), ModBlocks.ovenIdle.getDefaultState());
        }

        keepInventory = false;

        //worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if(tileEntity != null)
        {
            tileEntity.validate();
            worldObj.setTileEntity(new BlockPos(xCoord, yCoord, zCoord), tileEntity);
        }
    } */
}
