package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Blocks;
import com.aviv871.tombcraft.reference.GuiId;
import com.aviv871.tombcraft.tileentity.TombRiserTileEntity;
import net.minecraft.block.ITileEntityProvider;
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

public class Tomb_Riser extends BlockTombCraft implements ITileEntityProvider
{
    public  Tomb_Riser(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TombRiserTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            FMLNetworkHandler.openGui(playerIn, TombCraft.instance, GuiId.TOMB_RISER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}
